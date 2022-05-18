<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 삭제 아이콘을 위해 fontawesome CDN -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<script src="../resources/js/jquery-3.6.0.js"></script>

</head>
<body>

	<h1>게시글목록화면</h1>

	<a href="${contextPath}/board/addPage">새글작성</a>

	<table border="1">
		<caption>전체게시글 ${count}개</caption>
		<thead>
			<tr>
				<td>게시글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
				<td>작성일자</td>
				<td>삭제</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="6">작성된 게시글 없음.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach items="${boards}" var="board">
					<tr>
						<td>${board.no}</td>
						<td><a href="${contextPath}/board/detail?no=${board.no}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td>${board.hit}</td>
						<td>${board.created}</td>
						<td><a href="${contextPath}/board/remove?no=${board.no}"><i class="fa-solid fa-trash-can"></i></a></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>


</body>
</html>