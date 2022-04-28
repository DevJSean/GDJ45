/*
    뷰(VIEW)
    1. 테이블이나 다른 뷰를 이용해 만드는 가상테이블이다.
    2. 데이터가 디스크에 저장되지 않고, 쿼리문만 저장해 둔다.
    3. 복잡한 쿼리를 뷰로 만들어 두고 사용하면 간단하게 쿼리문을 작성할 수 있다.
    4. 뷰를 사용하므로 얻어지는 성능상 이점은 없다.
    5. 보여 주고 싶은 데이터만 뷰로 만들어 보안상 이점을 얻을 수 있다.
*/


-- 1. 'admin'이 작성한 게시글을 이용해서 'admin_boards'이름을 가진 VIEW를 생성하시오.
CREATE VIEW admin_boards
    AS (SELECT board_no, title, content, create_date
          FROM boards
         WHERE member_id = 'admin');
         
-- 2. 생성된 admin_boards를 조회하시오
SELECT board_no, title, content, create_date
  FROM admin_boards;
  
-- 3. board_no, title, content, name, create_date를 이용해서 'view_boards'이름을 가진 view를 생성하시오
CREATE VIEW view_boards
    AS (SELECT b.board_no, b.title, b.content, m.name, b.create_date
          FROM members m INNER JOIN boards b
            ON m.member_id = b.member_id);
        
-- 4. 생성된 view_boards를 조회하시오.
SELECT board_no, title, content, name, create_date
  FROM view_boards;
  
-- 5. 생성된 VIEW의 목록을 조회하시오.
DESC USER_VIEWS;
SELECT VIEW_NAME, -- 뷰 이름 
       TEXT       -- 뷰로 저장해둔 쿼리문
  FROM USER_VIEWS;
  
  
-- . 생성된 모든 VIEW를 삭제하시오.
DROP VIEW admin_boards;
DROP VIEW view_boards;