package com.goodee.ex12.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/user/login")
	public String login(HttpSession session, HttpServletRequest request) {
		// 마치 로그인 처럼 구현 (DB, userDTO 같은 게 없음 -> session, request로 해결)
		// 아이디, 비밀번호, 이름을 Map으로 만들어서 session에 보관
		// HttpSession session = request.getSession(); 매개변수가 아니고 request를 통해서 여기서 선언해도 됨.
		Map<String, String> user = new HashMap<>();
		user.put("id", request.getParameter("id"));
		user.put("pw", request.getParameter("pw"));
		user.put("name", "로그인 성공한 사람");
		session.setAttribute("user", user); // session은 페이지가 바껴도 사용 가능(목록보기, 상세보기, 삽입,수정,삭제 페이지)
											// user의 정보는 창을 다 닫아야(브라우저 자체) 사라진다.
		return "index"; // index.jsp로 이동
	}
	
}
