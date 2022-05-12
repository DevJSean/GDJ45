package com.goodee.ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.ex04.domain.Board;

@Controller
public class Controller2 {

	/*
	  ModelAndView 클래스
	  
	  1. 스프링2 이전에 주로 사용된 클래스
	  2. Model을 통해서 전달할 값을 저장하고, View를 통해서 이동할 JSP를 결정
	  3. ModelAndView는 이제 사용하지 않는다. Model 쓰면 된다.
	  
	  4. ModelAndView와 Model의 비교(값을 가지고 이동하려고 하는 것은 같다.)
	     1) ModelAndView
	        public ModelAndView 메소드() {
	        	ModelAndView mav = new ModelAndView();
	        	mav.addObject("속성", 전달할 값);
	        	mav.setViewName("JSP이름");
	        	return mav;
	        }
	  
	  	 2) Model
	  	    public String 메소드(Model model) {
	  	    	model.addAttribute("속성", 전달할 값);
	  	    	return "JSP이름";
	  	    }
	*/
	
	@GetMapping("/add1") // <a href="${contextPath}/add1?title=공지사항1&hit=10">
	public ModelAndView add1(HttpServletRequest request) {   // (request), (String title, Long hit), (Board board)
		
		String title = request.getParameter("title");
		Long hit = Long.parseLong(request.getParameter("hit"));
		
		Board board = Board.builder().title(title).hit(hit).build();
				
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board); // Model에 속성 board를 저장한다.
		mav.setViewName("add");        // add.jsp로 이동한다.
		
		return mav;                    // forward 하기 때문에 add.jsp로 board를 전달한 상황이다.
	}
	
	@GetMapping("/add2") // <a href="${contextPath}/add2?title=필독&hit=9">
	public ModelAndView a2(String title, Long hit) { // @RequestParam 애너테이션을 생략할 수 있다.
		
		Board board = Board.builder().title(title).hit(hit).build();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("add");
		
		return mav;
	}
	
	@GetMapping("/add3")  // <a href="${contextPath}/add3?title=내용&hit=8">
	public ModelAndView add3(Board board) { // Board 클래스의 setTitle, setHit 가 동작하면서 파라미터를 받아준다.
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("add");
		
		return mav;
	}
	
	
}



