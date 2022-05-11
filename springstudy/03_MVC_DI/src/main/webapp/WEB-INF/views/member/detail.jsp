<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>제목 : ${member1.id}</h1><!-- EL로 필드 값을 꺼내는건 Member에 getter가 있어야 한다. -->
	<h1>내용 : ${member1.pw}</h1>

	<h1>제목 : ${member2.id}</h1>
	<h1>내용 : ${member2.pw}</h1>
</body>
</html>