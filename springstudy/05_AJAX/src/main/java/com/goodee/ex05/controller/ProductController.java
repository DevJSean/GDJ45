package com.goodee.ex05.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.ex05.domain.ProductDTO;
import com.goodee.ex05.service.ProductService;


@Controller
public class ProductController {

	private ProductService productService; // root-context.xml의 bean id와 이름 맞춘다.

	// DI를 사용하는 경우(스프링 컨테이너에 있는 bean을 스프링이 주입하는 방법)  @Autowired
	// root-context.xml에 등록한 Bean을 스프링이 가져오는 방법
	// ↓  1. 필드 2. 생성자 3. setter 방식 중    3. setter주입 방식 사용
	@Autowired
	public void setService(ProductService productService) { // bean이 ProductService service 매개변수로 들어온다
		this.productService = productService;
	}
	
	
	@GetMapping(value="/product/list1", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<ProductDTO> list1() {
		return productService.list1(); // List 반환 값이 produces에 의해서 JSON 데이터로 변환되어야 함을 나타내고,
	}								   // List -> JSON 변환을 위해 jackson이 자동으로 개입한다.
									   // 반환이 JSP 이름이 아니라 값임을 나타내기 위해서 @ResponseBody 애너테이션이 필요하다.
	
	@GetMapping(value="/product/list2", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<Map<String, Object>> list2() {
		return productService.list2();
	}
	
	@GetMapping(value="/product/list3", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> list3() {
		return productService.list3();

	}
}
