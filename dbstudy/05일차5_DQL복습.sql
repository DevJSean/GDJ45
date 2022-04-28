-- 1. employees 테이블에서 salary가 10000~20000 사이가 아닌 사원들의 last_name, salary를 조회하시오.
SELECT last_name, salary
  FROM employees
 WHERE salary NOT BETWEEN 10000 AND 20000;

-- 2. employees 테이블에서 employee_id가 150인 사원의 employee_id, first_name, last_name을 조회하시오.
SELECT employee_id, first_name, last_name
  FROM employees
 WHERE employee_id = 150; -- employee_id는 number 타입이므로 이것이 정답이다.
-- WHERE employee_id = TO_NUMBER('150')으로 자동변환된 뒤 처리된다. 역시 문제 없다.

-- 3. employees 테이블에서 commission_pct가 없는 사원들의 employee_id, commission_pct를 조회하시오.
SELECT employee_id, commission_pct
  FROM employees
 WHERE commission_pct IS NULL;

-- 4. employees 테이블에서 commission_pct가 있는 사원들의 employee_id, commission_pct를 조회하시오.
SELECT employee_id, commission_pct
  FROM employees
 WHERE commission_pct IS NOT NULL;

-- 5. employees 테이블에서 모든 사원들의 employee_id와 커미션(salary * commission_pct)을 조회하시오.
--    커미션이 없는 경우 0으로 조회하시오.
SELECT employee_id, SALARY * NVL(COMMISSION_PCT, 0) AS COMMISSION
  FROM employees;

-- 6. employees 테이블에서 모든 사원들의 employee_id와 commission_pct를 10% 인상시킨 결과를 조회하시오.
--    commission_pct가 없는 경우에는 인상시키지 않도록 처리하시오.
--    두 가지 풀이
-- 우선 commission_pct * 1.1은 10% 인상 결과를 의미한다.
-- 1) NVL(commission_pct * 1.1, 0)
--    commission_pct * 1.1 결과가 null이면 0을 사용한다.
-- 2) NVL2(commission_pct, commission_pct * 1.1, 0)
--    commission_pct가 null이 아니면 commission_pct * 1.1을 사용하고 commission_pct가 null이면 0을 사용한다.

SELECT
       EMPLOYEE_ID
     , NVL(COMMISSION_PCT * 1.1, 0) AS NEW_COMMISSION_PCT
     , NVL2(COMMISSION_PCT, COMMISSION_PCT * 1.1, 0) AS NEW_COMMISSION_PCT
  FROM
       EMPLOYEES;

-- 7. employees 테이블에서 모든 사원들을 salary 기준으로 오름차순 정렬하여 조회하시오.
SELECT employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id
  FROM employees
 ORDER BY salary ASC; -- ASC 정렬은 생략 가능하다.

-- 8. employees 테이블에서 모든 사원들을 salary 기준으로 내림차순 정렬하여 조회하시오.
SELECT employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id
  FROM employees
 ORDER BY salary DESC;

-- 9. employees 테이블에서 같은 department_id를 가진 사원끼리 모아서 조회하되, 높은 salary를 가진 사원을 먼저 조회하시오.
--     (부서별로 정렬하되, 급여가 높은 사원을 먼저 조회하시오.)
SELECT employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id
  FROM employees
 ORDER BY department_id ASC, salary DESC;  -- 1차 department_id 정렬 결과가 같은 행(row, record)들을 대상으로 2차 salary 정렬이 반영된다.

-- 10. employees 테이블에서 commission_pct가 없는 사원들을 높은 salary 순으로 조회하시오.
SELECT employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id
  FROM employees
 WHERE commission_pct IS NULL
 ORDER BY salary DESC;
