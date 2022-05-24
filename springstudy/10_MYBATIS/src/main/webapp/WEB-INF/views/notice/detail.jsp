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
	//페이지 로드 이벤트
	$(function() { 
		// 수정하러가기
		// location과 파라미터 noticeNo를 활용
		$('#btnChangePage').on('click', function(){
			location.href='${contextPath}/notice/changePage?noticeNo=${notice.noticeNo}'; // 따옴표 밖에 + ${notice.noticeNo} 해도 된다.
		})
		
		// 삭제
		// 폼의 서브밋을 활용
		$('#btnRemove').on('click', function(){
			if(confirm('삭제할까요?')){
				$('#f').attr('action', '${contextPath}/notice/removeOne');
				$('#f').submit();
			}
		})
		// 목록
		$('#btnList').on('click', function(){
			location.href='${contextPath}/notice/list';
		})
	})
	
</script>

</head>
<body>

	<form id="f"> <!-- method 생략되면 get 방식 -->
	
		<input type="hidden" name="noticeNo" value="${notice.noticeNo}"> <!-- 삭제에서 활용 -->
	
		공지번호 ${notice.noticeNo}<br>
		제목 ${notice.title}<br>
		내용 <br>
		<pre>${notice.content}</pre> <!-- <pre> content의 줄바꿈 다 반영 -->
		조회수 ${notice.hit}<br>
		작성일 ${notice.created}<br>
		수정일 ${notice.lastModified}<br>
	
		<input type="button" value="수정" id="btnChangePage">
		<input type="button" value="삭제" id="btnRemove">
		<input type="button" value="목록" id="btnList">
	
	</form>

</body>
</html>