-- 테이블 삭제
DROP TABLE boards;
DROP TABLE members;

-- MEMBERS 테이블 (회원) 생성
CREATE TABLE members(
    member_no   NUMBER,
    member_id   VARCHAR2(30 BYTE) NOT NULL UNIQUE,
    name        VARCHAR2(30 BYTE) NOT NULL,
    regist_date TIMESTAMP
);
ALTER TABLE members
    ADD CONSTRAINT members_pk PRIMARY KEY(member_no);


-- boards 테이블 (게시판) 생성
CREATE TABLE boards(
    board_no    NUMBER,
    title       VARCHAR2(1000 BYTE) NOT NULL,
    content     VARCHAR2(4000 BYTE),
    member_id   VARCHAR2(30 BYTE) NOT NULL,
    create_date DATE
);

-- 기본키 설정
ALTER TABLE boards
    ADD CONSTRAINT boards_pk PRIMARY KEY(board_no);
    
-- 외래키 설정
-- members             boards
-- member_id(UNIQUE)   member_id(FK)
ALTER TABLE boards 
    ADD CONSTRAINT boards_members_fk FOREIGN KEY(member_id)
        REFERENCES members(member_id);

-- members 테이블의 시퀀스
DROP SEQUENCE members_seq;
CREATE SEQUENCE members_seq 
    START WITH 1   -- 시작값 1
    INCREMENT BY 1 -- 증가값 1
    NOMINVALUE
    NOMAXVALUE 
    NOCACHE
    NOCYCLE;

-- boards 테이블의 시퀀스
DROP SEQUENCE boards_seq;
CREATE SEQUENCE boards_seq 
    START WITH 1   -- 시작값 1
    INCREMENT BY 1 -- 증가값 1
    NOMINVALUE
    NOMAXVALUE 
    NOCACHE
    NOCYCLE;
    
-- 데이터 추가
INSERT INTO members
    (member_no, member_id, name, regist_date)
VALUES 
    (members_seq.NEXTVAL, 'admin', '관리자', SYSTIMESTAMP);
    
INSERT INTO members
    (member_no, member_id, name, regist_date)
VALUES 
    (members_seq.NEXTVAL, 'user', '사용자', SYSTIMESTAMP);
    
-- 데이터 추가
INSERT INTO boards VALUES(boards_seq.NEXTVAL, '제목1', '내용1', 'admin', SYSDATE);
INSERT INTO boards VALUES(boards_seq.NEXTVAL, '제목2', '내용2', 'user', SYSDATE);
INSERT INTO boards VALUES(boards_seq.NEXTVAL, '제목3', '내용3', 'admin', SYSDATE);
INSERT INTO boards VALUES(boards_seq.NEXTVAL, '제목4', '내용4', 'user', SYSDATE);
INSERT INTO boards VALUES(boards_seq.NEXTVAL, '제목5', '내용5', 'admin', SYSDATE);

COMMIT;


/*
    서브쿼리(sub query)
    
    1. 메인쿼리에 포함되는 하위쿼리이다.
    2. 서브쿼리는 괄호()를 이용해서 묶어준다.
    3. SELECT절에서 사용되면 '스칼라 서브쿼리',
       FROM절에서 사용되면 '인라인 뷰',
       WHERE절에서 사용되면 '서브쿼리'라고 부른다.
    4. 종류
       1) 단일 행(SINGLE ROW) 서브쿼리 : 서브쿼리 결과가 1개(=, !=, >, <, >=, <=) 사용
       2) 다중 행(MULTI ROW) 서브쿼리 : 서브쿼리 결과가 여러개, 다중 행 연산(IN, ANY, ALL 등) 사용
       다중행 연산 IN은 단일행에서도 사용 가능하다. (만능키 같은 느낌)
*/

/* FROM 절 인라인 뷰 */
-- 1. FROM 절에서 사용하는 서브쿼리이다.
-- 2. 인라인 뷰는 대부분 테이블 형식을 반환하는 서브쿼리이다.
-- 3. 인라인 뷰에 별명(ALIAS)을 지정하고 사용한다.
-- 4. 인라인 뷰에서 조회한 칼럼만 메인쿼리가 사용할 수 있다.

-- 서브쿼리에서 board_no, title, content, member_id, create_date 칼럼을 사용했으므로
-- 메인쿼리에는 board_no, title, content, member_id, create_date 칼럼만 사용할 수 있다.
SELECT board_no, title, content, member_id, create_date
  FROM (SELECT board_no, title, content, member_id, create_date
          FROM boards);

-- 서브쿼리에 별명을 지정할 수 있다.
SELECT b.board_no, b.title, b.content
  FROM (SELECT board_no, title, content
          FROM boards) b;



-- 가상 칼럼 (PSEUDO COLUMN)
-- 1. 존재하지만 저장되어 있지 않은 칼럼이다.
-- 2. 사용할 수 있으나 일부 제약이 있다.
-- 3. 종류
--    1) ROWID  : 행ID, 해당 행의 물리적 저장 위치
--    2) ROWNUM : 행NUM, 해당 행의 번호

-- ROWID
SELECT ROWID, member_no, member_id
  FROM members;

