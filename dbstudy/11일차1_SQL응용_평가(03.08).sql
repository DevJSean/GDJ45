-- 1. 회원(MEMBER) 테이블의 칼럼 중 키가 저장된 HEIGHT 칼럼 값을 소수 이하 절사처리한 결과를 확인하려고 하는 경우 어떤 쿼리문을 작성해야 하는가? (5점)
SELECT TRUNC(HEIGHT)
  FROM MEMBER;
 
-- 2. '1990-01-01'부터 '1999-12-31'사이에 며칠이 지났는지 구하고자 하는 경우 어떤 쿼리문을 작성해야 하는가? (5점)
SELECT TO_DATE('99/12/31') - TO_DATE('90/01/01')
  FROM DUAL;

-- 3. 사원(EMPLOYEE) 테이블의 기본급(SALARY) 칼럼을 이용하여 평균 기본급을 정수로 반올림하여 계산하고자 하는 경우 어떤 쿼리문을 작성해야 하는가? (5점)
SELECT ROUND(AVG(SALARY))
  FROM EMPLOYEE;

-- 4. 사원(EMPLOYEE) 테이블의 기본급(SALARY) 칼럼과 사원명(NAME) 칼럼을 이용하여 가장 많은 기본급을 받는 사원의 사원명을 조회하고자 하는 경우 어떤 쿼리문을 작성해야 하는가? (5점)
SELECT NAME
  FROM EMPLOYEE
 WHERE SALARY = (SELECT MAX(SALARY) FROM EMPLOYEE);

-- 5. 사원(EMPLOYEE) 테이블을 가지고 있는 스키마(사용자) SCOTT을 제거하고자 하는 경우 어떤 쿼리문을 작성해야 하는가? (5점)
DROP USER SCOTT CASCADE;
--    또는
DROP TABLE EMPLOYEE;
DROP USER SCOTT;

-- 6. 다음 사용자를 생성하는 쿼리문을 작성하시오. 실제로 쿼리문을 실행하시오. (5점)
/*
    1) 사용자명 : ADMIN
    2) 비밀번호 : 1234
    3) 사용권한 : DBA
*/
CREATE USER ADMIN IDENTIFIED BY 1234;
 GRANT DBA TO ADMIN;

