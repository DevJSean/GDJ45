-- 그룹화
-- 1. SELECT절에서 조회할 칼럼은 반드시 GROUP BY절에 존재해야 한다.
-- 2. 그룹화는 대부분 그룹함수와 함께 사용된다.


-- DEPARTMENTS 테이블

-- 1. 동일한 지역(LOCATION_ID)으로 그룹화하여 조회하시오.
SELECT LOCATION_ID
  FROM DEPARTMENTS
 GROUP BY LOCATION_ID;

-- 2. 지역(LOCATION_ID)의 중복을 제거하여 조회하시오.
SELECT DISTINCT LOCATION_ID
  FROM DEPARTMENTS;

-- 3. 동일한 지역(LOCATION_ID)으로 그룹화하여 각 지역별 부서의 수를 조회하시오.
SELECT LOCATION_ID, COUNT(*)
  FROM DEPARTMENTS
 GROUP BY LOCATION_ID;

-- 4. 부서명(DEPARTMENT_NAME)의 첫 2글자가 일치하는 부서를 그룹화하여 해당 그룹의 개수를 조회하시오.
SELECT SUBSTR(DEPARTMENT_NAME, 1, 2), COUNT(*)
  FROM DEPARTMENTS
 GROUP BY SUBSTR(DEPARTMENT_NAME, 1, 2);

-- 5. 부서명(DEPARTMENT_NAME)의 첫 2글자가 일치하는 부서를 그룹화하여 해당 그룹의 개수를 조회하시오.
--    부서명의 첫 2글자가 'IT', 'Co'인 부서만 조회하시오. (조건)
--    WHERE절  : 그룹화 이전에 처리할 수 있는 조건
--    HAVING절 : 그룹화 이후에 처리할 수 있는 조건
SELECT SUBSTR(DEPARTMENT_NAME, 1, 2), COUNT(*)
  FROM DEPARTMENTS
 WHERE SUBSTR(DEPARTMENT_NAME, 1, 2) IN('IT', 'Co')
 GROUP BY SUBSTR(DEPARTMENT_NAME, 1, 2);

-- 6. 부서명(DEPARTMENT_NAME)의 첫 2글자가 일치하는 부서를 그룹화하여 해당 그룹의 개수를 조회하시오.
--    해당 그룹의 개수가 2개 이상인 부서만 조회하시오. (조건)
--    그룹별 개수를 구한 다음에 지정할 수 있는 조건이므로 HAVING절에서 처리해야 한다.
SELECT SUBSTR(DEPARTMENT_NAME, 1, 2), COUNT(*)
  FROM DEPARTMENTS
 GROUP BY SUBSTR(DEPARTMENT_NAME, 1, 2)
HAVING COUNT(*) >= 2;


-- EMPLOYEES 테이블

-- 1. 부서번호(department_id)별로 그룹화하여 부서번호(department_id)와 부서별 사원수를 조회하시오.
SELECT department_id AS 부서번호, COUNT(*) AS 사원수
  FROM employees
 GROUP BY department_id;

-- 2. 부서번호(department_id)별로 집계하여 부서번호(department_id)와 부서별 급여(salary)평균을 조회하시오.
--    급여(salary)평균은 소수 1자리로 반올림하고, 부서번호(department_id)순으로 오름차순 정렬해서 조회하시오.
SELECT department_id AS 부서번호, ROUND(AVG(salary), 1) AS 급여평균
  FROM employees
 GROUP BY department_id
 ORDER BY department_id ASC;

-- 3. 동일한 직업아이디(job_id)을 가진 사원들의 직업아이디(job_id)와 인원수와 최대급여 및 최소급여를 조회하시오.
--    급여평균의 오름차순 정렬로 조회하시오.
SELECT job_id AS 직업아이디, COUNT(*) AS 인원수, MAX(salary) AS 최대급여, MIN(salary) AS 최소급여
  FROM employees
 GROUP BY job_id -- GROUP BY 절은 칼럼의 별명을 사용할 수 없다.
 ORDER BY AVG(salary) ASC;

-- 4. 소속된 사원수가 5명 이상인 부서의 부서번호(department_id)와 해당 부서의 사원수를 조회하시오.
--   부서번호(department_id)의 내림차순으로 정렬하여 조회하시오.
SELECT department_id AS 부서번호, COUNT(*) AS 사원수
  FROM employees
 GROUP BY department_id
HAVING COUNT(*) >= 5
 ORDER BY 부서번호 DESC; 
 --ORDER BY 부서번호 : ORDER BY 절은 SELECT절 이후에 처리되므로 SELECCT절에서 지정한 별명을 사용할 수 있다.
 
-- 5. 급여(salary)평균이 10000 이상인 부서의 부서번호(department_id)와 급여(salary)평균을 조회하시오.
--    급여(salary)는 정수로 절사처리하시오.
SELECT department_id AS 부서번호, TRUNC(AVG(salary)) AS 급여평균
  FROM employees
 GROUP BY department_id
HAVING AVG(salary) >= 10000;

-- 6. 부서번호(department_id)마다 같은 직업아이디(job_id)를 가진 사원수를 조회하시오.
--    조회된 사원수를 기준으로 내림차순 정렬하여 조회하시오.
--    단, 부서번호(department_id)가 없는 사원은 조회하지 않는다.
SELECT department_id AS 부서번호, job_id AS 직업아이디, COUNT(*) AS 사원수
  FROM employees
 WHERE department_id IS NOT NULL
 GROUP BY department_id, job_id
 ORDER BY COUNT(*) DESC;

