-- HR 계정의 employees 테이블을 SCOTT 계정으로 복사하기
CREATE TABLE employees
    AS (SELECT *
         FROM HR.employees);
-- 기본키는 추가로 생성
ALTER TABLE employees
    ADD CONSTRAINT employees_pk PRIMARY KEY(employee_id);
    
-- 서버메시지 출력을 할 수 있도록 SERVEROUTPUT 값을 ON으로 세팅한다.
-- 디폴트 상태는 SET SERVEROUTPUT OFF 이다.
SET SERVEROUTPUT ON;

-- PL/SQL의 프로그래밍 기본 형식
/*
[DECLARE]
    변수 선언
BEGIN
    작업 수행
END;
*/

-- 'Hello World' 서버메시지 출력하기
-- DBMS_OUTPUT.PUT_LINE('메시지') : '메시지' 출력 후 줄 바꿈을 진행한다.
-- DBMS_OUTPUT.PUT('메시지')      : '메시지'만 출력한다.
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello World');
END;

-- 변수 선언하기1
-- 스칼라 변수(타입을 직접 명시하는 방법)
-- 대입연산자는 '등호(=)'가 아니라 '콜론등호(:=)'이다.
DECLARE
    my_name VARCHAR2(20 BYTE);
    my_age  NUMBER(3);
BEGIN
    my_name := '민경태';
    my_age  := 45;
    DBMS_OUTPUT.PUT_LINE('My name is ' || my_name);
    DBMS_OUTPUT.PUT_LINE('I am ' ||my_age || ' years old.');
END;
    
-- 변수 선언하기2
-- 참조 변수 (기존 테이블의 특정 칼럼과 같은 타입으로 선언)
-- 참조 변수 선언 방식 : 테이블명.칼럼%TYPE

DECLARE
    my_name   employees.last_name%TYPE;
    my_salary employees.salary%TYPE;
BEGIN
    my_name   := '민경태';
    my_salary := 10000;
    DBMS_OUTPUT.PUT_LINE(my_name);
    DBMS_OUTPUT.PUT_LINE(my_salary);
END;
    
-- 참조 타입은 주로 테이블에 저장된 데이터를 변수에 저장할 때 사용
-- 테이블의 데이터를 변수에 저장하는 방법
-- SELECT 칼럼 INTO 변수 : 칼럼의 값을 변수에 저장하는 방식
DECLARE
    var_last_name employees.last_name%TYPE;
    var_salary    employees.salary%TYPE;
BEGIN
    SELECT last_name, salary
      INTO var_last_name, var_salary
      FROM employees
     WHERE employee_id = 100;
    DBMS_OUTPUT.PUT_LINE('LAST_NAME : ' || var_last_name);
    DBMS_OUTPUT.PUT_LINE('SALARY : ' || var_salary);
END;
    
    
-- 변수 선언하기3    
-- 레코드 타입 : 여러 칼럼을 하나의 레코드로 저장할 수 있는 변수의 타입이다.
-- 레코드 타입을 먼저 정의하고, 레코드 타입의 변수를 선언해서 사용한다.
-- DECLARE에서 레코드 타입 정의와 변수 선언을 수행한다.

DECLARE
    -- 레코드 타입 정의
    -- 새로 정의하는 타입의 이름은 임의로 정한다
    -- 여기서는 employee_type
    TYPE employee_type IS RECORD(
        var_employee_id employees.employee_id%TYPE, -- employees 테이블의 employee_id와 같은 타입이라고 선언
        var_first_name employees.first_name%TYPE, 
        var_last_name  employees.last_name%TYPE
    );

    -- 레코드 타입(employee_type)의 변수(var_record) 선언
    var_record employee_type;
    
