-- CREATE TABLE과 서브쿼리
-- 1. 서브쿼리 결과 집합을 새로운 테이블로 생성할 수 있다.
-- 2. 서브쿼리 결과 집합이 없는 경우(행(ROW)이 없는 경우)에도 새로운 테이블을 생성할 수 있다.
--    이 경우 새로운 테이블에는 칼럼만 존재한다.
-- 3. NOT NULL 제약조건을 제외한 제약조건은 복사되지 않는다.

-- 1. member_no가 1인 데이터만 member2 테이블로 복사
CREATE TABLE members2
    AS (SELECT member_no, member_id, name, regist_date
          FROM members
         WHERE member_no = 1);
-- 기본키는 별도로 지정
ALTER TABLE members2
    ADD CONSTRAINT members2_pk PRIMARY KEY(member_no);
    
-- 2. members 테이블의 복사(행은 복사하지 않기)
CREATE TABLE members3
    AS (SELECT member_no, member_id, name, regist_date
          FROM members
         WHERE 2 = 1);
-- 기본키는 별도로 지정
ALTER TABLE members3
    ADD CONSTRAINT members3_pk PRIMARY KEY(member_no);
    
-- 3. boards 테이블의 전체 데이터를 boards2 테이블로 복사
CREATE TABLE boards2
    AS (SELECT board_no, title, content, member_id, create_date
          FROM boards);
-- 기본키는 별도로 지정
ALTER TABLE boards2
    ADD CONSTRAINT boards2_pk PRIMARY KEY(board_no);
    
-- INSERT와 서브쿼리
-- VALUES절에 서브쿼리를 사용할 수 있다.
INSERT INTO members2(member_no, member_id, name, regist_date)
(SELECT member_no, member_id, name, regist_date
   FROM members
  WHERE member_no = 2);
  
-- UPDATE와 서브쿼리
-- SET절에서 서브쿼리를 사용할 수 있다.
-- board_no가 3인 행을 5인 행과 같은 내용으로 수정
UPDATE boards2
   SET (title, content) = (SELECT title, content
                             FROM boards2
                            WHERE board_no = 5)
 WHERE board_no = 3;
 
-- DELETE와 서브쿼리
-- WHERE절에서 서브쿼리를 사용할 수 있다.
-- board_no가 5인 행의 title과 content와 내용이 같은 행을 삭제
DELETE
  FROM boards2
 WHERE (title, content) = (SELECT title, content
                             FROM boards2
                            WHERE board_no = 5);

COMMIT;