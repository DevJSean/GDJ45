package com.goodee.ex08.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex08.domain.BookDTO;
import com.goodee.ex08.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/book/list")
	public String list(Model model) {
		List<BookDTO> books = bookService.findBooks();
		model.addAttribute("books", books); 
		// model.addAttribute("books", bookService.findBooks()); 
		return "book/list";
	}
	
	@GetMapping("/book/detail")
	public String detail(@RequestParam Long book_no, Model model) {
		BookDTO book = bookService.findBookByNo(book_no);
		model.addAttribute("book", book);
		//model.addAttribute("book", bookService.findBookByNo(book_no));
		return "book/detail";
	}
	
	@GetMapping("/book/savePage")
	public String savePage() {
		return "book/save";
	}
	
	//성공, 실패 메시지
	@PostMapping("/book/save")
	public void save(BookDTO book, HttpServletRequest request, HttpServletResponse response) {
		int res = bookService.save(book);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('등록되었습니다')");
				out.println("location.href='" + request.getContextPath() + "/book/list'");
				out.println("</script>");             
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('등록되지 않았습니다.')");
				out.println("history.back()"); 
				out.println("</script>");
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//성공, 실패 메시지
	@PostMapping("/book/change")
	public void change(BookDTO book, HttpServletRequest request, HttpServletResponse response) {
		int res = bookService.change(book);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('수정되었습니다')");
				out.println("location.href='" + request.getContextPath() + "/book/list'");
				out.println("</script>");             
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('수정되지 않았습니다.')");
				out.println("history.back()"); 
				out.println("</script>");
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//성공, 실패 메시지
	@GetMapping("/book/remove")
	public void remove(@RequestParam Long book_no, HttpServletRequest request, HttpServletResponse response) {
		int res = bookService.remove(book_no);
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(res > 0) {
				out.println("<script>");
				out.println("alert('삭제되었습니다')");
				out.println("location.href='" + request.getContextPath() + "/book/list'");
				out.println("</script>");             
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('삭제되지 않았습니다.')");
				out.println("history.back()"); 
				out.println("</script>");
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// 트랜잭션 테스트용
	@GetMapping("/book/transaction/test")
	public String transaction() {
		bookService.transaction(); // 테스트 용 서비스 호출
		return "redirect:/book/list"; // 트랜잭션 테스트 후 목록으로 넘어가기
	}
	
	
	
}
