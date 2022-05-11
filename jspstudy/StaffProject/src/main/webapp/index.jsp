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
		fnSearch();
		fnAdd();
		fnSearchAll();
	});

	function fnList(){
		$.ajax({
			url: "http://localhost:9090/StaffProject/list.json",
			type: "get",
			dataType: 'json',
			success: function(responseText){
				
				var staffList = $('#staffList');
				staffList.empty();
				
				$.each(responseText, function(i, staff){
					var tr = '<tr>';
					tr += '<td>' + staff.sno + '</td>';
					tr += '<td>' + staff.name + '</td>';
					tr += '<td>' + staff.dept + '</td>';
					tr += '<td>' + staff.salary + '</td>';
					tr += '</tr>'; 
					staffList.append(tr);	
				})
			}
		})
	}
	 
	function fnSearch() {
		$('#btnSearch').on('click', function(){
			$.ajax({
				url:"http://localhost:9090/StaffProject/query.json",
				type: 'get',
				data: 'query=' + $('#searchSno').val(),
				dataType: 'json',
				success: function(responseText){
					
					var staffList = $('#staffList');
					staffList.empty();
					
					var tr = '<tr>';
					tr += '<td>' + responseText.sno + '</td>';
					tr += '<td>' + responseText.name + '</td>';
					tr += '<td>' + responseText.dept + '</td>';
					tr += '<td>' + responseText.salary + '</td>';
					tr += '</tr>'; 
					staffList.append(tr);
					
				}
			})
		})
	}
	
	
	function fnAdd() {
		$('#btnAdd').on('click', function(ev){
			var regSno = /^[0-9]{5}$/;
			var regDept = /^[가-힣]{3,5}$/;
			var sno = document.getElementById('sno');
			var dept = document.getElementById('dept');
			var salary = document.getElementById('salary');
			
			if(regSno.test(sno.value) == false){
				alert('사원번호는 5자리 숫자입니다.');
				$('#sno').val('');
			}
			if(regDept.test(dept.value) == false){
				alert('부서는 3~5자리 한글입니다.');
				$('#dept').val('');
			}
			
			switch (dept.value){
				case "기획부": salary.value = 5000;
								break;
				case "개발부": salary.value = 6000;
								break;
				case "영업부": salary.value = 7000;
								break;
				default: salary.value = 4000;
			}
			
			$.ajax({
				url: "http://localhost:9090/StaffProject/add.json",
				type: 'post',
				data: 'sno=' + $('#sno').val() + '&name=' + $('#name').val() + '&dept=' + $('#dept').val() + '&salary=' + $('#salary').val(),
				dataType: 'json',
				success: function(responseText){
					if(responseText.res == 1){
						alert('사원 등록이 성공했습니다.');
						fnList();

						$('#sno').val('');
						$('#name').val('');
						$('#dept').val('');
						
					}
				},
				error: function(jqXHR){
					alert(jqXHR.responseText);
					alert(jqXHR.status);
				}
			})
		})
	}
	
	function fnSearchAll(){
		$('#btnSearchAll').on('click', function(){
			fnList();
		})
	}
	
</script>


</head>
<body>

	<div>
		<h3>사원등록</h3>
		<form id="addForm">
			<label for="sno">
				<input type="text" name="sno" id="sno" placeholder="사원번호">
			</label>
			<label for="name">
				<input type="text" name="name" id="name" placeholder="사원명">
			</label>
			<label for="dept">
				<input type="text" name="dept" id="dept" placeholder="부서명">
			</label>
				<input type="hidden" name="salary" id="salary" value="">
			<input type="button" value="등록" id="btnAdd">
		</form>
	</div>

	<hr>
	
	<div>
		<h3>사원조회</h3>
		<form id="searchForm">
			<label for="searchSno">
				<input type="text" name="searchSno" id="searchSno" placeholder="사원번호입력">
			</label>
			<input type="button" value="조회" id="btnSearch">
			<input type="button" value="전체" id="btnSearchAll">
		</form>
	</div>
	
	<hr>
	
	<div>
		<h3>사원목록</h3>
		<table border="1">
			<thead>
				<tr>
					<td>사원번호</td>
					<td>사원명</td>
					<td>부서명</td>
					<td>연봉</td>
				</tr>
			</thead>		
			<tbody id="staffList">
			</tbody>
		</table>
	</div>

</body>
</html>