<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){   // 페이지 로드 이벤트
		// 수정하기
		var f = $('#f');
		var btnModify = $('#btn_modify');
		btnModify.click(function(){
			// 수정된 내용이 없을 때 경고창 띄우기
			//   수정한 내용      DB에 저장된 내용
			// $('#name').val()이 ${emp.name}과 같으면 수정된 내용이 없는 것.
			if($('#name').val() == '${emp.name}' && $('#dept').val() == '${emp.dept}'){
				alert('수정할 내용이 없습니다.');
				return;
			}
			f.attr('action', '/DBCP/modify.do');
			f.submit();
		});
		
		// 삭제하기
		var btnRemove = $('#btn_remove');
		btnRemove.click(function(){
			// 방법1
			if(confirm('삭제할까요?')) {
				f.attr('action', '/DBCP/remove.do');
				f.submit();
			}
			// 방법2
			/*
			if(confirm('삭제할까요?')){
				location.href='/DBCP/remove.do?empNo=' + $('#empNo').val();
			}
			*/
		});
	});
</script>

</head>
<body>

	<h1>사원상세정보화면</h1>
	<form id="f">
		<div>사원번호 : ${emp.empNo}</div>
		<div>사원이름 : <input type="text" name="name" id="name" value="${emp.name}"></div>
		<div>부서     : <input type="text" name="dept" id="dept" value="${emp.dept}"></div>
		<div>입사일자 : ${emp.hired}</div>
		<input type="hidden" name="empNo" value="${emp.empNo}"> <!-- 수정할 때 DB에서 사원번호를 사용해야 하기 때문에 넣는다 -->
		<input type="button" value="수정" id="btn_modify">
		<input type="button" value="삭제" id="btn_remove">
		<input type="button" value="목록" onclick="location.href='/DBCP/list.do'">
	</form>
</body>
</html>