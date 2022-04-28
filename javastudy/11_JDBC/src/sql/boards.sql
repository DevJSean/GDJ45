-- 테이블 삭제
DROP TABLE boards;

-- 테이블 생성
CREATE TABLE boards(
	no      NUMBER,
	title   VARCHAR2(1000 BYTE) NOT NULL,
	hit     NUMBER,
	created DATE
);

-- 기본키
ALTER TABLE boards 
	ADD CONSTRAINT boards_pk PRIMARY KEY(no);
	
-- 시퀀스
DROP SEQUENCE boards_seq;
CREATE SEQUENCE boards_seq NOCACHE;
