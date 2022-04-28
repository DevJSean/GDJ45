<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String title = request.getParameter("title"); // 여기서 변수 title에 "경제"가 들어감
	
	if(title == null) {  // 전달이 안 됐을 때
		title = "홈페이지";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title%></title>
</head>
<body>

	<header>
		<h1>웹사이트</h1>	
		<nav>
			<ul>
				<li>메뉴1</li>
				<li>메뉴2</li>
				<li>메뉴3</li>
				<li>메뉴4</li>
				<li>메뉴5</li>
			</ul>
		</nav>
	</header>
	
	<section>
    
