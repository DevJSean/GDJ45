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
	<a href="/JUNIT/remove.prod?productNo=${product.productNo}">삭제</a><br>
	
	<input type="button" value="삭제" onclick="location.href='/JUNIT/remove.prod?productNo=${product.productNo}'">
	
	<form action="/JUNIT/remove.prod">
		<!-- <input name="파라미터" value="값"> -->
		<input type="hidden" name="productNo" value="${product.productNo}">
		<button>삭제</button>
	</form>

	<hr>

	<div>제품번호 : ${product.productNo}</div>
	<div>제품명 &nbsp;&nbsp;&nbsp;: ${product.name}</div>
	<div>제품가격 : ${product.price}원</div>
	<br>
	<div>제품이미지<br><img src="${contextPath}/storage/${product.image}" alt="${product.image}"></div>
	

</body>
</html>