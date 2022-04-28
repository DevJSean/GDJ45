<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>


	<form action="/JSP/ex06_session/022_add_cart.jsp">
		<h1>쇼핑 목록</h1>
		<select name="name">
			<option value="TV">TV</option>
			<option value="세탁기">세탁기</option>
			<option value="건조기">건조기</option>
			<option value="냉장고">냉장고</option>
			<option value="인덕션">인덕션</option>
		</select>
		<input type="text" name="quantity" size="4">개  <%-- 공백을 넘기면 null이 아니라 빈 문자열이 넘어감 --%>
		<button>장바구니에 담기</button> 
	</form>


</body>
</html>