-- 테이블 삭제
DROP TABLE employee;
DROP TABLE department;

-- 테이블 생성
CREATE TABLE department(
    dept_no   NUMBER NOT NULL,
    dept_name VARCHAR2(15 BYTE) NOT NULL,
    location  VARCHAR2(15 BYTE) NOT NULL
);

CREATE TABLE employee(
    emp_no    NUMBER NOT NULL,
    name      VARCHAR2(20 BYTE) NOT NULL,
    depart    NUMBER,
    position  VARCHAR2(20 BYTE),
    gender    CHAR(2),
    hire_date DATE,
    salary    NUMBER
);

-- 기본키 생성
ALTER TABLE department ADD CONSTRAINT department_pk PRIMARY KEY(dept_no);
ALTER TABLE employee ADD CONSTRAINT employee_pk PRIMARY KEY(emp_no);

-- 외래키 생성
ALTER TABLE employee 
    ADD CONSTRAINT employee_department_fk FOREIGN KEY(depart)
        REFERENCES department(dept_no);
        
/***************************************************************/

/*
    DML(Data Manipulation Language)
    
    1. 데이터조작어
    2. 행(Row, Record)의 삽입, 수정, 삭제 작업
    3. 작업(트랜잭션) 완료를 위해서 commit이 필요
    4. 작업(트랜잭션) 취소를 위해서 rollback이 필요
    5. 종류
        1) INSERT INTO VALUES : 삽입
        2) UPDATE SET WHERE   : 수정
        3) DELETE FROM WHERE  : 삭제
*/

-- 1. 삽입
-- 외래키가 참조하는 기본키를 가진 '부모테이블'의 데이터를 먼저 삽입해야 한다.
-- 외래키를 가진 '자식테이블'을 나중에 삽입한다.

-- 부모테이블 삽입
INSERT INTO department VALUES(1, '영업부', '대구');
INSERT INTO department VALUES(2, '인사부', '서울');
INSERT INTO department VALUES(3, '총무부', '대구');
INSERT INTO department VALUES(4, '기획부', '서울');

COMMIT; --작업 성공은 COMMIT, 작업 취소는 ROLLBACK;

-- 자식테이블 삽입
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (1001, '구창민', 1, '과장', 'M', '95-05-01', 5000000);
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (1002, '김민서', 1, '사원', 'M', '17-09-01', 2500000);    
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (1003, '이은영', 2, '부장', 'F', '90-09-01', 5500000);
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (1004, '한성일', 1, '과장', 'M', '93-04-01', 5000000);

COMMIT;

-- 2. 삽입(외부 데이터 이용)
--  1) 엑셀 데이터 생성(시트마다 테이블 1개 데이터 배치)
--  2) 테이블 선택 후 우클릭 - [데이터 임포트]
--  * 참고 : 샘플데이터 대량 생성하는 사이트
--          mockaroo.com

-- 제일 윗 줄의 테이블 삭제 및 생성을 다시 작업한다
-- TRUNCATE TABLE employee;  -- 칼럼은 남기고 레코드 모두 삭제 방법도 있음.
-- TRUNCATE TABLE department;


-- 3. 수정

-- department 테이블
-- 1) 부서번호가 1인 부서의 지역을 '인천'으로 수정하시오.
UPDATE department 
    SET location = '인천'
    WHERE dept_no = 1;

-- 2) 부서번호가 3인 부서의 이름은 '전략부', 지역을 '부산'으로 수정하시오.
UPDATE department
    SET dept_name = '전략부', location = '부산'
    WHERE dept_no = 3;
    
-- 3) 부서번호가 2인 부서의 번호를 5로 수정하시오.
--    department의 부서번호는 employee(자식테이블)의 외래키가 참조하고 있는 칼럼이므로 수정이 안됨
--    해결책)
--    (1) 외래키 일시 중지(DISABLE)
--    (2) 데이터 수정(UPDATE)
--    (3) 외래키 재시작(ENABLE)

-- 외래키 이름 : employee_department_fk

ALTER TABLE employee 
    DISABLE CONSTRAINT employee_department_fk;

UPDATE department
   SET dept_no = 5 
 WHERE dept_no = 2;
    
UPDATE employee
   SET depart = 5
 WHERE depart = 2;

ALTER TABLE employee
    ENABLE CONSTRAINT employee_department_fk;

-- employee 테이블
-- 4) 부서번호가 1인 사원들의 기본급을 100000 인상하시오.
UPDATE employee
   SET salary = salary + 100000 -- 여기서 =은 대입연산자
 WHERE depart = 1;              -- 여기서 =은 equals

-- 5) 직급이 '부장'인 사원들의 기본급을 10% 인상하시오.
UPDATE employee
   SET salary =  salary * 1.1
 WHERE position = '부장';


-- 4. 삭제

-- 1) 부서번호가 3인 부서를 삭제하시오. (가능)
--    employee 테이블에는 부서번호가 3인 행(ROW,RECORD)이 없기 때문에 가능하다.
DELETE
  FROM department    
 WHERE dept_no = 3;      
 
-- 2) 부서번호가 1인 부서를 삭제하시오. (실패)
--    EMPLOYEE 테이블에는 DEPARTMENT 테이블의 부서번호 1을 참조하고 있는 행(ROW, RECORD)이 있다
/*
ALTER TABLE employee 
    ADD CONSTRAINT employee_department_fk FOREIGN KEY(depart)
        REFERENCES department(dept_no);
*/
DELETE
  FROM department      
 WHERE dept_no = 1;
 
-- 3) 부서번호가 1인 부서를 삭제하시오. (department, employee 모두 삭제)
--    department 테이블의 부서번호 1인 행을 삭제하고, 참조 중인 employee 테이블의 행도 함께 삭제
/*
ALTER TABLE employee 
    ADD CONSTRAINT employee_department_fk FOREIGN KEY(depart)
        REFERENCES department(dept_no)
            ON DELETE CASCADE; -- 이 옵션으로 외래키를 지정한다면 department와 employee의 레코드 삭제가 한꺼번에 된다.      
DELETE
  FROM department      
 WHERE dept_no = 1;
 */