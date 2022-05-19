<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="resources/js/jquery-3.6.0.js"></script>  <!-- index.jsp의 위치에 주의, jquery 상대경로 위치 주의 -->

<script type="text/javascript">

	$(document).ready(function(){
		location.href='${contextPath}/book/list'; // index.jsp 열리자마자 list.jsp로 이동
	})
	
</script>
</head>
<body>

</body>
</html>