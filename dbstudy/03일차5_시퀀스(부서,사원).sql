-- 시퀀스

-- 테이블 삭제
DROP TABLE employee;
DROP TABLE department;

-- 테이블 생성
CREATE TABLE department(
    dept_no   NUMBER NOT NULL,
    dept_name VARCHAR2(15 BYTE) NOT NULL,
    location  VARCHAR2(15 BYTE) NOT NULL
);

CREATE TABLE employee(
    emp_no    NUMBER NOT NULL,
    name      VARCHAR2(20 BYTE) NOT NULL,
    depart    NUMBER,
    position  VARCHAR2(20 BYTE),
    gender    CHAR(2),
    hire_date DATE,
    salary    NUMBER
);

-- 기본키 생성
ALTER TABLE department ADD CONSTRAINT department_pk PRIMARY KEY(dept_no);
ALTER TABLE employee ADD CONSTRAINT employee_pk PRIMARY KEY(emp_no);

-- 외래키 생성
ALTER TABLE employee 
    ADD CONSTRAINT employee_department_fk FOREIGN KEY(depart)
        REFERENCES department(dept_no);

/*
    시퀀스
    
    1. 일련'번호'를 생성하는 데이터베이스 객체이다.
    2. 기본키에서 주로 사용된다.
    3. 자동으로 증가하는 번호를 생성한다.
    4. nextval를 이용하면 다음에 생성될 번호를 알 수 있다. (주로 사용)
    5. currval를 이용하면 현재 생성된 번호를 알 수 있다.
*/

-- 시퀀스 생성하기
CREATE SEQUENCE department_seq
    START WITH 1   -- 시작값 1
    INCREMENT BY 1 -- 증가값 1
    MINVALUE 1     -- 최소값 1(시작값이 1이니까 어차피 최소값이 1임)
    MAXVALUE 100;  -- 최대값 100(100번 까지만 번호가 만들어진다)
        
-- 시퀀스 이용해서 번호 삽입하기        
-- department 삽입
INSERT INTO department VALUES(department_seq.NEXTVAL, '영업부', '대구');
INSERT INTO department VALUES(department_seq.NEXTVAL, '인사부', '서울');
INSERT INTO department VALUES(department_seq.NEXTVAL, '총무부', '대구');
INSERT INTO department VALUES(department_seq.NEXTVAL, '기획부', '서울');

-- 시퀀스 생성하기
CREATE SEQUENCE employee_seq
    START WITH 1001  -- 시작값 1001
    INCREMENT BY 1   -- 증가값 1
    NOMINVALUE       -- 최소값 없음
    NOMAXVALUE       -- 최대값 없음
    NOCACHE          -- 메모리 캐시 사용 안 함(추천)
    NOCYCLE;         -- 번호 순환 없음 (NOMAXVALUE 때문에 어차피 순환 안하긴 함)

-- employee 삽입
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (employee_seq.NEXTVAL, '구창민', 1, '과장', 'M', '95-05-01', 5000000);
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (employee_seq.NEXTVAL, '김민서', 1, '사원', 'M', '17-09-01', 2500000);    
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (employee_seq.NEXTVAL, '이은영', 2, '부장', 'F', '90-09-01', 5500000);
INSERT INTO employee(emp_no, name, depart, position, gender, hire_date, salary)
VALUES (employee_seq.NEXTVAL, '한성일', 1, '과장', 'M', '93-04-01', 5000000);

COMMIT;