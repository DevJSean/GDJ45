<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="resources/js/jquery-3.6.0.js"></script>
<script>

	$(document).ready(function(){
		
		fnAjax1();
		$('#btnReset').on('click', fnAjax1);
		$('#btnSearch').on('click', fnAjax2);
		
	})
	
	function fnAjax1(){
		
		$('#movies').empty();
		
		$.ajax({
			url: '${contextPath}/movie/searchAllMovies',
			type: 'get',
			dataType: "json",
			success: function(obj){ 
				$('#movies').empty();
				
				alert(obj.message);
				
				$.each(obj.list, function(i, movie){
					var tr = '<tr>';
					tr += '<td>' + movie.title + '</td>';
					tr += '<td>' + movie.genre + '</td>';
					tr += '<td>' + movie.description + '</td>';
					tr += '<td>' + movie.star + '</td>';
					tr += '</tr>';
					$('#movies').append(tr);
				})
			},
			error: function(jqXHR){
				console.log(jqXHR.status);
				console.log(jqXHR.responseText);
			}
		})
	}
	function fnAjax2(){
		
		$('#movies').empty();
		
		$.ajax({
			url: '${contextPath}/movie/searchMovie',
			type: 'post',
			data: JSON.stringify({
				'column': $('#column').val(),
				'query': $('#query').val()
			}),
			contentType: 'application/json',
			
			dataType: "json",
			success: function(obj){ 
				
				$('#movies').empty();
				
				alert(obj.message);

 				$.each(obj.list, function(i, movie){
					var tr = '<tr>';
					tr += '<td>' + movie.title + '</td>';
					tr += '<td>' + movie.genre + '</td>';
					tr += '<td>' + movie.description + '</td>';
					tr += '<td>' + movie.star + '</td>';
					tr += '</tr>';
					$('#movies').append(tr);
				}) 
			},
			error: function(jqXHR){
				var tr = '<tr><td colspan="4">검색 결과가 없습니다.</td></tr>';
				$('#movies').append(tr);
			}
		})
	}

</script>

</head>
<body>

	<form id="f">
		<select name="column" id="column">
			<option value="TITLE">제목</option>
			<option value="GENRE">장르</option>
			<option value="DESCRIPTION">내용</option>
		</select>
		<input type="text" name="query" id="query">
		<input type="button" value="검색" id="btnSearch">	
		<input type="button" value="초기화" id="btnReset">	
	</form>
	
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<td>제목</td>
				<td>장르</td>
				<td>내용</td>
				<td>평점</td>
			</tr>
		</thead>
		<tbody id="movies">
		</tbody>
	</table>	
</body>
</html>