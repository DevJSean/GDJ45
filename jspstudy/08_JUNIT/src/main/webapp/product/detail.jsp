<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 3가지 방법 --%>
	<a href="/JUNIT/remove.do?product_no=${product.product_no}">삭제</a><br>
	
	<input type="button" value="삭제" onclick="location.href='/JUNIT/remove.do?product_no=${product.product_no}'">
	
	<form action="/JUNIT/remove.do">
		<!-- <input name="파라미터" value="값"> -->
		<input type="hidden" name="product_no" value="${product.product_no}">
		<button>삭제</button>
	</form>


	<div>${product.product_no}</div>
	<div>${product.name}</div>
	<div>${product.price}</div>
	<div><img src="${contextPath}/storage/${product.image}" alt="${product.image}"></div>
	
	<%-- <%=request.getContextPath()%> --%>
	<%-- /JUNIT --%>
	<%-- webapp --%>
	<!-- 서버에서는 webapp 밑에 storage 폴더가 있다.  -->
	<!--  ../storage/${product.image} -->

</body>
</html>