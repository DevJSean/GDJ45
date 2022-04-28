-- 교육용 HR계정의 lock을 풀고 새로 비밀번호를 부여한다

ALTER USER hr ACCOUNT UNLOCK;

ALTER USER hr IDENTIFIED BY 1111;