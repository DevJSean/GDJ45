<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sessionB</title>
</head>
<body>

	<%-- session은 인코딩을 안해도 된다. --%>

	<h3>이름 : <%=session.getAttribute("name")%></h3>
	<h3>이름 : ${name}</h3>
	<h3>이름 : ${sessionScope.name}</h3>
	
	<h3>나이 : <%=session.getAttribute("age")%></h3>
	<h3>나이 : ${age}</h3>
	<h3>나이 : ${sessionScope.age}</h3>


</body>
</html>