DROP TABLE product;
CREATE TABLE product(

	no NUMBER,
	name VARCHAR2(20 BYTE),
	price NUMBER

);

ALTER TABLE product 
	ADD CONSTRAINT product_pk PRIMARY KEY(no);
	
DROP SEQUENCE product_seq;
CREATE SEQUENCE product_seq NOCACHE;
