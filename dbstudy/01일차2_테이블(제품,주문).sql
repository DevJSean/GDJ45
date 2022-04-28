/*
    1. 테이블 작성 방법
    CREATE TABLE 테이블명(칼럼명 타입 [제약조건], 칼럼명 타입 [제약조건], [제약조건] ...);

    2. 타입
        1) 고정 길이 문자열 : CHAR    (주민등록번호, 휴대전화번호 ...)
        2) 가변 길이 문자열 : VARCHAR2(최대크기 설정)
        3) 숫자 : NUMBER (정수, 실수 모두 포함)
        4) 날짜 : DATE, TIMESTAMP
        
    3. 제약조건
        1) NOT NULL : 널 값 불가능
        2) UNIQUE : 중복 불가능
        3) CHECK : 값의 제한 (조건으로 지정, 0~10,000 사이 등..)
        4) PRIMARY KEY : 기본키 (개체무결성 제약조건: UNIQUE & NOT NULL)
        5) FOREIGN KEY : 외래키 (참조무결성 제약조건)
*/

-- 제품 테이블 
-- 테이블 생성 방식 (3번째 방법을 주로 사용)
-- 1. PRIMARY KEY 제약조건의 이름이 없는 방식
CREATE TABLE PRODUCT(
    PRODUCT_NO   NUMBER NOT NULL PRIMARY KEY, -- NOT NULL은 PRIMARY KEY에 포함되니 생략 가능
    PRODUCT_NAME VARCHAR2(30 BYTE),           -- 칼럼 명은 _을 사용하는 snake case 방식을 사용하는데
    PRICE        NUMBER,                      -- DB에서는 어차피 다 대문자로 변환되기 때문에 대문자로 작성한다.
    STOCK        NUMBER                    
); 

-- 2. PRIMARY KEY 제약조건의 이름이 없는 방식
CREATE TABLE PRODUCT(
    PRODUCT_NO   NUMBER,
    PRODUCT_NAME VARCHAR2(30 BYTE),
    PRICE        NUMBER,
    STOCK        NUMBER,
    PRIMARY KEY(PRODUCT_NO) -- 제약 조건을 마지막에 명시하는 방법
);

-- 3. PRIMARY KEY 제약조건의 이름이 PRODUCT_PK로 지정되는 방식
CREATE TABLE PRODUCT(
    PRODUCT_NO   NUMBER,
    PRODUCT_NAME VARCHAR2(30 BYTE),
    PRICE        NUMBER,
    STOCK        NUMBER,
    CONSTRAINTS PRODUCT_PK PRIMARY KEY(PRODUCT_NO) -- 제약조건 이름 설정
);  -- 추후에 제약조건을 찾거나 파악할 때 용이하게 사용된다.
                
-- 주문 테이블
CREATE TABLE ORDERS( -- ORDER라는 키워드가 있어서 이를 방지하기 위해 S를 붙인다
    ORDER_NO    NUMBER PRIMARY KEY,
    ORDER_NAME  VARCHAR2(15 BYTE),
    PRODUCT_NO  NUMBER REFERENCES PRODUCT(PRODUCT_NO), -- 외래키 (같은 칼럼명을 사용)
    ORDER_DATE  DATE
);

CREATE TABLE ORDERS(
    ORDER_NO    NUMBER,
    ORDER_NAME  VARCHAR2(15 BYTE),
    PRODUCT_NO  NUMBER,
    ORDER_DATE  DATE,
    PRIMARY KEY(ORDER_NO),
    FOREIGN KEY(PRODUCT_NO) REFERENCES PRODUCT(PRODUCT_NO)
);

CREATE TABLE ORDERS(
    ORDER_NO    NUMBER,
    ORDER_NAME  VARCHAR2(15 BYTE),
    PRODUCT_NO  NUMBER,
    ORDER_DATE  DATE,
    CONSTRAINTS ORDERS_PK PRIMARY KEY(ORDER_NO),
    CONSTRAINTS ORDERS_PRODUCT_FK FOREIGN KEY(PRODUCT_NO) REFERENCES PRODUCT(PRODUCT_NO)
);              -- 외래키 제약조건 이름 규칙 주의
    
/*
    1. 참조 관계의 테이블에서...
        1) 기본키를 가진 테이블 : 부모 테이블
        2) 외래키를 가진 테이블 : 자식 테이블
    
    2. 참조 관계의 테이블 삭제시 주의할 점
        1) 자식 테이블을 먼저 지워야 한다. (외래키를 가진 테이블을 먼저 지운다)
        2) 부모 테이블을 나중에 지운다.
    
    3. 테이블 삭제
        DROP TABLE 테이블명;
    
*/

-- 외래키를 가진 '주문(ORDERS)테이블'을 먼저 삭제해야 한다.
DROP TABLE ORDERS;

-- 기본키를 가진 '제품(PRODUCT)테이블'을 나중에 삭제
DROP TABLE PRODUCT;

-- 이렇게 OBJECT 객체가 생성된 SCOTT을 지우려면 CASCADE를 사용해야 한다.
-- DROP USER SCOTT CASCADE;
