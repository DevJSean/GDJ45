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

/* WHERE 절 서브쿼리 */

-- 1. 사원번호(emp_no)가 1001인 사원과 같은 직급(position)을 가진 사원을 조회하시오.
--    서브쿼리 : 사원번호가 1001인 사원의 직급(단일 행 : 사원번호(emp_no) 칼럼이 PK이므로 서브쿼리 결과는 1개)
--    메인쿼리 : SELECT 칼럼 FROM 사원 WHERE 직급 = (사원번호가 1001인 사원의 직급)
/*
서브쿼리가 실행되면 아래와 같은 메인쿼리가 완성된다.
SELECT emp_no, name, position
  FROM employee
 WHERE position = ('과장');
*/
SELECT emp_no, name, position
  FROM employee
 WHERE position = (SELECT position
                     FROM employee
                    WHERE emp_no = 1001);
                    
-- 2. 급여(salary)가 가장 높은 사원을 조회하시오.
--    서브쿼리 : 가장 높은 급여(단일 행 : 집계함수의 결과를 사용하므로 서브쿼리 결과는 1개)
--    메인쿼리 : SELECT 칼럼 FROM 사원 WHERE 급여 = (가장 높은 급여)
SELECT emp_no, name, salary
  FROM employee
 WHERE salary = (SELECT max(salary)
                   FROM employee);

-- 3. 부서번호(dept_no)가 1인 부서와 같은 지역에 있는 부서를 조회하시오.
--    서브쿼리 : 부서번호(dept_no)가 1인 부서의 지역(단일 행 : 부서번호(dept_no)칼럼이 PK이므로 서브쿼리 결과는 1개)
--    메인쿼리 : SELECT 칼럼 FROM department WHERE 지역 = (부서번호가 1인 부서의 지역)
SELECT dept_no, dept_name, location
  FROM department
 WHERE location = (SELECT location
                     FROM department
                    WHERE dept_no = 1);

-- 4. 전체 사원의 평균급여 이상을 받는 사원을 조회하시오.
--    서브쿼리 : 평균급여(단일 행 : 집계함수의 결과를 사용하므로 서브쿼리 결과는 1개)
--    메인쿼리 : SELECT 칼럼 FROM employee WHERE 급여 >= (평균급여)
SELECT emp_no, name, salary
  FROM employee
 WHERE salary >= (SELECT AVG(salary)
                    FROM employee);

-- 5. 전체 사원의 평균 근속개월 이상을 근무한 사원을 조회하시오.
--    서브쿼리 : 평균 근속개월(단일 행 : 집계함수의 결과를 사용하므로 서브쿼리 결과는 1개)
--    메인쿼리 : SELECT 칼럼 FROM employee WHERE 근속개월 >= (평균근속개월)

--  MONTHS_BETWEEN(최근날짜, 이전날짜) : 두 날짜 사이에 경과한 개월수 반환
SELECT emp_no, name, hire_date
  FROM employee
 WHERE MONTHS_BETWEEN(SYSDATE, hire_date) >= (SELECT AVG(MONTHS_BETWEEN(SYSDATE, hire_date))
                                                FROM employee);

-- 6. 부서번호(depart)가 2인 부서에 근무하는 사원들의 직급과 일치하는 직급을 가진 사원을 조회하시오.
--    서브쿼리 : 부서번호(depart)가 2인 부서에 근무하는 사원들의 직급(다중 행: 부서번호(depart)칼럼은 PK도 아니고 UNIQUE도 아니므로 서브쿼리 결과가 여러 개)
--    메인쿼리 : SELECT 칼럼 FROM employee WHERE 직급 IN(부서번호가 2인 부서에 근무하는 사원들의 직급)
SELECT emp_no, name, position
  FROM employee
 WHERE position IN(SELECT position
                     FROM employee
                    WHERE depart = 2);
                    
-- 7. 부서명(dept_name)이 '영업부'인 부서에 근무하는 사원을 조회하시오.
--    서브쿼리 : 부서명(dept_name)이 '영업부'인 부서번호(다중 행: 부서명(dept_name)칼럼은 PK도 아니고 UNIQUE도 아니므로 서브쿼리 결과가 여러 개)
--    메인쿼리 : SELECT 칼럼 FROM employee WHERE 부서번호 IN(부서명이 '영업부'인 부서번호)
SELECT emp_no, name, depart
  FROM employee
 WHERE depart IN(SELECT dept_no
                   FROM department
                  WHERE dept_name = '영업부');
 
-- 8. 직급(position)이 '과장'인 사원들이 근무하는 부서를 조회하시오.

SELECT dept_no, dept_name, location
  FROM department
 WHERE dept_no IN(SELECT depart
                   FROM employee
                  WHERE position ='과장');
                  
-- 9. 부서번호(depart)가 1인 부서에 근무하는 어떤 사원의 급여(salary)보다 더 많은 급여를 받는 사원을 조회하시오.
--    부서번호(depart)가 1인 부서에 근무하는 사원들의 급여 중 어떤 급여이든 많이 받으면 조회하시오.

-- ANY : 제시된 조건 중 하나만 만족해도 가능한 경우

-- 서브쿼리 : 부서번호(depart)가 1인 부서에 근무하는 사원들의 급여(salary)
-- 메인쿼리 : SELECT 칼럼 FROM employee WHERE 급여 > ANY(부서번호가 1인 부서에 근무하는 사원들의 급여)

SELECT emp_no, name, depart, salary
  FROM employee
 WHERE salary > ANY(SELECT salary
                      FROM employee
                     WHERE depart = 1);
                     
-- ANY 대신 집계함수 사용
-- 메인쿼리 : SELECT 칼럼 FROM employee WHERE 급여 > (부서번호가 1인 부서에 근무하는 사원들의 최소급여)
SELECT emp_no, name, depart, salary
  FROM employee
 WHERE salary > (SELECT MIN(salary)
                   FROM employee
                  WHERE depart = 1);

-- 10. 부서번호(depart)가 1인 부서에 근무하는 모든 사원의 급여(salary)보다 더 많은 급여를 받는 사원을 조회하시오.
--     부서번호(depart)가 1인 부서에 근무하는 사원들의 급여 중 어떤 급여이든 많이 받으면 조회하시오.

-- ALL : 제시된 모든 조건을 만족하는 경우

-- 서브쿼리 : 부서번호(depart)가 1인 부서에 근무하는 사원들의 급여(salary)
-- 메인쿼리 : SELECT 칼럼 FROM employee WHERE 급여 > ALL(부서번호가 1인 부서에 근무하는 사원들의 급여)
SELECT emp_no, name, depart, salary
  FROM employee
 WHERE salary > ALL(SELECT salary
                      FROM employee
                     WHERE depart = 1);
                     
-- ALL 대신 집계함수 사용
-- 메인쿼리 : SELECT 칼럼 FROM employee WHERE 급여 > (부서번호가 1인 부서에 근무하는 사원들의 최대급여)
SELECT emp_no, name, depart, salary
  FROM employee
 WHERE salary > (SELECT MAX(salary)
                   FROM employee
                  WHERE depart = 1);