-- ROWID를 조건으로 사용하기
-- 오라클 최고의 조회 속도
SELECT member_no, member_id
  FROM members
 WHERE ROWID = 'AAAE9xAABAAALDRAAA'; --실제로 사용할 수 없는 방식(대신 INDEX를 사용함)
 
-- ROWNUM
SELECT ROWNUM, board_no, title, content, member_id, create_date
  FROM boards
 WHERE member_id = 'admin';
 
-- ROWNUM 칼럼의 사용방법
-- 1. ROWNUM은 1을 포함하는 범위를 조건으로 사용할 수 있다.
-- 2. ROWNUM은 1을 포함하지 않는 범위를 조건으로 사용할 수 없다.
-- 3. ROWNUM에 별명(ALIAS)를 지정하고 별명을 이용해 1을 포함하지 않는 범위를 조건으로 사용할 수 있다.

SELECT board_no, title
  FROM boards
 WHERE ROWNUM = 1; -- 가능
 
SELECT board_no, title
  FROM boards
 WHERE ROWNUM <= 3; -- 가능

SELECT board_no, title
  FROM boards
 WHERE ROWNUM = 2; -- 불가능


-- ROWNUM에 별명(ALIAS)을 추가하고 사용해서 ROWNUM = 2인 데이터를 조회하기

-- 1) 잘못된 방법
--    실행순서 때문에 안된다
SELECT ROWNUM AS 글번호, board_no, title, content, member_id, create_date
  FROM boards
 WHERE 글번호 = 2;

-- 2) 인라인 뷰를 이용한 해결 방법
--    WHERE절 이전에 ROWNUM의 별명을 주면 해결
SELECT b.글번호, b.board_no, b.title, b.content, b.member_id, b.create_date
  FROM (SELECT ROWNUM AS 글번호, board_no, title, content, member_id, create_date
          FROM boards) b
  WHERE b.글번호 = 2;

-- 1. boards 테이블에서 3번째 게시글을 조회하시오.
SELECT b.rn, b.board_no, b.title, b.content, b.member_id, b.create_date
  FROM (SELECT ROWNUM AS rn, board_no, title, content, member_id, create_date
          FROM boards) b
  WHERE b.rn = 3;

-- 2. boards 테이블에서 2~4번째 게시글을 조회하시오.
SELECT b.rn, b.board_no, b.title, b.content, b.member_id, b.create_date
  FROM (SELECT ROWNUM AS rn, board_no, title, content, member_id, create_date
          FROM boards) b
  WHERE b.rn BETWEEN 2 AND 4;

-- 3. 작성일자(CREATE_DATE) 기준으로 3번째 작성된 게시글을 조회하시오.
-- 작성일자의 오름차순 정렬 이후 ROWNUM이 3인 데이터

-- 1) ROWNUM 칼럼
--      1) 정렬하기 a
--      2) ROWNUM붙이기 b
--      3) 3번째 작성된 게시글 고르고 SELECT하기
SELECT b.rn, b.board_no, b.title, b.content, b.member_id, b.create_date
  FROM (SELECT ROWNUM AS rn, a.board_no, a.title, a.content, a.member_id, a.create_date
          FROM (SELECT board_no, title, content, member_id, create_date
                  FROM boards
                 ORDER BY create_date) a ) b
  WHERE b.rn = 3;

-- 2) ROW_NUMBER() OVER(ORDER BY 칼럼)
SELECT b.rn, b.board_no, b.title, b.content, b.member_id, b.create_date
  FROM (SELECT ROW_NUMBER() OVER(ORDER BY create_date) AS rn, board_no, title, content, member_id, create_date
          FROM boards) b;
          
-- 3) RANK() OVER(ORDER BY 칼럼) : 동일한 순위(행번호)는 같은 번호 부여
SELECT b.rn, b.board_no, b.title, b.content, b.member_id, b.create_date
  FROM (SELECT RANK() OVER(ORDER BY create_date) AS rn, board_no, title, content, member_id, create_date
          FROM boards) b;


-- 4. 작성일자(CREATE_DATE) 기준으로 2~4번째로 작성한 게시글을 조회하시오.
-- 1) ROWNUM 칼럼
SELECT b.rn, b.board_no, b.title, b.content, b.member_id, b.create_date
  FROM (SELECT ROWNUM AS rn, a.board_no, a.title, a.content, a.member_id, a.create_date
          FROM (SELECT board_no, title, content, member_id, create_date
                  FROM boards
                 ORDER BY create_date) a ) b
  WHERE b.rn BETWEEN 2 AND 4;
  
  -- 2) ROW_NUMBER() OVER(ORDER BY 칼럼)
SELECT b.rn, b.board_no, b.title, b.content, b.member_id, b.create_date
  FROM (SELECT ROW_NUMBER() OVER(ORDER BY create_date) AS rn, board_no, title, content, member_id, create_date
          FROM boards) b
 WHERE b.rn BETWEEN 2 AND 4;
 
-- 3) RANK() OVER(ORDER BY 칼럼) : 동일한 순위(행번호)는 같은 번호 부여
SELECT b.rn, b.board_no, b.title, b.content, b.member_id, b.create_date
  FROM (SELECT RANK() OVER(ORDER BY create_date) AS rn, board_no, title, content, member_id, create_date
          FROM boards) b
 WHERE b.rn BETWEEN 2 AND 4;