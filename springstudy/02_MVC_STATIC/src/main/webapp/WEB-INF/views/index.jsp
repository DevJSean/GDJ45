<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- pom.xml에 jstl이 있어서 taglib 가능함 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		${pageContext.request.contextPath}를 page, request, session, application 중 하나에 상황에 맞게 저장해서
		${contextPath}로 꺼내서 쓸 수 있도록 하자.
		
		<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page/request/session/application" />
		var="contextPath"                          : contextPath 속성(attribute)으로 저장합니다.
		value="${pageContext.request.contextPath}" : contextPath 속성에 저장되는 값
		scope="application"                        : application에 저장된 속성은 애플리케이션(프로젝트)에서 공유한다.
													 index.jsp에서 작업, 제일 먼저 열리기 때문에 무조건 선언됨. 모든 jsp에서 contextPath 속성을 사용할 수 있다.
	 --%>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application" />


	<h1>${res}</h1>
	
	<a href="/ex02/imageView" >이미지 보러가기</a>

	<br>
	
	<!-- hosting에 올릴 때 "/ex02"(contextPath)가 폴더로 인식되는 경우가 있다. -->
	<!-- contextPath를 변수(EL)로 처리하면 해결된다 -->

	<a href="${contextPath}/imageView">이미지 보러가기</a>

	<br>
	
	<!-- gallery/lion.jsp로 이동해서 Eevee.jpg 이미지를 보여주세요. -->
	<!-- 
		여기서 Eevee.jpg는 resources가 아닌 assets 폴더에 있다.
		servlet-context.xml에서 static-resource를 resources폴더로만 잡아두었기 때문에 
		assets 폴더를 새로 등록이 필요하다.
	 -->
	<a href="${contextPath}/lionView">사자 보러가기</a>
	
	

</body>
</html>