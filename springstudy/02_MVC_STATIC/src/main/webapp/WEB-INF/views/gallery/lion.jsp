<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

	<h1>어흥</h1>
	<img src="assets/images/Eevee.jpg">
	<img src="/ex02/assets/images/Eevee.jpg">
	<img src="${pageContext.request.contextPath}/assets/images/Eevee.jpg">
	<img src="${contextPath}/assets/images/Eevee.jpg">

</body>
</html>