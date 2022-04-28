-- 테이블 삭제
DROP TABLE proceeding;
DROP TABLE project;
DROP TABLE employee;
DROP TABLE department;

-- 외래키 제약조건을 무시하고 테이블 삭제
DROP TABLE department CASCADE CONSTRAINTS;
DROP TABLE employee CASCADE CONSTRAINTS;
DROP TABLE project CASCADE CONSTRAINTS;
DROP TABLE proceeding CASCADE CONSTRAINTS;

-- 테이블 생성
CREATE TABLE department (
    dept_no        VARCHAR2(15 BYTE) NOT NULL,
    dept_name      VARCHAR2(30 BYTE),
    dept_location  VARCHAR2(50 BYTE)
);
CREATE TABLE employee (
    emp_no     NUMBER NOT NULL,
    dept_no    VARCHAR2(15 BYTE) NOT NULL,
    position   CHAR(10 BYTE),
    name       VARCHAR2(15 BYTE),
    hire_date  DATE,
    salary     NUMBER
);
CREATE TABLE proceeding (
    pcd_no  NUMBER NOT NULL,
    emp_no  NUMBER NOT NULL,
    pjt_no  NUMBER NOT NULL
);
CREATE TABLE project (
    pjt_no      NUMBER NOT NULL,
    pjt_name    VARCHAR2(30 BYTE),
    begin_date  DATE,
    end_date    DATE
);

-- 테이블 수정
-- PK 등록
ALTER TABLE department ADD CONSTRAINT department_pk PRIMARY KEY ( dept_no );
ALTER TABLE employee ADD CONSTRAINT employee_pk PRIMARY KEY ( emp_no );
ALTER TABLE proceeding ADD CONSTRAINT proceeding_pk PRIMARY KEY ( pcd_no );
ALTER TABLE project ADD CONSTRAINT project_pk PRIMARY KEY ( pjt_no );

-- FK 등록
ALTER TABLE employee
    ADD CONSTRAINT employee_department_fk FOREIGN KEY ( dept_no )
        REFERENCES department ( dept_no );
ALTER TABLE proceeding
    ADD CONSTRAINT proceeding_employee_fk FOREIGN KEY ( emp_no )
        REFERENCES employee ( emp_no );
ALTER TABLE proceeding
    ADD CONSTRAINT proceeding_project_fk FOREIGN KEY ( pjt_no )
        REFERENCES project ( pjt_no );