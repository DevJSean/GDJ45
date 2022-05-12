package com.goodee.ex04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.ex04.domain.Product;

@Controller
public class Controller3 {

	@PostMapping("/remove1")
	public String request(HttpServletRequest request, Model model) {
		
		String name = request.getParameter("model");
		int price = Integer.parseInt(request.getParameter("price"));
		
		Product product = new Product(name, price);
		
		model.addAttribute("product", product);
		
		return "product";
	}
	
	@PostMapping("/remove2")
	public String requestParam(@RequestParam(value="model", required=false, defaultValue="제품명") String name,
							   @RequestParam(value="price", required=false, defaultValue="0") int price,
							   Model model) {
		model.addAttribute("product", new Product(name,price));
		
		return "product";
	}
	
	@PostMapping("/remove3")
	public String product(@ModelAttribute Product product) {
		
		return "product";
	}
}
