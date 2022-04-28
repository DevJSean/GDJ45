/* INNER JOIN */

-- 1. 부서위치(LOCATION_ID)가 1700인 부서에 근무하는 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME을 조회하시오.
SELECT e.employee_id, e.last_name, d.department_name
  FROM departments d INNER JOIN employees e
    ON d.department_id = e.department_id
 WHERE d.location_id = 1700;
 
SELECT e.employee_id, e.last_name, d.department_name
  FROM departments d, employees e
 WHERE d.department_id = e.department_id
   AND d.location_id = 1700;
 
-- 2. 부서명이 'Executive'인 부서에 근무하는 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME을 조회하시오.
SELECT e.employee_id, e.last_name, d.department_name
  FROM departments d INNER JOIN employees e
    ON d.department_id = e.department_id
 WHERE d.department_name = 'Executive';
 
SELECT e.employee_id, e.last_name, d.department_name
  FROM departments d, employees e
 WHERE d.department_id = e.department_id
   AND d.department_name = 'Executive'; 

-- 3. 직업아이디(JOB_ID)가 변하지 않은 사원들의 EMPLOYEE_ID, LAST_NAME, JOB_ID를 조회하시오.
--    현재 JOB_ID(EMPLOYEES)와 과거 JOB_ID(JOB_HISTORY)가 일치하는 사원을 조회하시오.
SELECT e.employee_id, e.last_name, e.job_id
  FROM employees e INNER JOIN job_history jh
    ON e.employee_id = jh.employee_id
 WHERE e.job_id = jh.job_id;           -- 일반조건(현재 JOB_ID(EMPLOYEES)와 과거 JOB_ID(JOB_HISTORY)가 일치)
 
SELECT e.employee_id, e.last_name, e.job_id
  FROM employees e, job_history jh
 WHERE e.employee_id = jh.employee_id
   AND e.job_id = jh.job_id;

-- 4. 부서별로 사원수와 평균연봉을 DEPARTMENT_NAME과 함께 조회하시오.
--    평균연봉은 정수로 절사하고, 사원수의 오름차순 정렬하시오.
SELECT d.department_name AS 부서명, COUNT(*) AS 사원수, TRUNC(AVG(e.salary)) AS 평균연봉
  FROM departments d INNER JOIN employees e
    ON d.department_id = e.department_id
 GROUP BY d.department_name
 ORDER BY 사원수 ASC;

SELECT d.department_name AS 부서명, COUNT(*) AS 사원수, TRUNC(AVG(e.salary)) AS 평균연봉
  FROM departments d, employees e
 WHERE d.department_id = e.department_id
 GROUP BY d.department_name
 ORDER BY 사원수 ASC;

-- 5. CITY가 'S'로 시작하는 지역에 근무하는 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME, CITY를 조회하시오.
SELECT e.employee_id, e.last_name, d.department_name, l.city
  FROM locations l INNER JOIN departments d 
                           ON l.location_id = d.location_id
                   INNER JOIN employees e   
                           ON d.department_id = e.department_id
 WHERE l.city LIKE 'S%'
 ORDER BY e.employee_id;
 
SELECT e.employee_id, e.last_name, d.department_name, l.city
  FROM locations l, departments d, employees e
 WHERE l.location_id = d.location_id
   AND d.department_id = e.department_id
   AND l.city LIKE 'S%'
 ORDER BY e.employee_id;
 
-- 6. 모든 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME, CITY, COUNTRY_NAME을 조회하시오.
--    단, department_id가 없는 사원은 조회하지 마시오
SELECT e.employee_id, e.last_name, d.department_name, l.city, c.country_name
  FROM countries c INNER JOIN locations l
                           ON c.country_id = l.country_id
                   INNER JOIN departments d
                           ON l.location_id = d.location_id
                   INNER JOIN employees e
                           ON d.department_id = e.department_id;
                
SELECT e.employee_id, e.last_name, d.department_name, l.city, c.country_name
  FROM countries c, locations l, departments d, employees e
 WHERE c.country_id = l.country_id
   AND l.location_id = d.location_id
   AND d.department_id = e.department_id;

