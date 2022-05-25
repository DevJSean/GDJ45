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
		fnSearchAll();
		fnSearch();
		fnAutoComplete();
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
	
	function fnSearchAll() {
		$('#btnSearchAll').on('click', function(){
			location.href='${contextPath}/employee/list';
		})
	}
	
	function fnSearch(){
		
		var column = $('#column');
		var query = $('#query');
		var begin = $('#begin');
		var end = $('#end');
		
		$('#btnSearch').on('click', function(){
			
			// 사원번호 검색
			var regEmpId = /^[0-9]{3}$/;  // 숫자 3자리여야 함.
			if( column.val() == 'EMPLOYEE_ID' && regEmpId.test(query.val()) == false) {
				alert('사원번호가 올바르지 않습니다.');
				query.focus();
				return;
			}
			
			// 입사일자 검색
			var regHireDate = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/; // 2022-05-25
			if( column.val() == 'HIRE_DATE' && ( !regHireDate.test(begin.val()) || !regHireDate.test(end.val()) ) ){
				alert('입사일자가 올바르지 않습니다.');
				return;
			}
			
			// 연봉 검색
			if( column.val() == 'SALARY' && ( isNaN(begin.val()) || isNaN(end.val()) ) ){
				alert('올바른 연봉 값이 아닙니다.')
				return;
			}
			
			// 검색 실행
			// equalArea 작업은 column, query 파라미터 전송
			// rangeArea 작업은 column, begin, end 파라미터 전송
			if( column.val() == 'EMPLOYEE_ID' || column.val() == 'FIRST_NAME' ) {
				location.href="${contextPath}/employee/search?column=" + column.val() + "&query=" + query.val();
			} else {
				location.href="${contextPath}/employee/search?column=" + column.val() + "&begin=" + begin.val() + "&end=" + end.val();
			}
		})
	}
	
	function fnAutoComplete(){
		// keyup : 한 글자 입력이 끝난 뒤 동작
		$('#query').on('keyup', function(){
			$('#autoComplete').empty();
			$.ajax({  // DB에서 입력한 값으로 시작하는 값을 가져와서 보여 줌
				url: '${contextPath}/employee/autoComplete',
				type: 'get',
				data: 'column=' + $('#column').val() + '&query=' + $('#query').val(),
				dataType: 'json',
				success: function(result){
					if(result.status == 200){
						$.each(result.list, function(i, item){
							$('<option>')
							.val(item[result.column])
							.appendTo('#autoComplete');
						})
					}
				}
			})
		})
	}
	/*  
	result = {
		"status": 200,                          result.status
		"list": [                               result.list -> item
			{                                   
				"employeeId": null,             item["employeeId"] : null
				"firstName": "Alex",            item["firstName"]  : "Alex"
				"lastName": null,               item["lastName"]   : null
				...
			},
			{
				"employeeId": null,
				"firstName": "Andrew",
				"lastName": null,
				...
			},
			... ],
		"column": "firstName"                   result.column
	} */

	
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