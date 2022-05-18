<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="../resources/js/jquery-3.6.0.js"></script>
<script>
	
	$(document).ready(() => {
		$('#btnRemove').on('click', () => {
			
			if(confirm('삭제할까요?')){
				location.href='${contextPath}/board/remove?board_no=${board.board_no}';
				
			}
		})
	})
	
</script>

</head>
<body>

	게시글번호: ${board.board_no}<br>
	제목: ${board.title}<br>
	내용: ${board.content}<br>
	작성자: ${board.writer}<br>
	최초작성일: ${board.created}<br>
	최종수정일: ${board.lastModified}<br>

	<hr>

	<input type="button" value="목록" onclick="location.href='${contextPath}/board/list'">
	<input type="button" value="삭제" id="btnRemove">
	
	<form action="${contextPath}/board/modifyPage" method="post">
		<input type="submit" value="수정하기">
		<input type="hidden" name="board_no" value="${board.board_no}">
		<input type="hidden" name="title" value="${board.title}">
		<input type="hidden" name="content" value="${board.content}">
	</form>

</body>
</html>