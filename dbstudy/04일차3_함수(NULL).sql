-- 함수 학습용 데이터 생성
DROP TABLE student;
CREATE TABLE student(
    name VARCHAR2(20 BYTE),
    kor  NUMBER,
    eng  NUMBER,
    mat  NUMBER
);
INSERT INTO STUDENT(name, kor, eng, mat) VALUES('정숙',100, 100, 100);
INSERT INTO STUDENT(name, kor, eng, mat) VALUES('영수',100, NULL, 100);
INSERT INTO STUDENT(name, kor, eng, mat) VALUES('진영',NULL, 100, 100);
COMMIT;

-- NULL 처리
-- 1. 연산에 NULL이 포함되면 결과는 NULL이다.
-- 2. NULL 값을 다른 값으로 변경하는 것이 일반적이다.

-- 1. NVL 함수   (NULL VALUE임)
--    NVL(칼럼, 칼럼값이 NULL일 때 대신 사용할 값);
SELECT name, kor, eng, mat -- 원래 방식
  FROM student;
  
SELECT NVL(name, '아무개') -- NVL 방식
     , NVL(kor, 0)
     , NVL(eng, 0)
     , NVL(mat, 0)
  FROM student;
  
-- 2. NVL2 함수
--    NVL2(칼럼, 칼럼값이 NULL이 아닐 때 사용할 값, 칼럼값이 NULL일 때 사용할 값)
SELECT NVL(name, '아무개')
     , NVL2(kor, '국어응시', '국어결시')
     , NVL2(eng, '영어응시', '영어결시')
     , NVL2(mat, '수학응시', '수학결시')
  FROM student;