/* OUTER JOIN */

-- 7. 모든 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME을 조회하시오.
--    부서번호(department_id)가 없는 사원도 조회하고, EMPLOYEE_ID순으로 오름차순 정렬하시오.
--    부서번호(department_id)가 없는 사원의 부서명(department_name)은 'none'으로 조회하시오.
SELECT e.employee_id, e.last_name, NVL(d.department_name, 'None')
  FROM departments d RIGHT OUTER JOIN employees e
    ON d.department_id = e.department_id
 ORDER BY e.employee_id ASC;

SELECT e.employee_id, e.last_name, NVL(d.department_name, 'None')
  FROM departments d, employees e
 WHERE d.department_id(+) = e.department_id
 ORDER BY e.employee_id ASC;

-- 8. 부서별 근무하는 사원수를 조회하시오.
--    단, 근무하는 사원이 없으면 0으로 조회하시오.
SELECT d.department_id, d.department_name, COUNT(e.department_id)
  FROM departments d LEFT OUTER JOIN employees e
    ON d.department_id = e.department_id
 GROUP BY d.department_id, d.department_name;
 
SELECT d.department_id, d.department_name, COUNT(e.department_id)
  FROM departments d, employees e
 WHERE d.department_id = e.department_id(+)
 GROUP BY d.department_id, d.department_name;

/* SELF JOIN */

-- 9. MANAGER보다 먼저 입사한 사원들의 EMPLOYEE_ID, LAST_NAME, HIRE_DATE과 MANAGER의 HIRE_DATE를 조회하시오.
--    사원의 HIRE_DATE가 MANAGER의 HIRE_DATE보다 작은 사원을 조회하시오.

-- 비교대상
-- 모든 사원의 정보 : employee e
-- manager의 정보   : employee m

-- 관계
-- PK             FK
-- m.employee_id  e.manager_id

-- 조인조건
-- manager의 employee_id - 사원의 manager_id 

SELECT e.employee_id AS 사원번호, e.last_name AS 사원명, e.hire_date AS 사원고용일, m.hire_date AS 상사고용일
  FROM employees m INNER JOIN employees e
    ON m.employee_id = e.manager_id
 WHERE TO_DATE(m.hire_date) > TO_DATE(e.hire_date); -- hire_date는 date타입이므로 직접 비교한다.
 -- WHERE TO_DATE(m.hire_date) > TO_DATE(e.hire_date); hird_date가 VARCHAR2타입이면 날짜로 변환 후 비교한다.
 
SELECT e.employee_id AS 사원번호, e.last_name AS 사원명, e.hire_date AS 사원고용일, m.hire_date AS 상사고용일
  FROM employees m, employees e
 WHERE m.employee_id = e.manager_id
   AND m.hire_date > e.hire_date; 

-- 10. 같은 부서의 사원들 중에서 
--     나보다 늦게 입사하였으나 연봉을 더 많이 받는 사원이 있는 사원들의 DEPARTMENT_ID, LAST_NAME, SALARY, HIRE_DATE와
--     나보다 늦게 입사하였으나 연봉을 더 많이 받는 사원의 FIRST_NAME, SALARY, HIRE_DATE를 조회하시오.

-- 비교대상
-- 나 : employees me
-- 남 : employees you

-- 조인조건(같은 부서)
-- 나의 department_id - 남의 department_id

SELECT me.department_id AS 부서, me.last_name AS 사원명, me.salary AS 사원급여, me.hire_date AS 사원고용일, 
       you.first_name AS 상사명, you.salary AS 상사급여, you.hire_date AS 상사고용일
  FROM employees me INNER JOIN employees you
    ON me.department_id = you.department_id
 WHERE me.hire_date < you.hire_date
   AND me.salary < you.salary;
   
SELECT me.department_id AS 부서, me.last_name AS 사원명, me.salary AS 사원급여, me.hire_date AS 사원고용일, 
       you.first_name AS 상사명, you.salary AS 상사급여, you.hire_date AS 상사고용일
  FROM employees me, employees you
 WHERE me.department_id = you.department_id
   AND me.hire_date < you.hire_date
   AND me.salary < you.salary;