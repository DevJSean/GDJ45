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

	$(function(){
		
		// 수정화면으로 이동
		$('#btnChangePage').on('click', function(){
			location.href="${contextPath}/board/changePage"; // session에 게시글 정보를 올려놔서 파라미터 없어도 된다.
		})

		// 삭제
		$('#btnRemove').on('click', function(){
			if(confirm('삭제할까요?')){
				location.href="${contextPath}/board/remove?boardNo=${board.boardNo}"; // session에 있다고 하더라도 삭제는 파라미터 그대로 둔다.
			}
		})
		
		// 목록
		$('#btnList').on('click', function(){
			location.href="${contextPath}/board/list";
		})
		
		// 댓글 개수 + 리스트
		fnReplies();
		
		// 댓글 달기
		fnReplySave();
		
		// 댓글 삭제
		fnReplyRemove();
		
		// 댓글창 초기화
		fnInit();
		
	}) // 페이지 로드 이벤트
	
	// 함수
	function fnReplies(){
		// 댓글 개수 + 리스트
		$.ajax({
			/* 요청 */
			url: '${contextPath}/reply/list', 
			type: 'get',
			data: 'boardNo=${board.boardNo}',
			
			/* 응답 */
			dataType: 'json',
			success: function(obj){ // obj = {"replyCount" : 개수, "replies" : [{댓글정보}, {댓글정보}, ...]}
				$('#replies').empty(); // 초기화
			
				$('#replyCount').text(obj.replyCount); // 개수 달아주기
				
				$.each(obj.replies, function(i, reply){ // 리스트 넣기
					var tr = $('<tr>')
					.append($('<td>').text(reply.writer))
					.append($('<td>').text(reply.content))
					.append($('<td>').text(reply.ip))
					.append($('<td>').text(reply.created))
					if(reply.writer == '${user.id}'){  // 댓글 작성자와 로그인한 사람이 같으면
						$(tr).append($('<td>').html('<span class="removeLink" data-reply_no="' + reply.replyNo + '">x</span>'))
					}
					$(tr).appendTo('#replies');
				})
			},
			error: function(jqXHR){
				
			}
		})
	}
	
	function fnReplySave() {
		$('#btnReplySave').on('click', function(){
			$.ajax({
				/* 요청 */
				url: '${contextPath}/reply/save',
				type: 'post',
				data: $('#f').serialize(),
				
				/* 응답 */
				dataType: 'json',
				success: function(obj) {
					if(obj.res > 0) {
						alert('댓글이 등록되었습니다.');
						fnReplies();
						fnInit();
					}
				},
				error: function(jqXHR) {
					
				}
			})
		})
	}
	
	function fnReplyRemove() {
		//$('.removeLink').on('click', function(){} // 정적 요소 처리 방법
		$('body').on('click', '.removeLink', function(){ // 동적 요소 처리 방법(정적 요소(부모)를 부르고, 이벤트와 function사이에 동적 요소를 넣는다.)	
			if(confirm('삭제할까요?')){
				$.ajax({
					/* 요청 */
					url: '${contextPath}/reply/remove',
					type: 'get',
					data: 'replyNo=' + $(this).data('reply_no'),
						
					/* 응답 */
					dataType: 'json',
					success: function(obj){
						if(obj.res > 0){
							alert('댓글이 삭제되었습니다.');
							fnReplies();
							fnInit();
						}
					},
					error: function(jqXHR){
						
					}
				})
			}
		})
	}
	
	function fnInit(){
		$('#content').val('');
	}

</script>

<style>
	.removeLink:hover {
		cursor: pointer;
		color: red;
	}
</style>

</head>
<body>

	<h3>상세보기</h3>
	
	게시글번호 ${board.boardNo}<br>
	작성자 ${board.writer}<br>
	작성IP ${board.ip}<br>
	작성일 ${board.created}<br>
	수정일 ${board.modified}<br>
	조회수 ${board.hit}<br>
	제목 ${board.title}<br>
	${board.content}<br>
	
	<c:if test="${user.id == board.writer}"> <!-- 로그인한 사람, 이 게시글의 주인만 누를 수 있다. -->
		<input type="button" value="수정" id="btnChangePage">
		<input type="button" value="삭제" id="btnRemove">
	</c:if>
	<input type="button" value="목록" id="btnList">
	
	<hr>
	
	<div>
		댓글 <span id="replyCount"></span>개
	</div>
	
	<form id="f">
		<input type="hidden" name="writer" value="${user.id}">
		<input type="hidden" name="boardNo" value="${board.boardNo}">
		<textarea rows="3" cols="30" name="content" id="content"></textarea>
		<c:if test="${user != null}">
			<input type="button" value="작성완료" id="btnReplySave">
		</c:if>
	</form>
	
	<br><br>
	
	댓글 리스트<br>
	<table>
		<tbody id="replies">
			
		</tbody>
	</table>

</body>
</html>