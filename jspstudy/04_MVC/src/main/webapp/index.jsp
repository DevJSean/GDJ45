<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 
		welcome-file
		jsp 파일 이름(index.jsp), 파일의 위치(webapp)가 중요하다.
		프로젝트를 실행할 시 가장 먼저 나타나는 파일이다.
		
		프로젝트 자체를 실행해야 한다.
	 --%>

	<h1>홈페이지에 오신 걸 환영합니다.</h1>
	
	<%-- 모든 요청은 controller가 받도록 한다 --%>
	<a href="/MVC/input.do">input.jsp로 이동하기</a>


</body>
</html>