<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>practice 02</title>
</head>
<body>

	<%-- 
		순번    이름      나이
		1       홍길동    20
		2       홍길순    30
		3       홍두깨    40
	--%>
	<% 
		List<String> names = Arrays.asList("홍길동", "홍길순", "홍두깨");
		List<Integer> ages = Arrays.asList(20, 30, 40);
	%>
	<table border="1">
		<thead>
			<tr>
				<td>순번</td>
				<td>이름</td>
				<td>나이</td>
			</tr>
		</thead>
		<tbody>
			<% for(int i = 0, length = names.size(); i < length; i++) { %>
				<tr>
					<td><%=i + 1%></td>
					<td><%=names.get(i)%></td>
					<td><%=ages.get(i)%></td>
				</tr>
			<% } %>
		</tbody>
	</table>
	



</body>
</html>