package com.goodee.ex11.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmployeeService {

	// 전체조회
	public void getEmployees(HttpServletRequest request, Model model); // 목록을 가지고 forward할 것이기 때문에
																	   // 반환타입 없이 Model에 실어서 보내기
	// 검색
	public void findEmployees(HttpServletRequest request, Model model); // Controller에서 request, model을 넘긴 것이다, 넘겨야 한다.
																		// service에서 model에 정보를 실어서 반환할 계획.
	
	// 자동완성
	public Map<String, Object> autoComplete(HttpServletRequest request);
}
