/*
    프로시저(PROCEDURE)
    
    1. 여러 개의 쿼리문을 한 번에 처리할 수 있다.
    2. 동시에 진행해야하는 쿼리문들을 한 번에 수행할 수 있다.
       (예 : 이체의 경우 2개의 UPDATE문이 필요)
    3. EXECUTE(줄여서 EXEC)문으로 실행한다.
    4. 형식
        CREATE [OR REPLACE] PROCEDURE 프로시저명(매개변수)
        IS -- AS도 가능
            변수선언
        BEGIN
            작업수행
        [EXCEPTION]
            [WHEN OTHERS THEN]
            예외처리
        END 프로시저명;
*/

SET SERVEROUTPUT ON;

-- 프로시저 PROC1 정의
CREATE OR REPLACE PROCEDURE PROC1
IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello Procedure');
END PROC1;

-- 프로시저 PROC1 호출
EXECUTE PROC1();




-- 프로시저 PROC2 정의
CREATE OR REPLACE PROCEDURE PROC2
IS
    var_last_name EMPLOYEES.LAST_NAME%TYPE;
    var_employee_id EMPLOYEES.EMPLOYEE_ID%TYPE;
BEGIN
    var_employee_id := 100;
    SELECT LAST_NAME
      INTO var_last_name
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = var_employee_id;
    DBMS_OUTPUT.PUT_LINE(var_last_name);
END PROC2;

-- 프로시저 PROC2 실행
EXECUTE PROC2();




-- 프로시저 PROC3 정의
-- 입력 파라미터 : 프로시저로 전달되는 값을 저장할 변수
-- 형식 : 변수명 IN 타입
CREATE OR REPLACE PROCEDURE PROC3(var_employee_id IN EMPLOYEES.EMPLOYEE_ID%TYPE)
IS
    var_last_name EMPLOYEES.LAST_NAME%TYPE;
BEGIN
    SELECT LAST_NAME
      INTO var_last_name
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = var_employee_id;
    DBMS_OUTPUT.PUT_LINE(var_last_name);
END PROC3;

-- 프로시저 PROC3 실행
EXECUTE PROC3(100);  -- 인수 100을 입력 파라미터 var_employee_id에 전달한다.




-- 프로시저 PROC4 정의
-- 출력 파라미터 : 프로시저의 결과 값을 저장할 변수
-- 형식 : 변수명 OUT 타입
CREATE OR REPLACE PROCEDURE PROC4(var_last_name OUT EMPLOYEES.LAST_NAME%TYPE)
IS
    var_employee_id EMPLOYEES.EMPLOYEE_ID%TYPE;
BEGIN
    var_employee_id := 100;
    SELECT LAST_NAME
      INTO var_last_name
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = var_employee_id;
END PROC4;

-- 프로시저 PROC4 실행
DECLARE
    var_last_name EMPLOYEES.LAST_NAME%TYPE;
BEGIN
    PROC4(var_last_name);  -- 프로그래밍 내부에서는 EXECUTE가 없어야 프로시저를 호출할 수 있다.
    DBMS_OUTPUT.PUT_LINE(var_last_name);
END;




-- 프로시저 PROC5 정의
CREATE OR REPLACE PROCEDURE PROC5
(
    var_employee_id IN  EMPLOYEES.EMPLOYEE_ID%TYPE,
    var_last_name   OUT EMPLOYEES.LAST_NAME%TYPE
)
IS
BEGIN
    SELECT LAST_NAME
      INTO var_last_name
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = var_employee_id;
END PROC5;

-- 프로시저 PROC5 실행
DECLARE
    var_employee_id EMPLOYEES.EMPLOYEE_ID%TYPE;
    var_last_name   EMPLOYEES.LAST_NAME%TYPE;
BEGIN
    var_employee_id := 100;
    PROC5(var_employee_id, var_last_name);
    DBMS_OUTPUT.PUT_LINE(var_last_name);
END;




-- 연습
-- 사원번호를 전달하면 해당 사원의 연봉을 반환하는 프로시저 PROC6을 작성하시오.
-- 사원번호는 입력 파라미터로 처리하고, 연봉은 출력 파라미터로 처리하시오.
-- 전달된 사원번호가 존재하지 않는다면 연봉은 0으로 조회되도록 처리하시오. (EXCEPTION)
CREATE OR REPLACE PROCEDURE PROC6
(
    var_employee_id IN  EMPLOYEES.EMPLOYEE_ID%TYPE,
    var_salary      OUT EMPLOYEES.SALARY%TYPE
)
IS
BEGIN
    SELECT SALARY
      INTO var_salary
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = var_employee_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN  -- WHEN OTHERS THEN으로 처리할 수도 있음.
        var_salary := 0;
