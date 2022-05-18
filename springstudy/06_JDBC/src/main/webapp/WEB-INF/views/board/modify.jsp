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
<link rel="stylesheet" href="../resources/css/summernote-0.8.18-dist/summernote-lite.css" >
<script src="../resources/js/summernote-0.8.18-dist/summernote-lite.js"></script>
<script src="../resources/js/summernote-0.8.18-dist/lang/summernote-ko-KR.js"></script>

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
		
		// summernote
		$('#content').summernote({
			width: 1000,
			height: 300,
			lang: 'ko-KR',
			placeholder: '내용'
		})

		
	})

</script>

</head>
<body>

	<form id="f" action="${contextPath}/board/modify" method="post">
		<input type="hidden" name="board_no" value="${board.board_no}"><br>
		<input type="text" name="title" id="title" value="${board.title}"><br>
		<textarea name="content" id="content">${board.content}</textarea><br><br>
		<button>수정완료</button>
		<input type="reset" value="다시작성">
		<input type="button" value="목록" onclick="location.href='${contextPath}/board/list'">
	</form>


</body>
</html>