BEGIN

    -- employee_id가 100인 사원의 정보(employee_id, first_name, last_name)를 var_record)에 저장하기
    SELECT employee_id, first_name, last_name
      INTO var_record
      FROM employees
     WHERE employee_id = 100;
     
    -- var_record에는 employee_id, first_name, last_name이 모두 저장된 상태이다.
    -- 각 항목은 마침표(.)로 호출할 수 있다.
    DBMS_OUTPUT.PUT_LINE('사원번호 ' || var_record.var_employee_id);
    DBMS_OUTPUT.PUT_LINE('이름 ' || var_record.var_first_name);
    DBMS_OUTPUT.PUT_LINE('성 ' || var_record.var_last_name);
    
END;


-- 변수 선언하기 4
-- 행 타입 : 특정 행(ROW) 전체를 저장할 수 있는 타입이다.
-- 행 타입 선언 방식 : 테이블%ROWTYPE

DECLARE
    var_row employees%ROWTYPE; -- var_row : 변수 이름
BEGIN
    SELECT employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id
      INTO var_row
      FROM employees
     WHERE employee_id = 100;
     
    DBMS_OUTPUT.PUT_LINE('이메일 ' || var_row.email);
    DBMS_OUTPUT.PUT_LINE('전화 ' || var_row.phone_number);
END;

-------------------------------IF문---------------------------------------------
/*
       IF 조건식 THEN
          실행문
    ELSIF 조건식 THEN
          실행문
     ELSE  
          실행문
      END IF;
*/

DECLARE
    my_age NUMBER;
BEGIN
    my_age := 45;
      IF my_age >= 20 THEN
         DBMS_OUTPUT.PUT_LINE('성인');    
    ELSE 
         DBMS_OUTPUT.PUT_LINE('미성년자');
     END IF;
END;

-- 점수에 따른 학점 출력하기
DECLARE
    score NUMBER(3);
    grade VARCHAR2(1 BYTE);
BEGIN
          score := 100;
       IF score >= 90 THEN
          grade := 'A';
    ELSIF score >= 80 THEN
          grade := 'B';
    ELSIF score >= 70 THEN
          grade := 'C'; 
    ELSIF score >= 60 THEN
          grade := 'D';
     ELSE 
          grade := 'F';
      END IF;
          DBMS_OUTPUT.PUT_LINE('점수는 ' || score || '점이고 학점은 ' || grade || '이다.');
END;

-- employees 테이블에서 employee_id가 200인 사원의 연봉(salary)을 가져와서
-- 15000 이상이면 '고연봉', 10000 이상이면 '중연봉', 나머지는 저연봉으로 출력하시오.
DECLARE
    var_salary employees.salary%TYPE;
    var_result VARCHAR2(10 BYTE);
BEGIN
   SELECT salary
     INTO var_salary
     FROM employees
    WHERE employee_id = 200;
     
       IF var_salary >= 15000 THEN
          var_result := '고연봉';
    ELSIF var_salary >= 10000 THEN
          var_result := '중연봉';
     ELSE 
          var_result := '저연봉';
      END IF;
          DBMS_OUTPUT.PUT_LINE('연봉은 ' || var_salary || '이고 ' || var_result || '이다.');
END;


-------------------------------- CASE문 ----------------------------------------
/*
    CASE
        WHEN 조건식 THEN
            실행문
        WHEN 조건식 THEN
            실행문
        ELSE
            실행문
    END CASE;
*/
-- 점수에 따른 학점 출력하기
DECLARE
    score NUMBER;
    grade VARCHAR2(1 BYTE);
BEGIN
    score := 75;
    CASE
        WHEN score >= 90 THEN
            grade := 'A';
        WHEN score >= 80 THEN
            grade := 'B';
        WHEN score >= 70 THEN
            grade := 'C';
        WHEN score >= 60 THEN
            grade := 'D';
        ELSE
            grade := 'F';
    END CASE;
    DBMS_OUTPUT.PUT_LINE('점수는 ' || score || '점이고 학점은 ' || grade || '학점입니다.');
END;


--------------------------------- WHILE문 --------------------------------------
/*
    WHILE 조건식 LOOP
          반복실행문
    END LOOP;
*/

-- 1~5
DECLARE
    n NUMBER;
