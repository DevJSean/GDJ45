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

/*
    서브쿼리(sub query)
    
    1. 메인쿼리에 포함되는 하위쿼리이다.
    2. 서브쿼리는 괄호()를 이용해서 묶어준다.
    3. SELECT절에서 사용되면 '스칼라 서브쿼리',
       FROM절에서 사용되면 '인라인 뷰',
       WHERE절에서 사용되면 '서브쿼리'라고 부른다.
    4. 종류
       1) 단일 행(SINGLE ROW) 서브쿼리 : 서브쿼리 결과가 1개(=, !=, >, <, >=, <=) 사용
       2) 다중 행(MULTI ROW) 서브쿼리 : 서브쿼리 결과가 여러개, 다중 행 연산(IN, ANY, ALL 등) 사용
       다중행 연산 IN은 단일행에서도 사용 가능하다. (만능키 같은 느낌)
*/

   /* SELECT 절 스칼라(SCALA SUBQUERY) 서브쿼리 */
-- 1. SELECT절에서 사용되는 서브쿼리이다.
-- 2. 스칼라 서브쿼리는 단일 행 서브쿼리만 가능하다.

-- 1. 전체 사원의 인원수, 급여합, 급여평균, 최대급여, 최소급여를 조회하시오.
--    1) 일반 풀이
SELECT COUNT(*), SUM(salary), AVG(salary), MAX(salary), MIN(salary)
  FROM employee;

--    2) 스칼라 서브쿼리 풀이
SELECT (SELECT COUNT(*) FROM employee),
       (SELECT SUM(salary) FROM employee),
       (SELECT AVG(salary) FROM employee),
       (SELECT MAX(salary) FROM employee),
       (SELECT MIN(salary) FROM employee)
  FROM DUAL;

-- 2. 부서번호(dept_no)가 1인 부서와 같은 지역(location)에서 근무하는 사원들을 조회하시오.
--    사원번호(emp_no), 사원명(name), 부서명(dept_name)을 조회하시오.

-- 1) 일반 풀이
--    조인을 이용하시오.
--    스칼라 서브쿼리는 일치하지 않는 데이터를 NULL 처리한다.
--    같은 방식의 조인은 '외부조인'이다.
SELECT e.emp_no, e.name, d.dept_name
  FROM department d RIGHT OUTER JOIN employee e
    ON d.dept_no = e.depart
 WHERE d.location = (SELECT location
                       FROM department
                      WHERE dept_no = 1);

-- 2) 스칼라 서브쿼리 풀이
SELECT e.emp_no, e.name, (SELECT d.dept_name FROM department d WHERE d.dept_no = e.depart AND d.dept_no = 1)
  FROM employee e;