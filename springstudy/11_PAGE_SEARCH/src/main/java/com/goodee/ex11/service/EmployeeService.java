package com.goodee.ex11.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmployeeService {

	public void findEmployees(HttpServletRequest request, Model model); // Controller에서 request, model을 넘긴 것, 넘겨야 한다.
																		// service에서 model에 정보를 실어서 반환할 계획.
	
	
}