BEGIN
    n := 1;
    WHILE n <= 5 LOOP
        DBMS_OUTPUT.PUT_LINE(n);
        n := n + 1;
    END LOOP;
END;

-- 1~100 사이 모든 정수를 더하시오. (결과 5050)
DECLARE
    n NUMBER;
    total NUMBER;
BEGIN
    n := 1;
    total := 0;
    WHILE n <= 100 LOOP
        total := total + n;
        n := n+1;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('합계 ' || total);
END;

-- employee 테이블에는 사원번호(employee_id)가 100~206인 사원들이 있다.
-- 모든 사원들의 full_name(first_name || 공백 || last_name)을 출력하시오.
DECLARE
    var_employee_id employees.employee_id%TYPE;
    
    TYPE employee_type IS RECORD(
        var_first_name employees.first_name%TYPE,
        var_last_name employees.last_name%TYPE
    );
    var_record employee_type; 
    
BEGIN
    var_employee_id := 100;
    WHILE var_employee_id <= 206 LOOP
        SELECT first_name, last_name
          INTO var_record
          FROM employees
         WHERE employee_id = var_employee_id;
         
        DBMS_OUTPUT.PUT_LINE(var_record.var_first_name || ' ' || var_record.var_last_name); 
        
        var_employee_id := var_employee_id + 1;
    END LOOP;

END;


------------------------------- FOR문 ------------------------------------------
/*
    FOR 변수 IN START..STOP LOOP
        반복실행문
    END LOOP
*/

-- 1~5
DECLARE
    n NUMBER;
BEGIN
    FOR n IN 1..5 LOOP
        DBMS_OUTPUT.PUT_LINE(n);
    END LOOP;
END;

-- 1~10 사이 정수를 아래와 같이 출력하시오.
-- 1 : 홀수
-- 2 : 짝수
-- ...
-- 10 : 짝수
DECLARE
    n NUMBER;
    var_mod NUMBER; --나머지 값을 저장하는 변수
BEGIN
    FOR n IN 1..10 LOOP
        var_mod := MOD(n, 2);
        IF var_mod = 1 THEN
            DBMS_OUTPUT.PUT_LINE(n || ' : 홀수');
        ELSE 
            DBMS_OUTPUT.PUT_LINE(n || '  짝수');
        END IF;
    END LOOP;
END;

-- employees 테이블의 모든 사원들의 연봉(salary)의 총 합계를 출력하시오.
DECLARE
    var_employee_id employees.employee_id%TYPE;
    var_salary      employees.salary%TYPE;
    total NUMBER;
BEGIN
    total := 0;
    FOR var_employee_id IN 100..206 LOOP
        SELECT salary
          INTO var_salary
          FROM employees
         WHERE employee_id = var_employee_id;
        total := total + var_salary;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('전체 연봉 : ' || total);
END;

------------------------------- EXIT문 -----------------------------------------
-- 루프문을 종료시킨다. (자바의 break)
DECLARE
    n NUMBER;
BEGIN
    n := 1;
    WHILE TRUE LOOP -- 무한루프 만들기
        IF n > 5 THEN
            EXIT;   -- 무한루프 종료
        END IF;
        DBMS_OUTPUT.PUT_LINE(n);
        n := n + 1;
        
    END LOOP;
END;

-- employees 테이블의 모든 사원들의 근속일을 모두 더하시오.
-- 근속일의 합이 50000 이상이면 그만 더하고 최종 근속일의 합을 출력하시오.
DECLARE
    var_employee_id employees.employee_id%TYPE;
    var_hire_date   employees.hire_date%TYPE;
    total NUMBER;
BEGIN
    total := 0;
    FOR var_employee_id IN 100..206 LOOP
        IF total >= 50000 THEN
            EXIT;
        END IF;
        SELECT hire_date
          INTO var_hire_date
          FROM employees
         WHERE employee_id = var_employee_id;
        total := total + (SYSDATE - var_hire_date);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('총 근속일 합계 ' || total);
END;


------------------------------- CONTINUE문 -------------------------------------
-- 루프문의 시작부터 다시 시작한다. (자바의 continue)

