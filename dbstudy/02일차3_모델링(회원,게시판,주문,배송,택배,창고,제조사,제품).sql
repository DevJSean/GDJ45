-- 테이블 삭제
-- 외래키가 있는 테이블 삭제
DROP TABLE delivery;
DROP TABLE notice_board;
DROP TABLE product;
DROP TABLE orders;

-- 외래키가 없는 테이블 삭제
DROP TABLE member;
DROP TABLE shipping_company;
DROP TABLE manufacturer;
DROP TABLE warehouse;

--------------------------------------------------------------------------------
-- 테이블 생성
-- member(회원) 테이블
CREATE TABLE member (
    id              VARCHAR2(30 BYTE) NOT NULL,
    password        VARCHAR2(30 BYTE) NOT NULL,
    member_name     VARCHAR2(10 BYTE) NOT NULL,
    member_phone    VARCHAR2(15 BYTE) NOT NULL,
    member_address  VARCHAR2(50 BYTE) NOT NULL
);
-- 기본키
ALTER TABLE member ADD CONSTRAINT member_pk PRIMARY KEY ( id );
-- UNIQUE 지정
ALTER TABLE member ADD CONSTRAINT member_member_phone_un UNIQUE ( member_phone );

--------------------------------------------------------------------------------
-- notice_board(게시판) 테이블
CREATE TABLE notice_board (
    notice_board_no  NUMBER NOT NULL,
    id               VARCHAR2(30 BYTE) NOT NULL,
    notice_date      DATE,
    post             VARCHAR2(2000 BYTE)
);
-- 기본키
ALTER TABLE notice_board ADD CONSTRAINT notice_board_pk PRIMARY KEY ( notice_board_no );
-- 외래키
ALTER TABLE notice_board
    ADD CONSTRAINT notice_board_member_fk FOREIGN KEY ( id )
        REFERENCES member ( id );

--------------------------------------------------------------------------------
-- shipping_company(택배업체) 테이블
CREATE TABLE shipping_company (
    shipping_company_code     NUMBER NOT NULL,
    shipping_company_name     VARCHAR2(30 BYTE),
    shipping_company_phone    VARCHAR2(15 BYTE),
    shipping_company_address  VARCHAR2(30 BYTE)
);
-- 기본키
ALTER TABLE shipping_company ADD CONSTRAINT shipping_company_pk PRIMARY KEY ( shipping_company_code );

--------------------------------------------------------------------------------
-- delivery(배송) 테이블
CREATE TABLE delivery (
    delivery_no            NUMBER NOT NULL,
    shipping_company_code  NUMBER NOT NULL,
    delivery_date          DATE,
    delivery_price         VARCHAR2(30 BYTE)
);
-- 기본키
ALTER TABLE delivery ADD CONSTRAINT delivery_pk PRIMARY KEY ( delivery_no );
-- 외래키
ALTER TABLE delivery
    ADD CONSTRAINT delivery_shipping_company_fk FOREIGN KEY ( shipping_company_code )
        REFERENCES shipping_company ( shipping_company_code );

--------------------------------------------------------------------------------
-- orders(주문) 테이블
CREATE TABLE orders (
    orders_no     NUMBER NOT NULL,
    id           VARCHAR2(30 BYTE) NOT NULL,
    delivery_no  NUMBER NOT NULL,
    destination  VARCHAR2(80 BYTE) NOT NULL,
    orders_date   DATE
);
-- 기본키
ALTER TABLE orders ADD CONSTRAINT orders_pk PRIMARY KEY ( orders_no );
-- 외래키
ALTER TABLE orders
    ADD CONSTRAINT orders_delivery_fk FOREIGN KEY ( delivery_no )
        REFERENCES delivery ( delivery_no );
ALTER TABLE orders
    ADD CONSTRAINT orders_member_fk FOREIGN KEY ( id )
        REFERENCES member ( id );

--------------------------------------------------------------------------------
-- manufacturer(제조사) 테이블
CREATE TABLE manufacturer (
    manufacturer_no       NUMBER NOT NULL,
    manufacturer_name     VARCHAR2(30 BYTE),
    manufacturer_address  VARCHAR2(50 BYTE),
    manufacturer_phone    VARCHAR2(15 BYTE)
);
-- 기본키
ALTER TABLE manufacturer ADD CONSTRAINT manufacturer_pk PRIMARY KEY ( manufacturer_no );

--------------------------------------------------------------------------------
-- warehouse(창고) 테이블
CREATE TABLE warehouse (
    warehouse_no        NUMBER NOT NULL,
    warehouse_name      VARCHAR2(30 BYTE),
    warehouse_capacity  VARCHAR2(30 BYTE),
    warehouse_phone     VARCHAR2(15 BYTE)
);
-- 기본키
ALTER TABLE warehouse ADD CONSTRAINT warehouse_pk PRIMARY KEY ( warehouse_no );

--------------------------------------------------------------------------------
-- product(제품) 테이블
CREATE TABLE product (
    product_no       NUMBER NOT NULL,
    orders_no         NUMBER NOT NULL,
    warehouse_no     NUMBER NOT NULL,
    manufacturer_no  NUMBER NOT NULL,
    product_price    VARCHAR2(30 BYTE) NOT NULL,
    product_amount   NUMBER NOT NULL
);
-- 기본키
ALTER TABLE product ADD CONSTRAINT product_pk PRIMARY KEY ( product_no );
-- 외래키
ALTER TABLE product
    ADD CONSTRAINT product_manufacturer_fk FOREIGN KEY ( manufacturer_no )
        REFERENCES manufacturer ( manufacturer_no );
ALTER TABLE product
    ADD CONSTRAINT product_orders_fk FOREIGN KEY ( orders_no )
        REFERENCES orders ( orders_no );
ALTER TABLE product
    ADD CONSTRAINT product_warehouse_fk FOREIGN KEY ( warehouse_no )
        REFERENCES warehouse ( warehouse_no );

--------------------------------------------------------------------------------
-- 구조확인
DESC delivery;
DESC notice_board;
DESC product;
DESC orders;
DESC member;
DESC shipping_company;
DESC manufacturer;
DESC warehouse;