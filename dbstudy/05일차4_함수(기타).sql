-- 기타 함수


-- 1. 순위
--    1) RANK() OVER(ORDER BY 순위를구할칼럼 ASC)  : 오름차순 순위(낮은 값이 1등, ASC 생략 가능, 투수의 평균자책률)
--    2) RANK() OVER(ORDER BY 순위를구할칼럼 DESC) : 내림차순 순위(높은 값이 1등)
--    3) 같은 값이면 동점으로 처리(등수가 같음) A 1등, B 1등, C 3등

-- 1) employees 테이블의 사원 정보를 연봉(salary) 순으로 조회하시오 (높은 연봉이 1등)
--    각 사원의 연봉 순위를 함께 조회하시오.
SELECT RANK() OVER(ORDER BY salary DESC) AS 연봉순위
     , email
     , salary
  FROM employees
 ORDER BY salary desc;
 
-- 2) employees테이블의 사원 정보를 입사(hire_date) 순으로 조회하시오. (먼저 입사한 사원이 1등)
--    각 사원의 입사순위를 함께 조회하시오.
SELECT RANK() OVER(ORDER BY hire_date ASC) AS 입사순위
     , email
     , hire_date
  FROM employees
 ORDER BY hire_date ASC;
 
 
-- 2. 그룹 내의 순위
--    RANK() OVER (PARTITION BY 그룹칼럼 ORDER BY 순위구할칼럼 ASC) : 그룹 내 오름차순 순위
--    RANK() OVER (PARTITION BY 그룹칼럼 ORDER BY 순위구할칼럼 DESC): 그룹 내 내림차순 순위
SELECT RANK() OVER(PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) AS 부서내연봉순위,
       department_id,
       salary
  FROM employees
 ORDER BY department_id ASC, salary DESC;

-- PARTITION BY와 OVER 활용하기   순위는 필요 없고 그룹화만 하고 싶을 때 
-- PARTITION BY는 그룹화 작업을 수행하므로 그룹함수(집계함수)와 함께 사용 가능
SELECT distinct department_id, -- 중복제거
       SUM(salary) OVER (PARTITION BY department_id) AS 부서별연봉합,
       AVG(salary) OVER (PARTITION BY department_id) AS 부서별연봉평균,
       MAX(salary) OVER (PARTITION BY department_id) AS 부서별최대연봉,
       MIN(salary) OVER (PARTITION BY department_id) AS 부서별최소연봉,
       COUNT(*)    OVER (PARTITION BY department_id) AS 부서별사원수
  FROM employees;

-- 3. 분기
--    DECODE(표현식, 값1, 출력1, 값2, 출력2, 값3, 출력3, ...)
--    표현식과 값의 비교는 동등비교(=)만 가능

--   employees, departments 테이블의 join 대신 decode 함수 사용

-- department_id    department_name
-- 10               administration
-- 20               marketing
-- 30               purchasing
-- 40               human resources
-- 50               shipping
SELECT department_id,
       decode(department_id, 10, 'Administration',
                             20, 'Marketing',
                             30, 'Purchasing',
                             40, 'Human Resources',
                             50, 'Shipping') AS 부서명
  FROM employees;

-- 문제
-- phone_number     result
-- 011              'HP'
-- 515              'EAST'
-- 590              'WEST'
-- 603              'SOUTH'
-- 650              'NORTH'
SELECT phone_number,
       DECODE(SUBSTR(phone_number,1,3), '011', 'HP',   -- substring 값이 문자열이니까 '011' 따옴표 씌우는게 맞다
                                        '515', 'EAST',
                                        '590', 'WEST',
                                        '603', 'SOUTH',
                                        '650', 'NORTH') AS RESULT
  FROM employees;
  
-- 문제 (DECODE에서 표현식과 값의 크다, 작다 비교가 불가능한 부분 해결)
-- SALARY       RESULT
-- <10000       C         월급을 10000으로 나눠서 몫이 0이면(10000보다 낮으면) C        
-- 10000~19999  B         10000으로 나눠서 몫이 1이면(10000~19999) B
-- >=20000      A         이 부분은 생략한다. 그렇게 되면 해당 값들이 NULL이 나올테니, NVL처리를 하여 A를 출력
SELECT salary,              
       NVL(DECODE(TRUNC(salary / 10000), 0, 'C',
                                         1, 'B'), 'A') AS RESULT
  FROM employees;
  

-- 4. 분기 표현식
/*
      CASE
           WHEN 비교조건 THEN 결과값
           WHEN 비교조건 THEN 결과값
           ...
           ELSE 결과값
      END
*/

-- 문제
-- SALARY       RESULT
-- <10000       C             
-- 10000~19999  B         
-- >=20000      A 
SELECT salary,
       CASE
           WHEN salary < 10000 THEN 'C'
           WHEN salary < 20000 THEN 'B'  -- 자바의 else if 처럼 기능한다.
--         WHEN salary >= 10000 AND salary < 20000 THEN 'B'
           ELSE 'A'
       END AS 구분
  FROM employees;
  
-- 문제
-- hire_date를 이용해서 고용개월수를 구하고 다음 기준에 따라 'A', 'B', 'C' 나타내기
-- 고용개월수       RESULT
-- <180             C
-- 180~239          B          경과개월수를 반환하는 MONTH_BETWEEN()을 써야함.
-- >=240            A
SELECT TO_CHAR(hire_date, 'yyyy-mm-dd') AS 고용일,
       TRUNC(MONTHS_BETWEEN(SYSDATE, hire_date)) || '개월' AS 근무개월,
       CASE
           WHEN MONTHS_BETWEEN(SYSDATE, hire_date) < 180 THEN 'C'
           WHEN MONTHS_BETWEEN(SYSDATE, hire_date) < 240 THEN 'B'
           ELSE 'A'
       END AS 구분
  FROM employees;