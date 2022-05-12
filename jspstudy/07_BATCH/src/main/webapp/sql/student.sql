DROP TABLE STUDENT;
CREATE TABLE STUDENT
(
    STU_NO NUMBER PRIMARY KEY,
    NAME  VARCHAR2(20 BYTE),
    KOR  NUMBER(3) CHECK(KOR BETWEEN 0 AND 100),
    ENG  NUMBER(3) CHECK(ENG BETWEEN 0 AND 100),
    MAT  NUMBER(3) CHECK(MAT BETWEEN 0 AND 100),
    AVG NUMBER(5,2),
    GRADE CHAR(1 BYTE)
);

DROP SEQUENCE STUDENT_SEQ;
CREATE SEQUENCE STUDENT_SEQ NOCACHE;

