/*
    함수(FUNCTION)
    
    자바의 메소드와 같다
    1. 특정 결과 값을 반환한다.
    2. 값을 반환할 때 RETURN을 사용한다.
    3. 입력 파라미터를 사용할 수 있다.
    4. 함수 호출 결과를 처리할 수 있도록 호출해야 한다.
    5. 형식
        CREATE [OR REPLACE] FUNCTION 함수명(매개변수)
        RETURN 반환타입
        IS -- AS도 가능
            변수선언
        BEGIN
            RETURN 작업수행
        [EXCEPTION]
            예외처리
        END 함수명;
*/

-- 함수 FUNC1 정의
CREATE OR REPLACE FUNCTION func1
RETURN VARCHAR2 -- 반환타입
IS
BEGIN
    RETURN 'Hello Function'; -- 반환값이 Hello Function
END func1;

-- 함수 FUNC1 호출 (함수는 보통 쿼리문의 일부로 포함시켜서 호출한다)
SELECT func1()
  FROM dual;
  
  
-- 함수 FUNC2 정의
CREATE OR REPLACE FUNCTION func2
(
    var_salary NUMBER
)
RETURN VARCHAR2
IS
    var_result VARCHAR2(10 BYTE);
BEGIN
    IF var_salary >= 18000 THEN
       var_result := '고연봉';
    ELSIF var_salary >= 10000 THEN
          var_result := '중연봉';
    ELSE
         var_result := '저연봉';
    END IF;
    RETURN var_result;
END func2;

-- 함수 func2 호출
SELECT employee_id, first_name, salary, func2(salary)
  FROM employees;
  
  
  
-- 함수 func3 정의
CREATE OR REPLACE FUNCTION func3
(
    var_start NUMBER,  -- 시작
    var_end   NUMBER,  -- 종료
    var_mod   NUMBER   -- 나머지
)
RETURN NUMBER
IS
    n NUMBER;
    total NUMBER;
BEGIN
    total := 0;
    FOR n IN var_start..var_end LOOP
        IF MOD(n, 2) = var_mod THEN
            total := total + n;
        END IF;
    END LOOP;
    RETURN total;
END func3;

-- 함수 func3 호출
SELECT func3(1, 100, 0) -- 1부터 100까지 모든 짝수의 합계를 구한다.
  FROM DUAL;
  
SELECT func3(1, 100, 1) -- 1부터 100까지 모든 홀수의 합계를 구한다.
  FROM DUAL;
