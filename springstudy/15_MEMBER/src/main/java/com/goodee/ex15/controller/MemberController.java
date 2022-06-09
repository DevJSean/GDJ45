package com.goodee.ex15.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.ex15.domain.MemberDTO;
import com.goodee.ex15.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/member/agreePage")
	public String agreePage() {
		return "member/agree";
	}
	
	@GetMapping("/member/signInPage")
	public String signInPage(@RequestParam(required=false) String[] agreements, Model model) {
		model.addAttribute("agreements", agreements);
		return "member/signIn";
	}
	
	@ResponseBody // 값을 반환, AJAX처리
	@GetMapping(value="/member/idCheck", produces="application/json")
	public Map<String, Object> idCheck(@RequestParam String id) {
		return memberService.idCheck(id);
		// {"res": null}
		// {"res": {"memberNo": 1, "id": "userId", ...}}
	}
	
	@ResponseBody
	@GetMapping(value="/member/emailCheck", produces="application/json")
	public Map<String, Object> emailCheck(@RequestParam String email) {
		return memberService.emailCheck(email);
		// {"res": null}
		// {"res": {"memberNo": 1, "id": "userId", ...}}
	}
	
	@ResponseBody
	@GetMapping(value="/member/sendAuthCode", produces="application/json")
	public Map<String, Object> sendAuthCode(@RequestParam String email) {
		return memberService.sendAuthCode(email);
	}
	
	@PostMapping(value="/member/signIn")
/*  public String signIn(HttpServletRequest request) {
	    memberService.signIn(request); return "redirect: "; }  응답없이 redirect 처리 */
	public void signIn(HttpServletRequest request, HttpServletResponse response) { // response를 사용하는 것은 응답처리하면서 location.href로 redirect하는 것
		memberService.signIn(request, response);
	}
	
	@GetMapping(value="/member/signOut")
	public void signOut(HttpServletRequest request, HttpServletResponse response) {
		memberService.signOut(request, response);
	}
	
	@GetMapping("/member/loginPage")
	public String loginPage() {
		return "member/login";
	}
	
	// login() 메소드 수행 전에 LoginInterceptor의 preHandle() 메소드가 호출됨
	@PostMapping("/member/login")
	public void login(HttpServletRequest request, Model model) { 
		MemberDTO loginMember = memberService.login(request);
		if(loginMember != null) {
			model.addAttribute("loginMember", loginMember); // Model에 저장된 속성은 LoginInterceptor의 postHandle() 메소드의 ModelAndView 매개변수가 받는다.
		}
	}
	// login() 메소드 수행 후에 LoginInterceptor의 postHandle() 메소드가 호출됨
	
	
	// LoginInterceptor의 preHandle() 메소드에서 탈퇴자 조회 후 탈퇴자인 경우 처리
	@PostMapping("/member/reSignInPage")
	public String reSignInPage() {
		return "member/reSignIn";
	}
	
	@PostMapping("/member/reSignIn")
	public void reSignIn(HttpServletRequest request, HttpServletResponse response) {
		memberService.reSignIn(request, response);
	}
}
