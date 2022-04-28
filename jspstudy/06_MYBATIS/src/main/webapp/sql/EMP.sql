DROP TABLE EMP;
CREATE TABLE EMP
(
  NO NUMBER PRIMARY KEY,
  NAME VARCHAR2(20 BYTE),
  DEPT VARCHAR2(20 BYTE),
  SALARY NUMBER,
  HIRED VARCHAR2(10 BYTE)
);

DROP SEQUENCE EMP_SEQ;
CREATE SEQUENCE EMP_SEQ NOCACHE;

INSERT INTO EMP(NO, NAME, DEPT, SALARY, HIRED)
     VALUES(EMP_SEQ.NEXTVAL, 'RM', '총무', 5000, '2022-04-20');
COMMIT;