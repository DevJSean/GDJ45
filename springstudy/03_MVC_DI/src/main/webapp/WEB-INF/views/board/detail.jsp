<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>제목 : ${board1.title}</h1><!-- EL로 필드 값을 꺼내는건 Board에 getter가 있어야 한다. -->
	<h1>내용 : ${board1.content}</h1>
	<hr>
	<h1>제목 : ${board2.title}</h1>
	<h1>내용 : ${board2.content}</h1>


</body>
</html>