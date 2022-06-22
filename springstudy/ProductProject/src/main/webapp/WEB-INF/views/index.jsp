<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="resources/js/jquery-3.6.0.js"></script>
<script>
	$(function(){
		fnProductSearch();
	})
	
	function fnProductSearch(){
		$('#btnSearch').click(function(){
			$.ajax({
				url: '${contextPath}/product/search',
				type: 'get',
				data: $('#f').serialize(),
				dataType: 'json',
				success: function(obj) {
					$('#products').empty();
					var products = obj.items;
					$.each(products, function(i, product){
						var tr = '<tr>';
						tr += '<td><a href="' + product.link + '">' + product.title + '</a></td>';
						tr += '<td><img width="100px" src=' + product.image + '></td>';
						tr += '<td>' + product.lprice + '</td>';
						tr += '</tr>';
						$('#products').append(tr);
					})
				},
				error: function(jqXHR){
					alert(jqXHR.responseText);
				}
			})
		})
	}
	
	/*
	// 데이터 보내는 방법 1  
	// 파라미터 전송(form의 name 속성 이용)
		$.ajax({
			data: $('#f').serialize(), 파라미터 전송 (name 속성)
		})
	// 파라미터 전송(form의 id를 이용하여 json을 보내기)
		$.ajax({
			data: JSON.stringify({
				display: $('#display').val(),
				sort: $(':radio[name="sort"]:checked').val(),
				query: $('#query').val()
			}),
			contentType: 'application/json',
			type: 'POST', ...
		})
		Controller에서 데이터 받을 때
		(@RequestBody SearchDTO search)
		
	// 데이터 보내는 방법 2
		HttpServletRequest request
		
		Controller에서 데이터 받을 때
		@RequestParam String display, ...
	
	// 데이터 보내는 방법 3
		SearchDTO
			String display
			String sort
			String query
	*/
	
</script>
</head>
<body>
	
	<h1>제품 검색 화면</h1>
	<form id="f">
		<label for="display">
			검색결과건수
			<select name="display" id="display">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="40">40</option>
				<option value="60">60</option>
				<option value="80">80</option>
				<option value="100">100</option>
			</select>
		</label><br>
		<label for="sim"><input type="radio" name="sort" id="sim" value="sim" checked="checked">유사도</label>
		<label for="date"><input type="radio" name="sort" id="date" value="date">날짜순</label>
		<label for="asc"><input type="radio" name="sort" id="asc" value="asc">낮은가격순</label>
		<label for="dsc"><input type="radio" name="sort" id="dsc" value="dsc">높은가격순</label><br>
		<input type="text" name="query" id="query">
		<input type="button" value="검색" id="btnSearch">
	</form>
	
	<hr>
		
	<table border="1">
		<thead>
			<tr>
				<td>상품명</td>
				<td>썸네일</td>
				<td>최저가</td>
			</tr>
		</thead>
		<tbody id="products"></tbody>
	</table>	
		
		
	
</body>
</html>