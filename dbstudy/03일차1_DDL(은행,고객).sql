--테이블 삭제
DROP TABLE customer;
DROP TABLE bank;

-- 테이블 생성
CREATE TABLE bank(
    bank_code VARCHAR2(20 BYTE),
    bank_name VARCHAR2(30 BYTE)
);

-- 기본키 지정
ALTER TABLE bank ADD CONSTRAINT bank_pk PRIMARY KEY(bank_code);

-- 테이블 생성
CREATE TABLE customer(
    no        NUMBER,
    name      VARCHAR2(30 BYTE) NOT NULL,
    phone     VARCHAR2(30 BYTE) UNIQUE,
    age       NUMBER CHECK(age >= 0 AND age <= 100), -- CHECK(age BETWEEN 0 AND 100)
    bank_code VARCHAR2(20 BYTE)
);

-- 기본키 지정
ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY(no);

-- 외래키 지정
ALTER TABLE customer 
    ADD CONSTRAINT customer_bank_fk FOREIGN KEY(bank_code) 
        REFERENCES bank(bank_code);

-- 테이블 정의 변경하기

-- 1. 칼럼 추가
-- bank 테이블에 bank_phone 칼럼을 추가하시오.
ALTER TABLE bank ADD bank_phone VARCHAR2(15 BYTE);

-- customer 테이블에 grade 칼럼을 추가하시오. ('vip', 'gold', 'silver'의 도메인을 가진다)
ALTER TABLE customer ADD grade VARCHAR2(6 BYTE) CHECK(grade = 'VIP' OR grade = 'GOLD' OR grade = 'SILVER');
ALTER TABLE customer ADD grade VARCHAR2(6 BYTE) CHECK(grade IN('VIP', 'GOLD', 'SILVER'));


-- 2. 칼럼 수정
--    bank 테이블의 bank_name 칼럼을 VARCHAR2(15 BYTE)로 수정하시오.
ALTER TABLE bank MODIFY bank_name VARCHAR2(15 BYTE);

-- bank 테이블의 bank_name 칼럼을 NOT NULL로 수정하시오.
ALTER TABLE bank MODIFY bank_name VARCHAR2(15 BYTE) NOT NULL;

-- 3. 칼럼 삭제
-- customer 테이블의 age 칼럼을 삭제
ALTER TABLE customer DROP COLUMN age;

-- 4. 칼럼 이름 변경
-- customer 테이블의 no 칼럼을 customer_no로 변경
ALTER TABLE customer RENAME COLUMN no TO customer_no;

-- 테이블 구조 확인
DESCRIBE bank;
DESCRIB  customer;
DESCRI   customer;
DESCR    customer;
DESC     customer;