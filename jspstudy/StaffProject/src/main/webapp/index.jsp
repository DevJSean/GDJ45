<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	// 페이즈 로드 이벤트
	$(document).ready(function(){
		fnList();
		fnDetail();
		fnReset();
		fnAdd();
	})
	
	
	
	// 함수 영역
	
	// 사원 전체 조회
	function fnList(){
		// 얘는 화면이 열리면 바로 실행!
		$.ajax({
			url : '/StaffProject/list.json',
			type: 'GET',
			dataType : 'json',
			success : function(responseText){
				// json 형태로 들어온다.
				var staffList = $('#staffList');
				staffList.empty();	// 사원 목록 초기화
				$.each(responseText, function(i,staff){
					var tr=$('<tr>');
					$('<td>').html(staff.sno).appendTo(tr);
					$('<td>').html(staff.name).appendTo(tr);
					$('<td>').html(staff.dept).appendTo(tr);
					$('<td>').html(staff.salary).appendTo(tr);
					tr.appendTo(staffList);
				}) // end of each
				
			},
			error : function(jqXHR){
				
			}
		}) // end of ajax
	}
	
	// 사원1명 조회
	function fnDetail(){
		$('body').on('click','#btnDetail',function(){
			var staffList = $('#staffList');
			staffList.empty();
			var sno = $('#inputSno').val();	// 사원번호
			$.ajax({
				url : '/StaffProject/query.json',
				data : 'query=' + sno,
				type:'GET',
				dataType:'json',
				success : function(responseText){
					var staffList = $('#staffList');
					staffList.empty();
					var tr = $('<tr>');
					$('<td>').html(responseText.sno).appendTo(tr);
					$('<td>').html(responseText.name).appendTo(tr);
					$('<td>').html(responseText.dept).appendTo(tr);
					$('<td>').html(responseText.salary).appendTo(tr);
					tr.appendTo(staffList);
					$('#inputSno').val('');
				},
				error :function(jqXHR){
					
				}
				
			})// end of ajax
		}) // end of event
	} // end of function
	
	function fnReset(){
		$('#btnList').click(function(){
			fnList();
		})
	}
	
	
	// 정규식 부분
	var regSno = /^[0-9]{5}$/;
	var regDept = /^[가-힣]{3,5}$/;
	
	// 사원 등록
	function fnAdd(){
		$('#btnAdd').click(function(){
			if(regSno.test($('#sno').val())==false){
				alert('사원번호는 5자리 입니다.');
				$('#sno').val('');
				return;
			}
			if(regDept.test($('#dept').val())==false){
				alert('부서는 3~5자리 한글입니다.');
				$('#dept').val('');
				return;
			}
			$.ajax({
				url : '/StaffProject/add.json',
				type: 'POST',
				data : $('#formRegister').serialize(),
				dataType :'json',
				success : function(responseText){
					console.log(responseText);
					if(responseText.res == 1){
						alert('신규회원 등록 성공!');
						fnList();
						// 회원 입력창 초기화!
						$('#sno').val('');
						$('#name').val('');
						$('#dept').val('');
					} else{
						alert('회원등록 실패');
					}
				},
				error : function(jqXHR){
					alert("("+jqXHR.status+") " + jqXHR.responseText);
				}
			}) // end of ajax
		}) // end of event
	} // end of function
</script>
</head>
<body>

	<h1>사원등록</h1>
	<div>
		<form id="formRegister">
			<input type="text" name="sno" id="sno" placeholder="사원번호">
			<input type="text" name="name" id="name" placeholder="사원명">
			<input type="text" name="dept" id="dept" placeholder="부서명">
			<input type="button" value="등록" id="btnAdd">
		</form>
	</div>
	
	<hr>
	
	<h1>사원조회</h1>
	<div>
		<form id="selectform">
			<input type="text" name= "inputSno" id="inputSno" placeholder="사원번호입력">
			<input type="button" value="조회" id="btnDetail">
			<input type="button" value="전체" id="btnList">
		</form>
	</div>
	
	<hr>
	
	<h1>사원목록</h1>
	<div>
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