-- 6번 문제에서 생성한 ADMIN 계정으로 접속한 뒤 다음 SQL 파일을 다운로드 받아서 실행하시오.
-- 만약, ADMIN 계정을 만들지 못했다면 수업에서 사용한 SCOTT 계정으로 접속한 뒤 다음 SQL 파일을 다운로드 받아서 실행하시오.
DROP TABLE BUYS;
DROP TABLE USERS;
-- 사용자 테이블
-- 칼럼 : 사용자번호, 아이디, 이름, 태어난년도, 주소, 연락처1, 연락처2, 가입일
-- 기본키 : 사용자번호
CREATE TABLE USERS (
    USER_NO NUMBER PRIMARY KEY,
    USER_ID VARCHAR2(20) NOT NULL UNIQUE,
    USER_NAME VARCHAR2(20),
    USER_YEAR NUMBER(4),
    USER_ADDR VARCHAR2(100),
    USER_MOBILE1 VARCHAR2(3),  -- 010, 011 등
    USER_MOBILE2 VARCHAR2(8),  -- 12345678, 11111111 등
    USER_REGDATE DATE
);
-- 구매 테이블
-- 칼럼 : 구매번호, 아이디, 제품명, 제품카테고리, 제품가격, 구매개수
-- 기본키 : 구매번호
-- 외래키 : 아이디 (사용자 테이블의 아이디 칼럼을 참조하는 키)
CREATE TABLE BUYS (
    BUY_NO NUMBER PRIMARY KEY,
    USER_ID VARCHAR2(20) REFERENCES USERS(USER_ID),
    PROD_NAME VARCHAR2(20),
    PROD_CATEGORY VARCHAR2(20),
    PROD_PRICE NUMBER,
    BUY_AMOUNT NUMBER
);
-- USERS 테이블에 레코드(행, ROW) 삽입하기
INSERT INTO USERS VALUES (1, 'YJS', '유재석', 1972, '서울', '010', '11111111', '08/08/08');
INSERT INTO USERS VALUES (2, 'KHD', '강호동', 1970, '경북', '011', '22222222', '07/07/07');
INSERT INTO USERS VALUES (3, 'KKJ', '김국진', 1965, '서울', '019', '33333333', '09/09/09');
INSERT INTO USERS VALUES (4, 'KYM', '김용만', 1967, '서울', '010', '44444444', '15/05/05');
INSERT INTO USERS VALUES (5, 'KJD', '김제동', 1974, '경남', NULL, NULL, '13/03/03');
INSERT INTO USERS VALUES (6, 'NHS', '남희석', 1971, '충남', '016', '55555555', '14/04/04');
INSERT INTO USERS VALUES (7, 'SDY', '신동엽', 1971, '경기', NULL, NULL, '08/10/10');
INSERT INTO USERS VALUES (8, 'LHJ', '이휘재', 1972, '경기', '011', '66666666', '06/04/04');
INSERT INTO USERS VALUES (9, 'LKK', '이경규', 1960, '경남', '018', '77777777', '04/12/12');
INSERT INTO USERS VALUES (10, 'PSH', '박수홍', 1970, '서울', '010', '88888888', '12/05/05');
-- BUYS 테이블에 레코드(행, ROW) 삽입하기
INSERT INTO BUYS VALUES (1001, 'KHD', '운동화', NULL, 30, 2);
INSERT INTO BUYS VALUES (1002, 'KHD', '노트북', '전자', 1000, 1);
INSERT INTO BUYS VALUES (1003, 'KYM', '모니터', '전자', 200, 1);
INSERT INTO BUYS VALUES (1004, 'PSH', '모니터', '전자', 200, 5);
INSERT INTO BUYS VALUES (1005, 'KHD', '청바지', '의류', 50, 3);
INSERT INTO BUYS VALUES (1006, 'PSH', '메모리', '전자', 80, 10);
INSERT INTO BUYS VALUES (1007, 'KJD', '책', '서적', 15, 5);
INSERT INTO BUYS VALUES (1008, 'LHJ', '책', '서적', 15, 2);
INSERT INTO BUYS VALUES (1009, 'LHJ', '청바지', '의류', 50, 1);
INSERT INTO BUYS VALUES (1010, 'PSH', '운동화', NULL, 30, 2);
COMMIT;


-- 7. 모든 고객의 고객아이디, 고객명, 구매횟수를 조회하시오. 외부조인을 사용하시오. (12점)
/*
    실행 결과
    | 아이디  | 고객명 | 구매횟수 |
    |  KJD    | 김제동 |        1 |
    |  LHJ    | 이휘재 |        2 |
    |  KYM    | 김용만 |        1 |
    |  YJS    | 유재석 |        0 |
    ...
*/  
SELECT U.USER_ID AS 아이디,
       U.USER_NAME AS 고객명,
       COUNT(B.BUY_NO) AS 구매횟수
  FROM USERS U, BUYS B
 WHERE U.USER_ID = B.USER_ID(+)
 GROUP BY U.USER_ID, U.USER_NAME;

-- 8. 카테고리가 '전자'인 제품을 구매한 고객아이디, 고객명, 총구매액을 조회하시오. (12점)
/*
    실행 결과
    | 고객아이디 | 고객명 | 총구매액 |
    |  KYM       | 김용만 |      200 |
    |  PSH       | 박수홍 |     1800 |
    |  KHD       | 강호동 |     1000 |

*/  
SELECT U.USER_ID AS 고객아이디,
       U.USER_NAME AS 고객명,
       SUM(B.PROD_PRICE * B.BUY_AMOUNT) AS 총구매액
  FROM USERS U INNER JOIN BUYS B
    ON U.USER_ID = B.USER_ID
 WHERE B.PROD_CATEGORY = '전자'
 GROUP BY U.USER_NAME, U.USER_ID;

