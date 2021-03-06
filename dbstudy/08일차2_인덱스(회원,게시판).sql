/*
    인덱스(INDEX)
    
    1. 빠른 조회를 위해서 검색할 때 자주 사용하는 칼럼에 인덱스를 설정한다.
    2. 기본키(PK)나 중복불가(UNIQUE) 제약조건을 지정한 칼럼에는 자동으로 인덱스가 설정된다.
    3. 인덱스를 남용하면 삽입/수정/갱신(INSERT/UPDATE/DELETE) 작업할 때 성능이 떨어진다.
*/

--인덱스 조회
DESC ALL_INDEXES;
DESC DBA_INDEXES;
DESC USER_INDEXES; -- USER : SCOTT

SELECT INDEX_NAME, TABLE_NAME
  FROM USER_INDEXES;
  
SELECT INDEX_NAME, TABLE_NAME
  FROM USER_INDEXES
 WHERE TABLE_NAME = 'MEMBERS'; -- 테이블명 대문자로 해야함
 
-- 인덱스가 설정된 칼럼 조회
DESC USER_IND_COLUMNS;

SELECT INDEX_NAME, TABLE_NAME, COLUMN_NAME
  FROM USER_IND_COLUMNS;
  
SELECT INDEX_NAME, TABLE_NAME, COLUMN_NAME
  FROM USER_IND_COLUMNS
 WHERE TABLE_NAME = 'BOARDS';
 
-- 인덱스 생성
-- boards 테이블의 title 칼럼에 IND_TITLE 인덱스 부여하기
CREATE INDEX IND_TITLE
    ON boards(title);
    
-- 인덱스 삭제
DROP INDEX IND_TITLE;