package com.goodee.ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Controller4 {

	// redirect 하는 방법
	
	// redirect는 JSP로 이동하지 않는다.
	// redirect는 다른 매핑으로 이동한다.


	////////////////////////////////////////////////////////////////////////////
	//  forward는 JSP로 이동                                                  //
    //             return "list"; -> /WEB-INF/views/list.jsp                  //
	//  redirect는 매핑으로 이동                                              //
    //             return "redirect:/list"; -> http:localhost:9090/ex04/list  //
	////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 이렇게 하면 안됨
	// redirect는 GetMapping이 두 번
	@GetMapping("/list1")                
	public String list1(HttpServletRequest request) { // Model model은 forward할 때 쓰기 때문에 필요 없다.
		// forward
		// return "list"; list.jsp로 forward 이동하기
		
		// redirect는 request 전달해 주지 않는다.
		// request나 model에 저장해도 최종 목적지에 전달되지 않는다.
		request.setAttribute("page", request.getParameter("page")); 
		
		// redirect                 /WEB-INF/views/list.jsp가 아니고
	  	                         // http:localhost:9090/ex04/list2 로 인식한다. mapping으로 이동
		return "redirect:/list2"; // @GetMapping("/list2") 매핑으로 이동하라는 의미
	}                            
	@GetMapping("/list2")
	public String list2() {
		return "list";      // list.jsp로 forward
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	// redirect는 GetMapping이 두 번
	@GetMapping("/list3")
	public String list3(HttpServletRequest request) {
		
		// redirect할 때 데이터를 전달하려면 파라미터를 붙여줘야 한다.
		String page = request.getParameter("page");
		
		return "redirect:/list4?page=" + page;
	}
	@GetMapping("/list4")
	public String list4(HttpServletRequest request, Model model) {
		
		String page = request.getParameter("page");
		
		model.addAttribute("page", page);
		
		return "list";
	}
	*/

	// redirect는 GetMapping이 두 번
	@GetMapping("/list3")
	public String list3(int page) {
		
		// redirect할 때 데이터를 전달하려면 파라미터를 붙여줘야 한다.
		
		return "redirect:/list4?page=" + page;
	}
	@GetMapping("/list4")
	public String list4(int page, Model model) {
		
		model.addAttribute("page", page);
		
		return "list";
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	// redirect 할 때 데이터 전달하는 스프링의 방법
	// 1. Model 대신 RedirectAttributes 인터페이스를 사용한다.
	// 2. addAttribute() 대신 addFlashAttribute() 메소드를 사용한다.
	
	@GetMapping("/list5")
	public String list5(int page, 
						RedirectAttributes redirectAttributes) { // redirect 할 곳으로 데이터를 전송해 줄 수 있다.
		
		// addAttribute()를 쓰면 redirect 할 때 전달이 안 된다.
		// addFlashAttribute()를 써야 한다.
		redirectAttributes.addFlashAttribute("page", page); // page 파라미터를 안 붙여도 전달된다.
		
		return "redirect:/list6";
	}
	@GetMapping("/list6")
	public String list6() {
		return "list"; // list.jsp로 forward 한다
	}


}