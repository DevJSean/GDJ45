/*
     트리거(TRIGGER)
     1. DMl(INSERT, UPDATE, DELETE) 작업을 수행하는 경우 함께 수행하는 작업이다.
     2. DML 작업 수행 직전에 수행하는 BEFORE TRIGGER와
        DML 작업 수행 직후에 수행하는 AFTER TRIGGER가 있다.
     3. 형식
        CREATE [OR REPLACE] TRIGGER 트리거명
            트리거 설정
        BEGIN
            작업 수행
        END
*/

SET SERVEROUTPUT ON;


-- 트리거 TRIG1 정의
CREATE OR REPLACE TRIGGER trig1
    AFTER -- AFTER, BEFORE
    INSERT OR DELETE OR UPDATE -- INSERT, UPDATE, DELETE
    ON employees               -- 트리거를 적용할 테이블
    FOR EACH ROW               -- 트리거를 행(ROW)마다 적용
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello Trigger');
END trig1;

-- 트리거 TRIG1 동작 확인 (employees테이블에 INSERT, UPDATE, DELETE할 때 동작한다)
UPDATE employees
   SET salary = 25000
 WHERE employee_id = 100;


-- 트리거 TRIG2 정의
CREATE OR REPLACE TRIGGER trig2
    AFTER
    INSERT OR UPDATE OR DELETE
    ON employees
    FOR EACH ROW
BEGIN
    IF INSERTING THEN -- INSERT 했을 때
       DBMS_OUTPUT.PUT_LINE('INSERT 이후 동작');
    ELSIF UPDATING THEN
          DBMS_OUTPUT.PUT_LINE('UPDATE 이후 동작');
    ELSIF DELETING THEN
          DBMS_OUTPUT.PUT_LINE('DELETE 이후 동작');
    END IF;
END trig2;

-- 트리거 TRIG2 동작 확인 (employees테이블에 INSERT, UPDATE, DELETE할 때 동작한다)
UPDATE employees
   SET salary = 24000
 WHERE employee_id = 100;



-- 트리거 실습
-- 사원(employees) 테이블에서 삭제된 데이터를 퇴사자(retires) 테이블에 추가하시오

-- 1. 퇴사자(retires) 테이블 생성
-- 1) employees 테이블과 같은 구조로 복사
CREATE TABLE retires
    AS (SELECT employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id
          FROM employees
         WHERE 1 = 2 );
-- 2) 칼럼 추가
ALTER TABLE retires ADD retire_date DATE NOT NULL; -- 퇴사일이 언제인지 칼럼 추가
ALTER TABLE retires ADD retire_id NUMBER;          -- 기본키로 사용할 칼럼 추가
-- 3) 기본키 설정
ALTER TABLE retires ADD CONSTRAINT retires_pk PRIMARY KEY(retire_id);

-- 2. 퇴사자 시퀀스 생성
CREATE SEQUENCE retires_seq  NOCACHE;

-- 3. 트리거에서 사용되는 테이블
/*
            INSERT          UPDATE          DELETE
    :OLD    NULL            수정전데이터    삭제된데이터
    :NEW    삽입한데이터    수정후데이터    NULL
*/
-- AFTER DELETE를 설정하면 삭제된 데이터가 :OLD에 저장된다.

-- 4. 트리거 정의
CREATE OR REPLACE TRIGGER retire_trig
    AFTER
    DELETE
    ON employees
    FOR EACH ROW
BEGIN
    INSERT INTO retires(retire_id, 
                        employee_id, 
                        first_name, 
                        last_name, 
                        email, 
                        phone_number, 
                        hire_date, 
                        job_id, 
                        salary, 
                        commission_pct, 
                        manager_id, 
                        department_id, 
                        retire_date
                        ) VALUES (
                        retires_seq.NEXTVAL,  
                        :OLD.employee_id, 
                        :OLD.first_name, 
                        :OLD.last_name, 
                        :OLD.email, 
                        :OLD.phone_number, 
                        :OLD.hire_date, 
                        :OLD.job_id, 
                        :OLD.salary, 
                        :OLD.commission_pct, 
                        :OLD.manager_id, 
                        :OLD.department_id,
                        SYSDATE);
END retire_trig;



DELETE 
  FROM employees
 WHERE employee_id = 100;







