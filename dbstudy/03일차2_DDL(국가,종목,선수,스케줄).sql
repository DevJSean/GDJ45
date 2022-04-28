-- 테이블 삭제
DROP TABLE schedule;
DROP TABLE player;
DROP TABLE event;
DROP TABLE nation;

-- 테이블 생성
CREATE TABLE nation(
    n_code         NUMBER(3) NOT NULL, -- PRIMARY KEY라서 NOT NULL 안해도 되지만 대부분 명시하는 편이다
    n_name         VARCHAR2(30 BYTE) CONSTRAINT n_name_nn NOT NULL,
    n_parti_person NUMBER DEFAULT 0,
    n_parti_event  NUMBER,
    n_prev_rank    NUMBER,
    n_curr_rank    NUMBER
);

CREATE TABLE event(
    e_code       NUMBER NOT NULL,
    e_name       VARCHAR2(30 BYTE) CONSTRAINT e_name_nn NOT NULL,
    e_first_year NUMBER(4),
    e_info       VARCHAR2(100 BYTE)
);

CREATE TABLE player(
    p_code      NUMBER(3) NOT NULL,
    p_name      VARCHAR2(30 BYTE) CONSTRAINT p_name_nn NOT NULL,
    n_code      NUMBER(3) NOT NULL, -- 외래키라서
    e_code      NUMBER NOT NULL,
    p_rank      NUMBER,
    p_age       NUMBER(3)
);

CREATE TABLE schedule(
    n_code       NUMBER(3) NOT NULL,
    e_code       NUMBER NOT NULL,
    s_start_date DATE,
    s_end_date   DATE,
    s_info       VARCHAR2(100)
);

-- 기본키 지정
ALTER TABLE nation ADD CONSTRAINT nation_pk PRIMARY KEY(n_code);
ALTER TABLE event ADD CONSTRAINT event_pk PRIMARY KEY(e_code);
ALTER TABLE player ADD CONSTRAINT player_pk PRIMARY KEY(p_code);
ALTER TABLE schedule ADD CONSTRAINT schedule_pk PRIMARY KEY(n_code, e_code);

--외래키 지정
ALTER TABLE player 
    ADD CONSTRAINT player_nation_fk FOREIGN KEY(n_code)
        REFERENCES nation(n_code);
ALTER TABLE player 
    ADD CONSTRAINT player_event_fk FOREIGN KEY(e_code)
        REFERENCES event(e_code);
ALTER TABLE schedule
    ADD CONSTRAINT schedule_nation_fk FOREIGN KEY(n_code)
        REFERENCES nation(n_code);
ALTER TABLE schedule 
    ADD CONSTRAINT schedule_event_fk FOREIGN KEY(e_code)
        REFERENCES event(e_code);
        
-- 테이블 구조 확인
DESC nation;
DESC event;
DESC player;
DESC schedule;