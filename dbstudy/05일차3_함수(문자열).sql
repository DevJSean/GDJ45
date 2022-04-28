-- 문자 처리 함수

-- 1. 대소문자 처리
--    1) UPPER   : 대문자로 변환   (CAP)
--    2) LOWER   : 소문자로 변환   (cap)
--    3) INITCAP : 첫글자만 대문자 (Cap)
SELECT UPPER(email)
     , LOWER(email)
     , INITCAP(email)
  FROM employees;
  
  
-- 2. 길이
--    1) LENGTH  : 글자수
--    2) LENGTHB : 바이트수
SELECT email
     , LENGTH(email)
     , LENGTHB(email)
  FROM employees;
  
-- 3. 연결
--    1) ||                                     자바 ||  =  오라클  OR
--    2) CONCAT 함수                            자바  +  =  오라클  ||, CONCAT
--       인수가 2개로 고정됨
--       CONCAT(A, B) => AB
--       CONCAT(A, B, C) => 오류
--       CONCAT(A, CONCAT(B, C)) => ABC
SELECT '대한' || '독립' || '만세'
     , CONCAT('대한', CONCAT('독립', '만세'))  -- 다른 DBMS와의 호환성을 위해 이 방법이 좋다.
  FROM dual;
  
-- 4. 일부 반환
--    SUBSTR(문자열, BEGIN, LENGTH) : BEGIN부터 LENGTH개 반환
--    SUBSTR 함수의 BEGIN 시작은 1이다.
SELECT email
     , SUBSTR(email, 1, 3) -- 1  첫번째 글자부터 3개 반환
  FROM employees;
  
-- 5. 특정 문자열의 위치 반환                           자바는 INDEXOF("A") : 0
--    INSTR(문자열, 찾을문자열) : 위치 시작은 1부터, 없으면 0
SELECT email
     , INSTR(email, 'A') -- 대소문자 구분함
  FROM employees;
  
-- 6. 좌우 문자열 채우기
--    1) LPAD(문자열, 전체폭, 좌측에 채울문자)
--    2) RPAD(문자열, 전체폭, 우측에 채울문자)
SELECT department_id   
     , LPAD(NVL(department_id, 0), 3, '0')  -- department_id 값을 강제로 세자리가 되게끔 만들기     
  FROM employees;                           -- 중간에 NULL 값이 있어서 NVL 함수를 씌웠음. 단 LPAD 안에 넣어야 세자리 수가 나옴
  
-- 7. 공백 제거
--    1) LTRIM : 왼쪽 공백 제거
--    2) RTRIM : 오른쪽 공백 제거
--    3) TRIM : 양쪽 공백 제거
SELECT LTRIM('    ORACLE')
     , RTRIM('ORACLE    ')
     , TRIM('   ORACLE   ')
  FROM dual;