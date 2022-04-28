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

-- 집계함수 (그룹함수)
-- 1. 통계에서 사용(합계, 평균 등)
-- 2. NULL값을 연산에서 제외한다.
-- 3. 종류
--   1) 합계 : SUM(칼럼)
--   2) 평균 : AVG(칼럼)
--   3) 최대 : MAX(칼럼)
--   4) 최소 : MIN(칼럼)
--   5) 개수 : COUNT(칼럼)

-- 각 칼럼의 합계
SELECT SUM(kor)
     , SUM(eng)
     , SUM(mat)
     , SUM(kor) + SUM(eng) + SUM(mat) AS 전체합 -- 국어, 영어, 수학의 전체 합
--   , SUM(kor, eng, mat): 불가능, 집계함수의 인수는 1개만 가능하다
--   , SUM(kor + eng + mat): 어떤 레코드의 kor+eng+mat 합
  FROM student;

-- NULL은 제외하고 평균을 구한다.
SELECT AVG(kor)
     , AVG(eng)
     , AVG(mat)
  FROM student;

-- NULL인 경우 결시로 가정하고 0점 처리한 뒤 평균을 구한다.
SELECT AVG(NVL(kor, 0))
     , AVG(NVL(eng, 0))
     , AVG(NVL(mat, 0))
  FROM student;
  
-- 개수
-- 국어 시험을 응시한 사람이 몇 명인지 구한다.
SELECT COUNT(kor)
  FROM student;
  
-- 행(ROW)의 개수를 구한다. 인원수 구하기
SELECT COUNT(name) -- NULL값이 없는 칼럼이면 행의 개수를 셀 수 있다
     , COUNT(kor)  -- kor에 NULL이 있으므로 불가능
     , COUNT(eng)  -- eng에 NULL이 있으므로 불가능
     , COUNT(mat)  -- mat에 NULL이 없으므로 가능
  FROM student;

-- 특정 칼럼에 따라 차이가 발생하므로 행(ROW)의 개수를 구할 때는 모든 칼럼을 참조한다.
SELECT COUNT(*)    -- 이 상황에만 * 사용
  FROM student;
