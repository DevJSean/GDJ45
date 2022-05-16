package com.goodee.ex05.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.ex05.domain.MemberDTO;
import com.goodee.ex05.service.MemberService;

@Controller
public class MemberController {

	// 컨트롤러는 언제나 Service 호출한다.
	// 그래서 Service를 field로 등록한다.
	
	// DI를 사용 안 하는 경우
	// 개발자가 직접 필요한 Bean을 생성하는 방법
	// private MemberService memberService = new MemberServiceImpl();
	
	// DI를 사용하는 경우(스프링 컨테이너에 있는 bean을 스프링이 주입하는 방법)  @Autowired
	// root-context.xml에 등록한 Bean을 스프링이 가져오는 방법
	// ↓  1. 필드 2. 생성자 3. setter 방식 중   1. 필드 주입 방식 사용
	@Autowired
	private MemberService memberService; // root-context.xml의 bean id와 이름 맞춘다.

	
	// 컨트롤러의 메소드는 기본적으로 JSP의 이름을 반환한다.
	// MVC 처리, 페이지 이동, JSP 이름
	
	// Ajax는 JSP이름을 반환하는 것이 아니라 
	// 자신을 호출한 JSP로 값을 반환하는 구조이다.
	// AJAX 처리, 페이지 이동X, 반환할 값
	
	// 값을 반환하기 위해서는
	// @ResponseBody 애너테이션이 필요하다.
	
	@GetMapping(value="/member/detail1",
			    produces="text/plain; charset=UTF-8")   // 이 메소드가 반환하는 건 텍스트라는 뜻 (응답 타입 response.setContentType)
	@ResponseBody                                       // @ResponseBody를 붙인 메소드가 반환하는 건 JSP이름이 아니라 어떤 값(텍스트, XML,JSON 등)이다.
	public String detail1(HttpServletRequest request) { // 파라미터 id와 pw를 request로 받는다.
		String res = memberService.detail1(request);    // 파라미터 id와 pw를 MemberServiceImpl 클래스의 detail1로 보냈다. 그 결과를 res에 저장한다.
		return res;                                     // memberService의 detail1() 메소드에서 만든 텍스트를 member.jsp로 반환한다.
	}
	
	
	@GetMapping(value="/member/detail2",
			    produces="application/json; charset=UTF-8")  // 내가 반환하는 값은 JSON입니다.
	@ResponseBody // 이 메소드의 반환은 JSP 이름이 아니라 값이다.
	// 반환타입 MemberDTO는 jackson에 의해서 JSON 데이터로 자동 변환된다.
	public MemberDTO detail2(@RequestParam(value="id") String id,   // 파라미터 id를 String id에 저장
			                 @RequestParam(value="pw") String pw) { // 파라미터 pw를 String pw에 저장
		MemberDTO member = memberService.detail2(id, pw);
		
		// Jackson이 하는 일
		// 자바 객체 member를 자동으로 {"id" : 아이디, "pw" : 비밀번호} 로 만들어 준다.
		
		return member; // 자바 객체를 member.jsp로 반환하는데, 이 때 jackson이 개입해서 member를 {"id" : 아이디, "pw" : 비밀번호}로 바궈준다.
		               // 물론, JSON으로 바꿔서 보내라고 알려줘야 하는데, 그건 produces에서 처리한다.
		// 실제 return은 
		// return {"id" : 아이디, "pw" : 비밀번호}; 인 것이다.
	}
	
	
	@GetMapping(value="/member/detail3",
			    produces="application/json; charset=UTF-8") // 내가 반환하는 값은 JSON입니다.
	@ResponseBody // 이 메소드의 반환은 JSP 이름이 아니라 값이다.
	// 반환 타입 Map은 jackson에 의해서 JSON데이터로 자동 변환된다.
	public Map<String, Object> detail3(MemberDTO member) { // MemberDTO member의 setId와 setPw가 파라미터 id와 pw를 받아준다.
		
		Map<String, Object> res = memberService.detail3(member);
		 
		return res; // Map을 반환하고 있지만, produces에서 반환타입이 JSON이라고 했기 때문에, jackson이 개입해서 Map을 JSON 데이터로 바꿔준다.
		            // (단, @ResponseBody와 @GetMapping의 produces 속성이 있어야 한다)
	}
	
	
	@PostMapping(value="/member/detail4",
			    produces="application/json; charset=UTF-8")
	@ResponseBody
	// JSON 데이터가 요청의 본문에 포함된 상태로 Controller로 왔다.
	// 컨트롤러는 이런 데이터를 파라미터(parameter)로 처리할 수 없다.
	// @RequestBody 애너테이션을 이용하면 요청의 본문에 포함된 데이터를 받을 수 있다.
	
	// jackson을 사용하고 있기 때문에
	// 컨트롤러로 전달된 JSON 데이터는 MemberDTO 또는 Map으로 받으면 된다.
	
	// Map으로 보내고                     Map으로 받는 게 제일 이상적이다.
	public MemberDTO detail4(@RequestBody Map<String, Object> map) {
		
		MemberDTO member = memberService.detail4(map);
		
		return member; // Map이 JSON으로 변환돼서 반환되려면
		               // @ResponseBody : 내가 변환하는 건 JSP이름이 아니라 어떤 값이다.
		               // produces="application/json" : 내가 반환하는 건 JSON 데이터이다.
	}
}
