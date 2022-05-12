<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>제목 : ${board.title}</h1> <!-- EL은 getter가 있어야 동작한다. -->
	<h1>조회수 : ${board.hit}</h1>




</body>
</html>