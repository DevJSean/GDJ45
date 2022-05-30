<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

	$(function(){
		
		$('.reply_link').on('click', function(){
			$('.reply_form').addClass('blind');
	    	$(this).parent().parent().next().removeClass('blind');
		})
	})

</script>

<style>
	* {
		box-sizing: border-box;
	}
	.unlink, .link {
		display: inline-block;  /* 같은 줄에 둘 수 있고, width, height 등 크기 지정 속성을 지정할 수 있다. */
		padding: 10px;
		margin: 5px;
		border: 1px solid white;
		text-align: center;
		text-decoration: none;  /* 링크 밑줄 없애기 */
		color: gray;
	}
	.link:hover {
		border: 1px solid orange;
		color: limegreen;
	}
	table {
		border-collapse: collapse;
	}
	td:nth-of-type(1) { width: 80px; }
	td:nth-of-type(2) { width: 160px; }
	td:nth-of-type(3) { width: 240px; }
	td:nth-of-type(4) { width: 240px; }
	td:nth-of-type(5) { width: 120px; }
	td {
		padding: 5px;
		border-top: 1px solid silver;
		border-bottom: 1px solid silver;
		text-align: center;
	}
	tfoot td {
		border-left: 0;
		border-right: 0;
		border-bottom: 0;
	}
	
	.blind {
		display: none;
	}
</style>

</head>
<body>

	<h3>게시판</h3>

	<a href="${contextPath}/freeBoard/saveFreeBoard">새글작성</a>

	<hr>
	
	<table>
		<caption>${totalRecord}개 게시글</caption>
		<thead>
			<tr>
				<td>번호</td>
				<td>작성자</td>
				<td>내용</td>
				<td>작성일</td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty freeBoards}">
				<tr>
					<td colspan="5">첫 게시글을 작성해 주세요.</td>
				</tr>
			</c:if>
			<c:if test="${not empty freeBoards}">
				<c:forEach items="${freeBoards}" var="fb">
					<c:if test="${fb.state == -1}">
						<tr>
							<td colspan="5">삭제된 게시글입니다.</td>
						</tr>
					</c:if>
					<c:if test="${fb.state == 1}">
						<tr>
							<td>${fb.rowNum}</td>
							<td>${fb.writer}</td>
							<td>
								<!-- Depth만큼 들여쓰기(Depth 1 == Space 2)-->
								<c:forEach begin="1" end="${fb.depth}" step="1">&nbsp;&nbsp;</c:forEach><!-- begin="1" 1부터, end까지 반복 -->
								<!-- 댓글은 re 표시 -->
								<c:if test="${fb.depth > 0}"><i class="fa-brands fa-replyd"></i></c:if> 
								<!-- 댓글 내용, 20자까지 줄여서 표시해봤다.-->
								<c:if test="${fb.content.length() > 20}">
									${fb.content.substring(0, 20)} 
								</c:if>
								<c:if test="${fb.content.length() le 20}">
									${fb.content} 
								</c:if>
								<!-- 답글 달기 (if 있으면 1단 댓글만 허용, if 없으면 다단 댓글 허용)-->
								<c:if test="${fb.depth eq 0}">
									<a class="reply_link">답글</a>
								</c:if>
							</td>
							<td>${fb.created}</td>
							<td>내가 쓴 게시글만 삭제버튼 가능하게</td>
						</tr>
						<!-- class 속성값 reply_form을 가지고 있으면 안 보인다. -->
						<tr class="reply_form blind">
							<td colspan="5">
								<form action="${contextPath}/freeBoard/saveReply" method="post">
									<input type="text" name="writer" placeholder="작성자" size="4"> <!-- 원래는 작성자 readonly만 되어야 한다. -->
									<input type="text" name="content" placeholder="내용" size="40">
									<!-- 원글의 Depth, GroupNo, GroupOrd -->
									<input type="hidden" name="depth" value="${fb.depth}">
									<input type="hidden" name="groupNo" value="${fb.groupNo}">
									<input type="hidden" name="groupOrd" value="${fb.groupOrd}">
									<button>답글달기</button>
								</form>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					${paging}
				</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>