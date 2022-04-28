/*
    DQL(Data Query Lanaugage)
    
    1. 데이터 질의어
    2. 특정 테이블의 칼럼을 조회하거나 값을 조회한다.
    3. DB의 변경이 없다.
    4. 형식
        SELECT 칼럼
          FROM 테이블
        [WHERE 조건]
        [GROUP BY 그룹화] 집계함수(그룹함수: SUM,AVG,MAX,MIN,COUNT)와 함께 쓰임
        [ORDER BY 정렬]
*/

-- 1. 사원(employee) 테이블에서 사원명(name)을 조회하시오.
SELECT name
  FROM employee;
  
-- 칼럼 앞에 오너(OWNER)를 명시할 수 있다. * 오너 : 칼럼이 속한 테이블
SELECT employee.name
  FROM employee;
  
-- 테이블에 별명(ALIAS)을 주고, 이를 오너로 명시할 수 있다.
SELECT e.name
  FROM employee e; --employee 테이블의 별명은 e이다.
  
-- 조회할 칼럼에 별명(ALIAS)을 줄 수 있다.
SELECT name AS 사원명 --name칼럼의 별명을 사원명으로 지정
  FROM employee;

-- 2. 사원테이블의 모든 칼럼을 조회하시오.
-- * : 모든 칼럼을 의미한다.
-- 중요 : 실무에서는 *를 절대 사용하지 않는다. (성능 이슈)
SELECT *         
  FROM employee;
  
-- 모든 칼럼이 필요하면 모두 적어야 한다.
SELECT emp_no, name, depart, position, gender, hire_date, salary
  FROM employee;
  
-- 3. 부서(department)테이블의 지역명(location) 칼럼을 조회하시오.
SELECT location
  FROM department;
  
-- DISTINCT : 중복을 제거한다.
SELECT DISTINCT location
  FROM department;
  
-- 4. 사원테이블에서 직급(position)이 '과장'인 사원의 이름(name)을 조회하시오.
SELECT name
  FROM employee
 WHERE position = '과장';
 
-- 5. 사원테이블에서 급여(salary)가 5000000 이상인 사원의 이름(name)과 직급(position)을 조회하시오
SELECT name AS 이름, position AS 직급
  FROM employee
 WHERE salary >= 5000000;
 
-- 6. 사원테이블에서 급여(salary)가 2000000~4000000 사이인 사원의 이름(name)과 급여(salary)를 조회하시오.
SELECT name, salary
  FROM employee
 WHERE salary BETWEEN 2000000 AND 4000000; -- BETWEEN 자주 사용(추천)
 
SELECT name, salary
  FROM employee
 WHERE salary <= 2000000 AND salary >= 4000000;
 
-- 7. 사원테이블에서 고용일(hire_date)이 '90/01/01'~'99/12/31' 사이인 사원의 이름(name)과 고용일(hire_date)을 조회하시오.
SELECT name, hire_date
  FROM employee
 WHERE hire_date BETWEEN '90/01/01' AND '99/12/31'; 
 
SELECT name, hire_date   -- 입력된 형식이 일정하지 않을 때 사용하는 방법
  FROM employee
 WHERE TO_DATE(hire_date, 'yy/mm/dd') BETWEEN TO_DATE('90/01/01', 'yy/mm/dd') AND TO_DATE('99/12/31', 'yy/mm/dd');

-- 8. 사원테이블에서 직급(position)이 '과장', '부장'인 사원의 이름(name)과 부서번호(depart)를 조회하시오
SELECT name, depart
  FROM employee
 WHERE position IN('과장', '부장'); -- IN 연산이 OR임. (추천)
 
SELECT name, depart
  FROM employee
 WHERE position = '과장' OR position = '부장';

-- 9. 부서테이블에서 지역(location)이 '대구'인 지역의 부서명(dept_name)을 조회하시오.
SELECT dept_name
  FROM department
 WHERE location IN('대구'); -- 조건이 하나일 때도 IN을 사용한다(확장 대비)
 
SELECT dept_name
  FROM department
 WHERE location  = '대구';

-- 10. 사원테이블에서 성별(gender)이 NULL이 아닌 사원의 이름(name)을 조회하시오.
SELECT name
  FROM employee
 WHERE gender IS NOT NULL;
 
-- 11. 부서테이블에서 부서명(dept_name)에 '무'가 포함된 부서의 모든 칼럼을 조회하시오
-- 만능문자 : &, _
-- 문자 데이터를 대상으로 만능문자를 사용한다.
SELECT dept_no, dept_name, location
  FROM department
 WHERE dept_name LIKE '%무%';

-- 12. 사원테이블에서 사원명(name)이 '김'으로 시작하지 않는 사원의 사원명(name)을 조회하시오
SELECT name
  FROM employee
 WHERE name NOT LIKE '김%';
 
-- 13. 사원테이블에서 급여(salary)가 높은 사원부터 낮은 사원순(내림차순)으로 사원명(name)과 급여(salary)를 조회하시오
SELECT name, salary
  FROM employee
 ORDER BY salary DESC;
 
-- 14. 사원테이블의 모든 칼럼을 사원명(name)의 가나다순(오름차순)으로 조회하시오.
SELECT emp_no, name, depart, position, gender, hire_date, salary
  FROM employee
 ORDER BY name ASC;
 
-- 15. 사원테이블의 모든 칼럼을 부서(depart)별로 오름차순 정렬하되, 같은 부서내에서는 고용일(hire_date)의 오름차순으로 정렬하여 조회하시오
SELECT emp_no, name, depart, position, gender, hire_date, salary
  FROM employee
 ORDER BY depart ASC, hire_date ASC; 
--ORDER BY depart,    hire_date; ASC는 생략 가능하다.