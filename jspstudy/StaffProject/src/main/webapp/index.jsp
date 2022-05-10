<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<script>

	$(document).ready(function(){
		fnList();
		fnDetail();
		fnAdd();
	});

	function fnList(){
		$.ajax({
			url: "http://localhost:9090/StaffProject/list.do",
			dataType: 'json',
			success: function(responseText){
				
				var staffList = $('#staffList');
				staffList.empty();
				
				$.each(responseText, function(i, staff){
					var tr = '<tr>';
					tr += '<td>' + staff.? + '</td>';
					tr += '<td>' + staff.? + '</td>';
					tr += '<td>' + staff.? + '</td>';
					tr += '<td>' + staff.? + '</td>';
					tr += '</tr>'; 
					staffList.append(tr);	
				})
			}
		})
	}
	
	function fnDetail() {
		#('#btnDetail').on('click', function(){
			$.ajax({
				
			})
		})
	}
	
</script>


</head>
<body>

	<div>
		<form id="addForm">
			<label for="id">
				아이디
				<input type="text" name="id" id="id">
			</label>
			<label for="name">
				이름
				<input type="text" name="name" id="name">
			</label>
			<label for="tel">
				전화번호
				<input type="text" name="tel" id="tel">
			</label>
			<input type="button" value="등록" id="btnAdd">
		</form>
	</div>

	<hr>
	
	<div>
		<form id="detailForm">
			<label for="no">
				스태프번호
				<input type="text" name="no" id="no">
			</label>
			<input type="button" value="조회" id="btnDetail">
		</form>
	</div>
	
	<hr>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</thead>		
			<tbody id="staffList">
			</tbody>
		</table>
	</div>

</body>
</html>