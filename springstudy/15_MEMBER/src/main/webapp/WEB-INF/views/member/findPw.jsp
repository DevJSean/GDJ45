<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery -->
<script src="../resources/jquery-3.6.0.js"></script>
<!-- moment(날짜/시간 format) -->
<script src="https://momentjs.com/downloads/moment-with-locales.js"></script>
<script>

	/* 페이지 로드 이벤트 */
	$(function(){
		fnPwCheck();
		fnPwConfirm();
		fnEmailAuth();
		fnToUpperCase();
		fnChangePw();
	})
	
	/* 함수 */
	
	// 7. 비밀번호 변경
	function fnChangePw(){
		$('#f').on('submit', function(event){
			if(idPass == false){
				alert('아이디를 확인하세요.');
				event.preventDefault();
				return false;
			}
			else if(pwPass == false || rePwPass == false){
				alert('비밀번호를 확인하세요.');
				event.preventDefault();
				return false;
			}
			else if(authCodePass == false){
				alert('이메일 인증을 받아야 합니다.');
				event.preventDefault();
				return false;
			}
			return true;
		})
	}
	
	// 6. 입력을 무조건 대문자로 처리
	function fnToUpperCase(){
		$('#authCode').on('keyup', function(){
			$('#authCode').val($('#authCode').val().toUpperCase());
		})
	}
	
	// 5. 인증코드 검증
	let authCodePass = false;
	function fnVerifyAuthCode(authCode){  // 이메일로 전송한 인증코드
		$('#btnVerifyAuthCode').on('click', function(){
			if($('#authCode').val() == authCode){
				alert('인증되었습니다.');
				$('.authArea').css('display', 'none');
				$('.changeArea').css('display', 'block');
				authCodePass = true;
			} else {
				alert('인증에 실패했습니다.');
				authCodePass = false;
			}
		})
	}
	
	// 4. 이메일 중복체크
	function fnEmailCheck(){
		return new Promise(function(resolve, reject) {
			// 1) 이메일 정규식 체크
			let regEmail = /^[a-zA-Z0-9-_]+@[a-zA-Z0-9]+(\.[a-zA-Z]{2,}){1,2}$/;  // 실제 서비스에서 그대로 사용 가능.
			if(regEmail.test($('#email').val())==false){
				reject(1000);      // reject는 Promise 객체의 catch 메소드에 전달되는 함수
				                   // 이메일 형식이 잘못된 경우의 코드값 : 1000
				return;
			}
			// 2) 이메일 중복 체크
			$.ajax({
				url: '${contextPath}/member/emailCheck',
				type: 'get',
				data: 'email=' + $('#email').val(),
				dataType: 'json',
				success: function(obj){
					if(obj.res == null){
						resolve();     // resolve는 Promise 객체의 then 메소드에 전달되는 함수
					} else {
						reject(2000);  // reject는 Promise 객체의 catch 메소드에 전달되는 함수
						               // 중복된 이메일이 있는 경우의 코드값 : 2000
					}
				}
			})
		});
	}
	
	// 3. 이메일 인증
	function fnEmailAuth(){
		$('#btnGetAuthCode').on('click', function(){
			fnEmailCheck().then(
				function(){
					$.ajax({
						url: '${contextPath}/member/sendAuthCode',
						type: 'get',
						data: 'email=' + $('#email').val(),
						dataType: 'json',
						success: function(obj){  // obj에는 발송한 인증코드(authCode)가 저장되어 있음.
							alert('인증코드를 발송했습니다. 이메일을 확인하세요.');
							fnVerifyAuthCode(obj.authCode);  // 발송한 인증코드와 사용자가 입력한 인증코드가 일치하는지 점검.
						},
						error: function(jqXHR){
							alert('인증코드 발송이 실패했습니다.');
						}
					})
				}
			).catch(
				function(code){
					if(code == 1000){
						$('#emailMsg').text('이메일 형식이 올바르지 않습니다.').addClass('dont').removeClass('ok');
						$('#authCode').prop('readonly', true);
					} else if(code == 2000){
						$('#emailMsg').text('이미 사용 중인 이메일입니다.').addClass('dont').removeClass('ok');
						$('#authCode').prop('readonly', true);
					}
				}
			)
		})
	}
	
	// 2. 비밀번호 입력확인
	let rePwPass = false;
	function fnPwConfirm(){
		$('#rePw').on('keyup', function(){
			if($('#rePw').val() != '' && $('#pw').val() != $('#rePw').val()){
				$('#rePwMsg').text('비밀번호를 확인하세요.').addClass('dont').removeClass('ok');
				rePwPass = false;
			} else {
				$('#rePwMsg').text('');
				rePwPass = true;
			}
		})
	}
	
	// 1. 비밀번호 정규식
	let pwPass = false;
	function fnPwCheck(){
		// 비밀번호 정규식 검사
		$('#pw').on('keyup', function(){
			let regPw = /^[0-9]{1,4}$/;  // 숫자 1~4자
			if(regPw.test($('#pw').val())==false){
				$('#pwMsg').text('8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.').addClass('dont').removeClass('ok');
				pwPass = false;
			} else {
				$('#pwMsg').text('사용 가능한 비밀번호입니다.').addClass('ok').removeClass('dont');
				pwPass = true;
			}
		})
	}


</script>
<style>
	.changeArea {
		display: none;
	}
	.dont {
		color: red;
	}
	.ok {
		color: limegreen;
	}
</style>
</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include>

	<h3>비밀번호 찾기</h3>
	
	<div class="authArea">
	
		<form method="post">
		
			아이디<br>
			<input type="text" name="id" id="id"><br><br>
			가입 당시 이메일<br>
			<input type="text" name="email" id="email">
			<input type="button" value="인증번호받기" id="btnGetAuthCode"><br>
			<span id="emailMsg"></span><br>
			<input type="text" name="authCode" id="authCode" placeholder="인증코드 입력">
			<input type="button" value="인증하기" id="btnVerifyAuthCode"><br><br>
			
		</form>	
		
	</div>
	
	<div class="changeArea">

		<h3>새로운 비밀번호를 설정하세요</h3>
	
		<form id="f" method="post">
			
			<input type="password" name="pw" id="pw" placeholder="새 비밀번호">
			<span id="pwMsg"></span><br><br>
			
			<input type="password" name="rePw" id="rePw" placeholder="새 비밀번호 확인">
			<span id="rePwMsg"></span><br><br>
			
			<input type="button" value="비밀번호 변경하기" id="btnChangePw">
			
		</form>
	
	</div>
	
</body>
</html>