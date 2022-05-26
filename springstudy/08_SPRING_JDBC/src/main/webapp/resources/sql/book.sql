DROP TABLE BOOK;
CREATE TABLE BOOK
(
    BOOK_NO NUMBER NOT NULL PRIMARY KEY,
    TITLE VARCHAR2(100 BYTE) NOT NULL,
    AUTHOR VARCHAR2(100 BYTE) NOT NULL,
    PRICE NUMBER,
    PUBDATE VARCHAR2(10 BYTE),
    REGIDATE VARCHAR2(10 BYTE)
);

DROP SEQUENCE BOOK_SEQ;
CREATE SEQUENCE BOOK_SEQ NOCACHE;