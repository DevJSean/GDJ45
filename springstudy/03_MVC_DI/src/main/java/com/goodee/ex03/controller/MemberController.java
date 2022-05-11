package com.goodee.ex03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.ex03.domain.Member;

@Controller
public class MemberController {

	// 1. field 주입
	// @Autowired
	// private Member member1;
	
	// 2. 생성자 주입
	//private Member member1;
	//public BoardController(Member member2) {
	//	super();
	//	this.member1 = member2;
	//}

	// 3. setter 주입
	//private Member member1;
	//@Autowired
	//public void ㅋ(Member member3) {
	//	this.member1 = member3;
	//}

	@Autowired
	@Qualifier(value="member1")
	private Member member1;

	@Autowired
	@Qualifier(value="member2")
	private Member member2;

	@GetMapping("/member/detail")
	public String memberDetail(HttpServletRequest request) {
		
		request.setAttribute("member1", member1);
		request.setAttribute("member2", member2);
		
		return "member/detail";
	}
	
}
