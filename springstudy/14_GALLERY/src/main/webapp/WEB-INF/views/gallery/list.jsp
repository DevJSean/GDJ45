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

	<a href="${contextPath}/gallery/savePage">새글작성</a>

	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>대표이미지</td>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${galleries}" var="fa" varStatus="vs"> <!-- FileAttachDTO를 가져온 것이다. GalleryDTO에 있는 것을 사용하려면 gallery. 을 해야한다. -->
				<tr>
					<td>${beginNo - vs.index}</td> <!-- 큰 숫자가 위로 오게 하기 -->
					<td><img alt="${fa.origin}" src="${contextPath}/gallery/display?fileAttachNo=${fa.fileAttachNo}&type=thumb"></td> <!-- 이미지를 바이트 배열 단위로 쪼개서 가져와야 한다. -->
					<td>${fa.gallery.title}</td>
					<td>${fa.gallery.writer}</td>
					<td>${fa.gallery.hit}</td>
					<td>${fa.gallery.created}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6">
					${paging}
				</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>