<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%-- 변경되는 내용이 있는 경우 include 액션태그를 이용해 포함한다. --%>

	<% request.setCharacterEncoding("UTF-8"); %>
	<!-- header.jsp를 포함시킬 때 -->
	<!-- title을 param파라미터로 전달 -->
	<jsp:include page="header.jsp">
		<jsp:param value="경제" name="title" />
	</jsp:include>
	

		<div>구역1</div>
		<div>구역2</div>
		<div>구역3</div>
		<div>구역4</div>
		<div>구역5</div>
		<aside>배너</aside>

	<%@ include file="footer.jsp" %>