-- 9. 구매횟수가 2회 이상인 고객아이디, 고객명, 구매횟수를 조회하시오. 내부조인을 사용하시오. (12점)
/*
    실행 결과
    | 고객아이디 | 고객명 | 총구매액 |
    |  PSH       | 박수홍 |        3 |
    |  LHJ       | 이휘재 |        2 |
    |  KHD       | 강호동 |        3 |
*/  
SELECT U.USER_ID AS 고객아이디,
       U.USER_NAME AS 고객명,
       COUNT(*) AS 구매횟수
  FROM USERS U INNER JOIN BUYS B
    ON U.USER_ID = B.USER_ID
 GROUP BY U.USER_NAME, U.USER_ID
HAVING COUNT(*) >= 2;

-- 10. 태어난년도(USER_YEAR)가 1960년인 사용자의 이름을 서버 메시지로 출력할 수 있는 USER_PROC 프로시저를 작성하시오.
-- 작성된 USER_PROC 프로시저를 호출하기 위한 코드도 함께 작성하시오. (1960년에 태어난 사람은 1명이다.) (12점)
-- 프로시저 작성
CREATE OR REPLACE PROCEDURE USER_PROC
AS --혹은 IS
    var_name VARCHAR2;
    
BEGIN
    SELECT USER_NAME
      INTO var_name 
      FROM USERS 
     WHERE USER_YEAR = 1960;
    DBMS_OUTPUT.PUT_LINE (var_name);
END;

-- 프로시저 호출
EXECUTE USER_PROC(); -- 혹은 EXECUTE USER_PROC; 받는 매개변수가 없기 때문에 가능

-- 11. 사용자의 아이디(USER_ID)를 전달하면 해당 아이디를 가진 사용자의 구매총액(PROD_PRICE * BUY_AMOUNT)의 합계를 계산한 뒤 합계가 1000 이상이면 'A', 1000 미만 500 이상이면 'B', 500 미만이면 'C'를 반환하는 GET_GRADE() 사용자 함수를 작성하시오. 아이디가 'KHD'인 사용자의 이름(USER_NAME)과 GET_GRADE() 함수의 결과를 조회하는 쿼리문을 작성하시오. (12점)
/*
    실행 결과
    | 고객명 | 등급 |
    | 강호동 |    A |
*/  
-- 함수 작성
CREATE OR REPLACE FUNCTION GET_GRADE( ID VARCHAR2 )
RETURN CHAR  -- 혹은 VARCHAR2
IS
    total NUMBER;
    grade CHAR(1); -- 위의 반환 값에 맞춰서
    
BEGIN
    SELECT SUM(PROD_PRICE * BUY_AMOUNT) -- 여러 개의 데이터를 SUM으로 합쳐 하나의 행으로 나옴
      INTO total
      FROM BUYS
     WHERE USER_ID = ID;
     --테이블의 id = 매개변수로 전달 받은 ID  -> 여러 데이터들이 나올 것.

    IF total >= 1000 THEN
        grade := 'A';
    ELSIF total >= 500 THEN  -- ELSIF에서 1000보다 작다는 의미가 있으니 BETWEEN안해도 됨.
        grade := 'B';
    ELSE
        grade := 'C';
    END IF;
    RETURN grade;
END;

-- 함수 호출
SELECT USER_NAME AS 고객명, GET_GRADE('KHD') AS 등급 -- 함수가 buys에서 작동하지만 
  FROM USERS                                         -- users 테이블만 선택해도 됨. 
 WHERE USER_ID = 'KHD';

-- 12. 구매(BUYS) 테이블의 데이터가 수정(UPDATE)되거나 추가(INSERT)되는 경우 "구매내역이 변동되었습니다."라는 서버 메시지를 출력하고자 한다.
-- 적절한 트리거(TRIGGER)를 작성하시오. TRIGGER명은 BUYS_TRIG로 지정하시오. (10점)
CREATE OR REPLACE TRIGGER BUYS_TRIG
    AFTER
    UPDATE OR INSERT
    ON BUYS
    FOR EACH ROW

BEGIN
    DBMS_OUTPUT.PUT_LINE('구매내역이 변동되었습니다.');
END BUYS_TRIG;