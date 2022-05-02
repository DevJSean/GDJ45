<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	label {
		display: block;
	}
</style>

<!-- 비동기 처리를 위한 제이쿼리 CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	// 페이지 로드 이벤트
	$(document).ready(function(){
		fnList();
		fnDetail();
		fnAdd();
		fnModify();
		fnRemove();
		fnInit();
	});
	
	// 함수
	function fnList(){
		// 화면이 열리면 곧바로 실행
		$.ajax({
			url: "http://localhost:9090/AJAX/list.do", // /AJAX/list.do
			dataType: 'json',
			success: function(responseText){
				var memberList = $('#memberList'); // <tbody>
				memberList.empty(); // 회원 목록의 초기화
				$.each(responseText, function(i, member){ // $.each(배열, function(인덱스, 각 멤버 하나))
					var tr = '<tr>';
					tr += '<td>' + member.id + '</td>';
					tr += '<td>' + member.name + '</td>';
					tr += '<td>' + member.gender + '</td>';
					tr += '<td>' + member.address + '</td>';
					tr += '</tr>';
					memberList.append(tr);
				})
			}
		})
	}
	function fnDetail(){
		// 조회 버튼을 클릭하면 실행
		
	}
	function fnAdd(){
		// 등록 버튼을 클릭하면 실행
		$('#btnAdd').on('click', function(){
			// 요청 URL
			// http://localhost:9090/AJAX/add.do
			
			// 요청 Method
			// post
			
			// 요청 Parameter (보내주는 값)
			// id=아이디&name=이름&gender=성별&address=주소
			
			// 요청 Parameter 상세
			// id	   : 신규 회원의 아이디 (필수, 중복 불가)
			// name    : 신규 회원의 이름
			// gender  : 신규 회원의 성별
			// address : 신규 회원의 주소
			
			$.ajax({
				// 요청
				url: '/AJAX/add.do',
				type: 'POST',
				    // ↓ AddService에서 부르는 파라미터    ↓                               ↓                                                        ↓
				data: 'id=' + $('#id').val() +         '&name=' + $('#name').val() + '&gender=' + $(':radio[name="gender"]:checked').val() + '&address=' + $('#address').val(),
				
				// 응답
				dataType: 'json',
				success: function(responseText){
					alert(responseText.res);
				},
				error: function(jqXHR){
					alert(jqXHR.status); // 1818
					alert(jqXHR.responseText); // 데이터베이스에 등록되지 않았습니다.
				}
			});
		});
	}
	function fnModify(){
		// 수정 버튼을 클릭하면 실행
		
	}
	function fnRemove(){
		// 삭제 버튼을 클릭하면 실행
		
	}
	function fnInit(){
		// 초기화 버튼을 클릭하면 실행
		$('#btnInit').on('click', function(ev){
			$('#id').val('');
			$('#name').val('');
			$(':radio[name="gender"]').prop('checked', false);
			$('#address').val('');
		})
		
	}
</script>

</head>
<body>
	
	<h1>회원관리</h1>
	<div>
		<form id="formMember">
			<label for="id">
				아이디
				<input type="text" name="id" id="id">	
			</label>
			<label for="name">
				이름
				<input type="text" name="name" id="name">
			</label>
			<label for="male">
				남자
				<input type="radio" name="gender" id="male" value="male">
			</label>
			<label for="female">
				여자
				<input type="radio" name="gender" id="female" value="female">
			</label>
			<label for="address">
				주소
				<input type="text" name="address" id="address">
			</label>
			<div>
				<input type="button" value="초기화" id="btnInit">
				<input type="button" value="등록" id="btnAdd">
				<input type="button" value="수정" id="btnModify">
				<input type="button" value="삭제" id="btnRemove">
			</div>
		</form>
	</div>
	
	<hr>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>주소</td>
					<td>버튼</td>
				</tr>
			</thead>
			<tbody id="memberList">
			</tbody>
		</table>
	</div>

</body>
</html>