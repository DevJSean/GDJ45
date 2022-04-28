<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		// 로그인 유지를 선택한 뒤 로그인을 수행하고,
		// 다시 login_form으로 되돌아 왔을 경우
		// login_form 화면을 보여 주면 안 된다.
		boolean keepLogin = false;
		Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("login_id")) {
				keepLogin = true;
				break;
			}
		}
	%>
	
	<!-- 로그인의 첫 시도 -->
	<% if(keepLogin == false) { %>
		<form action="02_login.jsp" method="post">
			<div>
				<input type="text" name="id" placeholder="아이디">
			</div>
			<div>
				<input type="password" name="pw" placeholder="비밀번호">
			</div>
			<div>
				<button>로그인</button>
			</div>
			<div>
				<label>
					<input type="checkbox" name="checkKeepLogin"> <!-- check 안하면 submit 자체가 안되어 null임 -->
					로그인 유지하기
				</label>
			</div>
		</form>
	<% } else { 
		// 로그인 유지 누르고 다시 만났을 때
		response.sendRedirect("03_keep_login.jsp");
	
	   } %>
		

</body>
</html>