<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

	<a href="${contextPath}/member">회원 관리</a><br>
	
	<a href="${contextPath}/board">게시판 관리</a><br>
	
	<a href="${contextPath}/product">제품 관리</a><br>
	
	<a href="${contextPath}/reservation">예약 관리</a><br>
	
	<a href="${contextPath}/openapi">일일 박스오피스 순위</a>
	
	
	

</body>
</html>