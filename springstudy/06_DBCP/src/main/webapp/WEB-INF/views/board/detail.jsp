<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	게시글번호: ${board.board_no}<br>
	제목: ${board.title}<br>
	내용: ${board.content}<br>
	작성자: ${board.writer}<br>
	최초작성일: ${board.created}<br>
	최종수정일: ${board.lastModified}<br>

</body>
</html>