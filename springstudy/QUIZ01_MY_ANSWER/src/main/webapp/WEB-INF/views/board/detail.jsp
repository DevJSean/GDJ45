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
	// 페이지 로드 이벤트
	$(document).ready(function(){
		
		// 폼의 서브밋 이벤트
		$('#f').on('submit', (event) => {
			
			if($('#title').val() == ''){
				alert('제목은 필수입니다.')
				event.preventDefault(); // 이벤트 취소 : 서브밋 취소
			}
		})
	})

</script>
</head>
<body>

	<h1>게시글 수정화면</h1>
	
	글번호: ${board.no}<br>
	작성자: ${board.writer}<br>
	작성IP: ${board.ip}<br>
	조회수: ${board.hit}<br>
	작성일: ${board.created}<br>
	<form id="f" action="${contextPath}/board/modify" method="post">
		<input type="hidden" name="no" value="${board.no}"><br>
		제목 <input type="text" name="title" id="title" value="${board.title}"><br>
		<textarea style="resize: none;" cols="30" rows="10" name="content" id="content">${board.content}</textarea><br>
		<button>수정</button>
		<input type="button" value="목록" onclick="location.href='${contextPath}/board/list'">
	</form>


</body>
</html>