package com.goodee.ex15.service;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.ex15.domain.MemberDTO;
import com.goodee.ex15.domain.SignOutMemberDTO;
import com.goodee.ex15.mapper.MemberMapper;
import com.goodee.ex15.util.SecurityUtils;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public Map<String, Object> idCheck(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("res", memberMapper.selectMemberById(id));
		return map;
	}
	
	@Override
	public Map<String, Object> emailCheck(String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("res", memberMapper.selectMemberByEmail(email));
		return map;
	}
	
	@Override
	public Map<String, Object> sendAuthCode(String email) {
		
		// 인증 코드
		String authCode = SecurityUtils.authCode(6);
		System.out.println(authCode); // 이메일 안 열어보고 확인하려고 
		
		// 필수 코드
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");  // 구글 메일로 보낸다는 뜻
		props.put("mail.smtp.port", "587"); 		    // 구글 메일 보내는 포트
		props.put("mail.smtp.auth", "true"); 		    // 인증되었다.
		props.put("mail.smtp.starttls.enable", "true"); // TLS 허용한다.
		
		// 메일을 보내는 사용자 정보
		final String USERNAME = "wltlgus110@gmail.com";	// 메일을 보내는 사용자 정보
		final String PASSWORD = "rchkuovwczmribic";		// 구글에서 발급받은 앱 비밀번호
		
		// 사용자 정보 javax.mail.Session에서 저장
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
		
		/*  
		 	이메일 보내기
		 	1. 사용자 정보는 구글 메일만 가능하다
		 	2. 가급적 구글 부계정을 만들어서 사용한다.
		 	3. 구글 로그인 - Google 계정 - 보안
		 		1) 2단계 인증 - 사용
		 		2) 앱 비밀번호
		 			(1) 앱 선택 - 기타
		 			(2) 기기 선택 - windows 컴퓨터
		*/
		
		// 이메일 전송하기
		try {
			Message message = new MimeMessage(session);
			message.setHeader("Content-Type", "text/plain; charset=UTF-8");
			message.setFrom(new InternetAddress(USERNAME, "인증코드관리자"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); //setRecipient(보내는 사람, 받는 사람)
			message.setSubject("인증 요청 메일입니다.");
			message.setText("인증번호는 " + authCode + "입니다.");
			
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("authCode", authCode);
		
		return map;
	}
	
	@Override
	public void signIn(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터 처리
		String id = SecurityUtils.xss(request.getParameter("id"));       // 크로스 사이트 스크립팅
		String pw = SecurityUtils.sha256(request.getParameter("pw"));    // SHA-256 암호화
		String name = SecurityUtils.xss(request.getParameter("name"));   // 크로스 사이트 스크립팅
		String email = SecurityUtils.xss(request.getParameter("email")); // 크로스 사이트 스크립팅
		String location = request.getParameter("location");         
		String promotion = request.getParameter("promotion");            
		
		int agreeState = 1;
		if(location.isEmpty() == false && promotion.isEmpty() == false) {
	  //if(location.equals("location") && promotion.equals("promotion") ) {
			agreeState = 4; // 필수 + 위치 + 프로모션 동의
		} else if(location.equals("location") && promotion.isEmpty()) {
			agreeState = 2;
		} else if(location.isEmpty() && location.equals("promotion")) {
			agreeState = 3;
		}
		
		// MemberDTO 처리
		MemberDTO member = MemberDTO.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.email(email)
				.agreeState(agreeState)
				.build();
		
		// MEMBER 테이블에 member 저장
		int res = memberMapper.insertMember(member);
		
		// 응답
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res == 1) {
				out.println("<script>");
				out.println("alert('회원 가입되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('회원 가입에 실패했습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void signOut(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("memberNo"));
		Long memberNo = Long.parseLong(opt.orElse("0"));
	
		// MEMBER 테이블에서 member 삭제
		int res = memberMapper.deleteMember(memberNo);
		
		// 응답
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res == 1) {
				request.getSession().invalidate(); // 삭제되었으면 session에서 로그인 정보 빼야함.
				out.println("<script>");
				out.println("alert('Good Bye')");
				out.println("location.href='" + request.getContextPath() + "'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('회원 탈퇴 실패하셨네요.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public MemberDTO login(HttpServletRequest request) {
		
		// 파라미터
		String id = SecurityUtils.xss(request.getParameter("id"));
		String pw = SecurityUtils.sha256(request.getParameter("pw"));
		
		// MemberDTO
		MemberDTO member = MemberDTO.builder()
				.id(id)
				.pw(pw)
				.build();
		
		// ID/Password가 일치하는 회원 조회
		MemberDTO loginMember = memberMapper.selectMemberByIdPw(member);
		
		// 로그인 기록 남기기
		if(loginMember != null) {
			memberMapper.insertMemberLog(id);
		}
		
		return loginMember;
	}
	
	@Override
	public SignOutMemberDTO findSignOutMember(String id) {
		return memberMapper.selectSignOutMemberById(id);
	}
	
	@Transactional
	@Override
	public void reSignIn(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터
		Long memberNo = Long.parseLong(request.getParameter("memberNo"));
		String id = request.getParameter("id");
		String pw = SecurityUtils.sha256(request.getParameter("pw"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		Integer agreeState = Integer.parseInt(request.getParameter("agreeState"));
		
		// MemberDTO
		MemberDTO member = new MemberDTO(memberNo, id, pw, name, email, agreeState, null, null, null, null, null);
		
		
		// MEMBER 테이블에 member 저장
		// insert와 delete  => transaction 대상이다.
		int res1 = memberMapper.reInsertMember(member);
		int res2 = memberMapper.deleteSignOutMember(id);
		
		// 응답
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(res1 == 1 && res2 == 1) {
				out.println("<script>");
				out.println("alert('다시 모든 서비스를 이용할 수 있습니다.')");
				out.println("location.href='" + request.getContextPath() + "'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('회원 재가입에 실패했습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keepLogin(HttpServletRequest request) {
		
		// 1000 * 60 * 60 * 24 * 7 : 7일에 해당하는 밀리초(ms)
		Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)); // 지금으로부터 7일 후 날짜
		
		// request.getSession().getId() == 9A071414F2E08D3CC7A7BC92D8F364A4(브라우저 닫을 때마다 바뀜) : 브라우저의 쿠키에서 JSESSIONID로 확인 가능 
		String sessionId = request.getSession().getId();		
		String id = request.getParameter("id");	
		
		// MemberDTO
		MemberDTO member = MemberDTO.builder()
				.id(id)
				.sessionId(sessionId)
				.sessionLimit(sessionLimit)
				.build();
	
		// MEMBER 테이블에서 member 정보 수정
		memberMapper.updateSessionInfo(member);
	}
	
	@Override
	public MemberDTO getMemberBySessionId(String sessionId) {
		return memberMapper.selectMemberBySessionId(sessionId);
	}
	
	
}
