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
			
			if($('#writer').val() == '' || $('#title').val() == ''){
				alert('작성자와 제목은 필수입니다.')
				event.preventDefault(); // 이벤트 취소 : 서브밋 취소
			}
		})
		
	})

</script>

</head>
<body>

	<h1>게시글 작성 화면</h1>
	
	<form id="f" action="${contextPath}/board/add" method="post">
		<label for="writer">
			작성자<input type="text" name="writer" id="writer" autofocus><br>
		</label>
		<label for="title">
			제목<input type="text" name="title" id="title"><br>
		</label>
		<label for="content">
			내용<br><textarea name="content" id="content"></textarea><br>
		</label>
		<button>작성</button>
		<input type="reset" value="다시작성">
		<input type="button" value="목록" onclick="location.href='${contextPath}/board/list'">
	</form>
	

</body>
</html>