END PROC6;

DECLARE
    var_employee_id EMPLOYEES.EMPLOYEE_ID%TYPE;
    var_salary      EMPLOYEES.SALARY%TYPE;
BEGIN
    var_employee_id := 99;
    PROC6(var_employee_id, var_salary);
    DBMS_OUTPUT.PUT_LINE('사원번호 ' || var_employee_id || '의 연봉 ' || var_salary);
END;


--------------------------------------------------------------------------------
-- 제품을 구매하는 상황
-- 구매목록 insert
-- 사용자포인트 update
-- 제품목록 update

-- 실습 환경    있던 테이블 다 지우고 새로 만든다
-- 제품 테이블
CREATE TABLE products(
    prod_code NUMBER,            -- 제품 코드
    prod_name VARCHAR2(10 BYTE), -- 제품명
    prod_price NUMBER,           -- 가격
    prod_stock NUMBER            -- 재고
);
ALTER TABLE products
    ADD CONSTRAINT products_pk PRIMARY KEY(prod_code);
INSERT INTO products VALUES(1000, '진라면', 500, 100);
INSERT INTO products VALUES(1001, '신라면', 600, 100);
COMMIT;
    
--고객 테이블
CREATE TABLE customers(
    cust_no    NUMBER,               -- 고객 번호
    cust_name  VARCHAR2(10 BYTE),    -- 고객명
    cust_point NUMBER                -- 고객포인트
);
ALTER TABLE customers
    ADD CONSTRAINT customers_pk PRIMARY KEY(cust_no);
INSERT INTO customers VALUES(1, '철수', 0);
INSERT INTO customers VALUES(2, '영희', 0);
COMMIT;

-- 구매 테이블
CREATE TABLE buys(
    buy_no     NUMBER,    --구매번호
    cust_no    NUMBER,    --구매자번호
    prod_code  NUMBER,    --구매제품
    buy_amount NUMBER     --구매수량
);
ALTER TABLE buys
    ADD CONSTRAINT buys_pk PRIMARY KEY(buy_no);
ALTER TABLE buys
    ADD CONSTRAINT buys_customers_fk FOREIGN KEY(cust_no)
        REFERENCES customers(cust_no);
ALTER TABLE buys
    ADD CONSTRAINT buys_products_fk FOREIGN KEY(prod_code)
        REFERENCES products(prod_code);

-- 구매 테이블 시퀀스 생성 
CREATE SEQUENCE buys_seq NOCACHE;


-- 구매 프로시저
-- 1. buy_proc(고객번호, 제품코드, 구매수량)
-- 2. 진행해야 할 쿼리
--      1) 구매 테이블에 구매 내역을 INSERT 한다.
--      2) 고객 테이블에 고객포인트를 UPDATE 한다. (총 구매액의 10% 적립)
--      3) 제품 테이블의 재고를 UPDATE 한다.

CREATE OR REPLACE PROCEDURE BUY_PROC
(
    -- 입력 파라미터 선언
    var_cust_no    IN customers.cust_no%TYPE,
    var_prod_code  IN products.prod_code%TYPE,
    var_buy_amount IN buys.buy_amount%TYPE
)
IS
BEGIN
    -- 쿼리문 작성
    -- 1) 구매 테이블에 구매 내역을 INSERT 한다.
    INSERT INTO buys VALUES(buys_seq.NEXTVAL, var_cust_no, var_prod_code, var_buy_amount);
    
    -- 2) 고객 테이블의 고객포인트를 UPDATE한다. (총 구매액의 10% 적립, 정수로 올림 처리)
    UPDATE customers          -- 올림처리((제품가격) * 구매수량 * 0.1)
       SET cust_point = cust_point + CEIL((SELECT prod_price  
                                             FROM products
                                            WHERE prod_code = var_prod_code) * var_buy_amount * 0.1)
     WHERE cust_no = var_cust_no;
     
    -- 3) 제품 테이블의 재고를 UPDATE한다.
     UPDATE products
        SET prod_stock = prod_stock - var_buy_amount
      WHERE prod_code = var_prod_code;
      
    -- 4) 커밋
    COMMIT;
    
EXCEPTION
    -- 예외 처리
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('예외코--드 ' || SQLCODE);
        DBMS_OUTPUT.PUT_LINE('예외메시지 ' || SQLERRM);     
        -- 롤백
        ROLLBACK;
END buy_proc;

-- 프로시저 실행
EXECUTE buy_proc(1, 1000, 5);

-- 확인
SELECT prod_code, prod_name, prod_price, prod_stock
  FROM products;
SELECT cust_no, cust_name, cust_point
  FROM customers;
SELECT buy_no, cust_no, prod_code, buy_amount
  FROM buys;