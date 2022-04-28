<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%

	// session에 저장된 장바구니 지우기
	// invalidate는 로그인도 풀리기 때문에 안됨.
	session.removeAttribute("cart");

	response.sendRedirect("/JSP/ex06_session/023_cart.jsp");

%>