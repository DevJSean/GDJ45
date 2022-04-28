-- 수강신청 데이터베이스 구축


/* 테이블 삭제 */

-- 외래키를 가진 테이블을 먼저 삭제
DROP TABLE lecture;
DROP TABLE enroll;
DROP TABLE student;
-- 외래키가 없는 테이블을 나중에 삭제
DROP TABLE course;
DROP TABLE Professor;

/* 테이블 생성 */

-- 1. PROFESSOR 테이블
CREATE TABLE professor(
    p_no    NUMBER NOT NULL,  -- 기본키
    p_name  VARCHAR2(30 BYTE),
    p_major VARCHAR2(30 BYTE)
);
-- 기본키
ALTER TABLE professor
    ADD CONSTRAINT professor_pk PRIMARY KEY(p_no);

-- 2. course 테이블
CREATE TABLE course(
    c_no   NUMBER NOT NULL,  -- 기본키
    c_name VARCHAR2(30 BYTE),
    c_unit NUMBER(1)
);
-- 기본키
ALTER TABLE course
    ADD CONSTRAINT course_pk PRIMARY KEY(c_no);


-- 3. STUDENT 테이블
CREATE TABLE student(
    s_no       NUMBER NOT NULL,  -- 기본키
    s_name     VARCHAR2(30 BYTE),
    s_address  VARCHAR2(100 BYTE),
    s_grade_no NUMBER(1),
    p_no       NUMBER NOT NULL  -- professor테이블의 p_no칼럼을 참조하는 외래키
);
-- 기본키
ALTER TABLE student
    ADD CONSTRAINT student_pk PRIMARY KEY(s_no);
-- 외래키
ALTER TABLE student 
    ADD CONSTRAINT student_professor_fk FOREIGN KEY(p_no) 
        REFERENCES professor(p_no);


-- 4. enroll 테이블
CREATE TABLE enroll(
    e_no   NUMBER NOT NULL,
    s_no   NUMBER NOT NULL,  -- student테이블의 s_no칼럼을 참조하는 외래키
    c_no   NUMBER NOT NULL,  -- course테이블의 c_no칼럼을 참조하는 외래키
    e_date DATE
);
-- 기본키
ALTER TABLE enroll
    ADD CONSTRAINT enroll_pk PRIMARY KEY(e_no);
-- 외래키
ALTER TABLE enroll 
    ADD CONSTRAINT enroll_student_fk FOREIGN KEY(s_no) 
        REFERENCES student(s_no);
ALTER TABLE enroll 
    ADD CONSTRAINT enroll_course_fk FOREIGN KEY(c_no) 
        REFERENCES course(c_no);


-- 5. lecture 테이블
CREATE TABLE lecture(
    l_no       NUMBER NOT NULL,  -- 기본키
    p_no       NUMBER NOT NULL,  -- professor테이블의 p_no칼럼을 참조하는 외래키
    e_no       NUMBER NOT NULL,  -- enroll테이블의 e_no칼럼을 참조하는 외래키
    l_name     VARCHAR2(30 BYTE),
    l_location VARCHAR2(30 BYTE)
);
-- 기본키
ALTER TABLE lecture
    ADD CONSTRAINT lecture_pk PRIMARY KEY(l_no);
-- 외래키
ALTER TABLE lecture 
    ADD CONSTRAINT lecture_professor_fk FOREIGN KEY(p_no) 
        REFERENCES professor(p_no);
ALTER TABLE lecture 
    ADD CONSTRAINT lecture_enroll_fk FOREIGN KEY(e_no) 
        REFERENCES enroll(e_no);
        
-- 1. PROFESSOR 테이블 데이터 입력
INSERT INTO PROFESSOR(p_no, p_name, p_major)
VALUES (1, '제임스', '전산');
INSERT INTO PROFESSOR VALUES (2, '앨리스', '회계');
INSERT INTO PROFESSOR VALUES (3, '스미스', '영화');

-- 2. COURSE 테이블 데이터 입력
INSERT INTO COURSE VALUES (11, '자바', 3);
INSERT INTO COURSE VALUES (22, '데이터베이스', 3);
INSERT INTO COURSE VALUES (33, '서버프로그램', 3);

-- 3. STUDENT 테이블 데이터 입력
INSERT INTO STUDENT VALUES (10101, '김학생', '서울', 1, 3);
INSERT INTO STUDENT VALUES (10102, '이학생', '경기', 1, 3);
INSERT INTO STUDENT VALUES (10103, '최학생', '인천', 1, 3);

-- 4. ENROLL 테이블 데이터 입력
INSERT INTO ENROLL VALUES (111, 10101, 11, '20-02-25');
INSERT INTO ENROLL VALUES (222, 10101, 22, '20-02-26');
INSERT INTO ENROLL VALUES (333, 10101, 33, '20-02-27');

-- 5. LECTURE 테이블 데이터 입력
INSERT INTO LECTURE VALUES (1111, 1, 111, '자바완전정복', 'A101');
INSERT INTO LECTURE VALUES (2222, 1, 111, '자바완전정복', 'B101');
INSERT INTO LECTURE VALUES (3333, 1, 111, '자바완전정복', 'C101');

-- 6. 변경된 내용을 DB 에 저장한다.
COMMIT;  -- INSERT, UPDATE, DELETE 문은 COMMIT이 필요함.