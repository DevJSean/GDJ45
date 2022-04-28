<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>

	<!-- HTML 주석 : 소스보기에서 보인다.  -->
	<%-- JSP 주석  : 소스보기에서 안 보인다.(페이지 우클릭 소스보기) --%>

	<%!
		// 선언부(declaration)
		public int getSum(int begin, int end) { // 작은 수에서 큰 수로 커진다는 가정이 있다.
			int sum = 0;
			if(begin > end) {
				int temp = begin;
				begin = end;
				end = temp;
			}
			for(int n = begin; n <= end; n++) {
				sum += n;
			}
			return sum;
		}
	%>
	
	<% 
	    // scriptlet 일반 자바 코드 영역
		int res1 = getSum(1, 10);
		int res2 = getSum(100, 1);
	%>
	
	<h3>1부터 10 사이 모든 정수의 합은 <%=res1%>입니다.</h3>
	<h3>1부터 100 사이 모든 정수의 합은 <%=res2%>입니다.</h3>
	
	<%-- 1~5 화면 출력 --%>
	<% for(int n = 1; n <= 5; n++) {%>
		<%=n%><br>
	<% } %>
		
	
</body>
</html>