-- 1~10 사이의 홀수만 출력하기(짝수는 출력에서 제외하기)

DECLARE
    n NUMBER;
BEGIN
    n := 0;
    WHILE n < 10 LOOP -- 0부터 시작이라 <= 가 아닌 < 을 사용해야 함
        n := n + 1;
        IF MOD(n, 2) = 0 THEN -- 짝수면
            CONTINUE;         -- 제외하겠다 WHILE LOOP로 올려보낸다.
        END IF;
        DBMS_OUTPUT.PUT_LINE(n);
    END LOOP;
END;

-- employees 테이블의 사원 중에서 department_id가 80인 사원들을 제외하고
-- 연봉(salary)의 합계를 출력하시오.
DECLARE
    var_employee_id   employees.employee_id%TYPE;
    var_department_id employees.department_id%TYPE;
    var_salary        employees.salary%TYPE;
    total NUMBER;
BEGIN
    total := 0;
    
    FOR var_employee_id IN 100..206 LOOP

        SELECT salary, department_id
          INTO var_salary, var_department_id
          FROM employees
         WHERE employee_id = var_employee_id;
             
        IF var_department_id = 80 THEN
            CONTINUE; -- FOR LOOP로 되돌아간다.
        END IF;
        
        total := total + var_salary;
        
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('연봉합계 : ' || total);
END;

---------------------------- 테이블 타입 ---------------------------------------
-- 변수 선언하기
-- 테이블 타입 : 특정 칼럼의 모든 데이터를 배열에 저장한다.

DECLARE

    -- 칼럼 salary 값을 모두 저장할 수 있는 타입 type_salary
    TYPE type_salary IS TABLE OF employees.salary%TYPE INDEX BY BINARY_INTEGER; -- 인덱스를 BINARY_INTEGER로 사용한다는 뜻

    -- 실제 salary 값을 모두 저장할 배열(salaries)
 -- 변수(배열)이름 타입;
    salaries       type_salary;
    
    -- 인덱스 i
    i BINARY_INTEGER;
    
    -- 행 타입
    var_row employees%ROWTYPE;
    
BEGIN
    -- 배열 salaries에 저장하기
    i := 0;
    FOR var_row IN(SELECT * FROM employees) LOOP -- employees의 모든 정보가 들어오는데, 한 row씩 var_row로 집어넣음
        salaries(i) := var_row.salary;           -- 자바버전 salaries[i] = var_row.salary
        i := i + 1;
    END LOOP;
    
    -- 배열 salaries 요소 출력하기
    i := 0;
    FOR i IN 0..106 LOOP
        DBMS_OUTPUT.PUT_LINE(salaries(i));
    END LOOP;
END;


-- department_id가 80인 부서만 별도로 dept80이라는 테이블에 생성하시오.
-- employees와 같은 구조를 가지는 (데이터는 없는) dept80 테이블 만들기
CREATE TABLE dept80
    AS (SELECT *
          FROM employees
         WHERE 1 = 2);

-- employees 테이블의 전체 데이터를 읽어서 department_id가 80이면 dept80 테이블에 INSERT
DECLARE
    var_row employees%ROWTYPE;
BEGIN
    FOR var_row IN(SELECT * FROM employees WHERE department_id = 80) LOOP
        INSERT INTO dept80 VALUES var_row;
    END LOOP;
    COMMIT;
END;


------------------------------- 예외 처리 --------------------------------------
-- EXCEPTION 구문에서 처리한다.
-- EXCEPTION 구문에서 예외마다 WHEN 처리를 수행한다. (자바의 CATCH)
DECLARE
    var_email employees.email%TYPE;
BEGIN
    SELECT email
      INTO var_email
      FROM employees
     WHERE employee_id = 0;
    DBMS_OUTPUT.PUT_LINE('이메일 ' || var_email);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('조회된 데이터가 없다.');        
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('예외메시지 ' || SQLERRM);
        DBMS_OUTPUT.PUT_LINE('예외코드 ' || SQLCODE);
END;
    


