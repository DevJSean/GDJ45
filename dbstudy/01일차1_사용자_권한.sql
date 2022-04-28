/*
- 접속
CONN
CONN SCOTT
CONN SCOTT/1111

- 스키마(사용자) 생성
CREATE USER 사용자명 IDENTIFIED BY 비밀번호;

- 권한 부여
GRANT 권한 TO 사용자명;
* 권한 : CONNECTION, RESOURCE, DBA

- 스키마(사용자) 삭제(저장된 DB객체가 없는 경우)
DROP USER 사용자명;

- 스키마(사용자) 삭제(저장된 DB객체가 있는 경우)
DROP USER 사용자명 CASCADE;

- 권한 해제
REVOKE 권한 FROM 사용자명;

*/