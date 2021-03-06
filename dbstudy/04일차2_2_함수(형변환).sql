/*
    DUAL 테이블
    
    1. dummy 칼럼에 'X'값을 하나 가지고 있는 테이블이다.
    DESC dual;
    SELECT dummy
      FROM dual;
    2. dual 테이블 자체는 아무 의미가 없다.
    3. 오라클의 SELECT문은 FROM절이 필수이기 때문에, 테이블이 필요없는 단순 조회도 FROM절을 작성해야 한다.
       이 때, DUAL 테이블을 FROM절에 작성한다.
    SELECT 1 + 1 FROM dual;
*/

-- 1. 형 변환 함수

-- 1) 숫자로 변환하기
--  TO_NUMBER('문자열')

SELECT '100', TO_NUMBER('100') -- 문자열 100과 숫자 100
  FROM dual;                   -- 문자열은 셀 안에서 좌측정렬, 숫자는 우측정렬
  
SELECT '1.5', TO_NUMBER('1.5')
  FROM dual;

-- '문자열' 연산 특징
-- 숫자형 문자열은 자동으로 숫자로 변환된다.
SELECT 1 + 1
  FROM dual;

SELECT 1 + '1'  -- SELECT 1 + TO_NUMBER('1')   내부적으로 자동 변환 
  FROM dual;    -- 자바에서는 int + String = int String

SELECT '1' + '1' -- SELECT TO_NUMBER('1') + TO_NUMBER('1')
  FROM dual;     -- 자바에서는 String + String = String String
  
-- 2) 날짜로 변환하기
-- TO_DATE('문자열', [형식])
-- 지정된 형식으로 문자열을 해석해서 날짜로 변환
-- 형식을 생략하면 오라클의 기본 날짜 형식으로 해석

-- 현재 날자와 시간
-- 타입이 DATE 타입인 경우 : SYSDATE
-- 타입이 TIMESTAMP인 경우 : SYSTIMESTAMP

SELECT SYSDATE -- 년/월/일 이외에도 시간 데이터도 들어가 있다
  FROM dual;
  
SELECT SYSTIMESTAMP
  FROM dual;

SELECT TO_DATE('22/02/24', 'yy/mm/dd')
  FROM dual;

SELECT TO_DATE('02/24/22', 'mm/dd/yy')
  FROM dual;
  
-- 주의
-- 날짜 형식을 변경하는 함수가 아니라,
-- 전달된 문자열을 지정된 날짜 형식으로 해석한다
-- 아래 함수는 날짜를 날짜로 바꾸겠다는 의미의 형식이다. 무의미한 내용임
SELECT TO_DATE(SYSDATE, 'yyyy-mm-dd') 
  FROM DUAL; -- yy/mm/dd(기본형, SYSDATE 형태)로 나온다. 잘못 만든 코드임

-- 날짜의 형식 변경은 TO_CHAR 함수로 처리.

-- 3) 문자로 변환하기
-- TO_CHAR(값, [형식])
-- 전달된 값(숫자, 날짜)을 지정한 형식으로 변경함
-- 1234 -> 1,234    1 -> 1.0    22/02/24 -> 2022-02-24
SELECT TO_CHAR(123)             -- '123'
    ,  TO_CHAR(123,   '999999') -- '   123'
    ,  TO_CHAR(123,   '000000') -- '000123'
    ,  TO_CHAR(1234,  '9,999')  -- '1,234'
    ,  TO_CHAR(12345, '9,999')  -- '######' (값보다 형식의 폭이 적다는 의미, 오류)
    ,  TO_CHAR(12345, '99,999') -- '12,345'
    ,  TO_CHAR(3.4,   '9')      -- '3'      (정수로 반올림)
    ,  TO_CHAR(3.5,   '9')      -- '4'      (정수로 반올림)
    ,  TO_CHAR(3.55,  '9.9')    -- '3.6'    (소수 1자리로 반올림)
  FROM DUAL;

SELECT TO_CHAR(SYSDATE)               -- 22/02/24
      ,TO_CHAR(SYSTIMESTAMP)          -- 22/02/24 13:33:19.058000 +09:00
      ,TO_CHAR(SYSDATE, 'yyyy-mm-dd') -- 2022-02-24
      ,TO_CHAR(SYSDATE, 'hh:mi:ss')   -- 01:33:19    시:분:초
  FROM dual;