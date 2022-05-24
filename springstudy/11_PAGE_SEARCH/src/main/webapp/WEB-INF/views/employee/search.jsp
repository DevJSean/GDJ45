<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

	// 페이지 로드 이벤트
	$(function(){
		fnAreaChoice();
	})
	
	// 함수
	function fnAreaChoice(){
		
		// 초기 : 모두 숨김
		$('#equalArea, #rangeArea').css('display', 'none');
	
		// 선택
		$('#column').on('change', function(){ // 목록이 바뀔 때 이벤트 change
			if($(this).val() == ''){          // $(this) : event 타겟 -> #column
				$('#equalArea, #rangeArea').css('display', 'none');
			} else if( $(this).val() == 'EMPLOYEE_ID' || $(this).val() == 'FIRST_NAME') {
				$('#equalArea').css('display', 'inline');
				$('#rangeArea').css('display', 'none');
			} else {
				$('#equalArea').css('display', 'none');
				$('#rangeArea').css('display', 'inline');
			}								  
		})
	}
	
</script>

</head>
<body>

	<h1>사원 검색</h1>
	
	<form id="f" method="get">
		<select name="column" id="column">
			<option value="">:::선택:::</option>
			<option value="EMPLOYEE_ID">사원번호</option>  <!-- 이걸로 검색하는 것이다. -->
			<option value="FIRST_NAME">이름</option>       <!-- Value는 DB의 칼럼명과 맞춘다. -->
			<option value="HIRE_DATE">고용일</option>
			<option value="SALARY">연봉</option>
		</select>
		<span id="equalArea"> <!-- select 옆에 두기 위해서 div가 아닌 span을 쓴다 -->
			<input type="text" name="query" id="query" list="autoComplete">
			<datalist id="autoComplete"></datalist> <!-- 텍스트 상자 안에 고를 수 있는 선택지 만들어주는 태그이다. -->
		</span>
		<span id="rangeArea">
			<input type="text" name="begin" id="begin">
			~
			<input type="text" name="end" id="end">
		</span>
		<input type="button" value="검색" id="btnSearch">
		<input type="button" value="전체사원조회" id="btnSearchAll">
	</form>

	<br><hr><br>
	
	<%@ include file="list.jsp" %> <%-- 다른 jsp를 불러들이기 --%>

</body>
</html>