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
        

-- 부모테이블 삽입
INSERT INTO department VALUES(1, '영업부', '대구');
INSERT INTO department VALUES(2, '인사부', '서울');
INSERT INTO department VALUES(3, '총무부', '대구');
INSERT INTO department VALUES(4, '기획부', '서울');

-- 자식테이블 삽입
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (1001, '구창민', 1, '과장', 'M', '95-05-01', 5000000);
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (1002, '김민서', 1, '사원', 'M', '17-09-01', 2500000);    
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (1003, '이은영', 2, '부장', 'F', '90-09-01', 5500000);
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (1004, '한성일', 2, '과장', 'M', '93-04-01', 5000000);

COMMIT;

-----------------------------------------------------------------------------
/*
    조인(join)
    
    1. 다중 테이블을 조회하는 방법이다.
    2. 조회하거나 조건으로 사용할 칼럼이 서로 다른 테이블에 존재하는 경우에 사용한다.
    3. 종류
        1) 크로스 조인: 카테젼 곱(각 테이블의 모든 행(row) 정보를 조합)
        2) 내부 조인 : INNER JOIN, 각 테이블에 모두 존재하는 데이터를 대상으로 조인
        3) 외부 조인 : OUTER JOIN, 한 테이블은 모든 데이터를 대상으로 하고 다른 테이블은 존재하는 데이터를 대상으로 조인
    4. 형식
        1) 콤마(,) 표기법
            SELECT 조회할칼럼
              FROM 테이블 1, 테이블2
             WHERE 테이블1.칼럼 = 테이블2.칼럼; -- 조인조건
        2) JOIN 문법
            SELECT 조회할칼럼
              FROM 테이블 1 JOIN 테이블2
                ON 테이블1.칼럼 = 테이블2.칼럼; -- 조인조건
*/

-- 1. 크로스 조인 확인
-- 1) 조인 문법
SELECT e.emp_no, e.name, e.depart, d.dept_no, d.dept_name, d.location
  FROM department d CROSS JOIN employee e;
  
-- 2) 콤마 표기법
SELECT e.emp_no, e.name, e.depart, d.dept_no, d.dept_name, d.location
  FROM department d, employee e;
  
-- 2. 사원번호(emp_no), 사원명(name), 부서명(dept_name)을 조회하시오.
--    테이블이 2개 사용되어야 하므로 '조인'이다.
--    employee   : emp_no, name
--    department : dept_name
--    조인조건   : department의 dept_no와 employee의 depart 칼럼값이 일치하는 데이터

-- 1) 조인 문법
SELECT e.emp_no, e.name, d.dept_name
  FROM department d INNER JOIN employee e
    ON d.dept_no = e.depart;

-- 2) 콤마 표기법    
SELECT e.emp_no, e.name, d.dept_name
  FROM department d, employee e
 WHERE d.dept_no = e.depart; 
 
-- 3. '대구' 지역에서 근무하는 사원들의 사원번호(emp_no)와 사원명(name)을 조회하시오.
--    조건인 '대구'는 department테이블에서 확인, 조회할 칼럼인 사원번호와 사원명은 employee테이블에서 확인

-- 1) 조인문법
SELECT e.emp_no, e.name
  FROM department d INNER JOIN employee e
    ON d.dept_no = e.depart -- 조인조건
 WHERE d.location = '대구'; -- 일반조건

-- 2) 콤마 표기법
SELECT e.emp_no, e.name
  FROM department d, employee e
 WHERE d.dept_no = e.depart -- 조인조건
   AND d.location = '대구'; -- 일반조건
   
   
-- OUTER JOIN 학습을 위해 데이터 수정/추가
-- 참조무결성이 위배된 데이터이기 때문에 외래키제약조건을 잠시 중지하고 추가한다.
ALTER TABLE employee DISABLE CONSTRAINT employee_department_fk;
-- 김민서의 부서를 null로 바꾸기
UPDATE employee
   SET depart = NULL
 WHERE emp_no = 1002;
 -- 아래 데이터를 새로 추가한다.
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (1005, '김미나', 5, '사원', 'F', '18-05-01', 1800000);
COMMIT;

-- 4. 사원번호(emp_no), 사원명(name), 부서명(dept_name)을 조회하시오
--    배정 받은 부서가 없는 경우 NULL로 조회되도록 하시오
-- INNER JOIN은 배정 받은 부서가 없는 김민서, 김미나 사원이 조회되지 않는다.
-- 1001 구창민 영업부
-- 1003 이은영 인사부
-- 1004 한성일 인사부

-- OUTER JOIN은 배정 받은 부서가 없어도 *사원 테이블에 존재하는 데이터*를 모두 조회한다.
-- 1001 구창민 영업부
-- 1002 김민서 NULL
-- 1003 이은영 인사부
-- 1004 한성일 인사부
-- 1005 김미나 NULL
-- 사원테이블에 존재하는 데이터는 모두 조회: 사원테이블이 왼쪽인지 오른쪽인지 판단
-- 사원테이블이 왼쪽에 있으면   '왼쪽 외부 조인'   : LEFT OUTER JOIN
-- 사원테이블이 오른쪽에 있으면 '오른쪽 외부 조인' : RIGHT OUTER JOIN

-- 1) 조인 문법
SELECT e.emp_no, e.name, d.dept_name
  FROM department d RIGHT OUTER JOIN employee e
    ON d.dept_no = e.depart;

-- 2) 콤마 표기법
SELECT e.emp_no, e.name, d.dept_name
  FROM department d, employee e
 WHERE d.dept_no(+) = e.depart; 
--왼쪽 조인은 오른쪽에(+)표시, 오른쪽 조인은 왼쪽에(+)표시

-- 5. 부서번호별로 소속된 사원수를 조회하시오.
--    소속된 사원이 없는 경우 사원수를 0으로 조회하시오
--    부서번호 사원수
--    1        2
--    2        1
--    3        0
--    4        0
--   부서번호 3, 4는 일치하는 사원 정보가 없지만 조회되도록 왼쪽 외부 조인으로 풀이
SELECT d.dept_no AS 부서번호, COUNT(e.depart) AS 사원수
  FROM department d LEFT OUTER JOIN employee e
    ON d.dept_no = e.depart
 GROUP BY d.dept_no
 ORDER BY d.dept_no;