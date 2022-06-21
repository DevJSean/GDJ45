package com.goodee.ex01.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BbsService {

	public Map<String, Object> uploadSummernoteImage(MultipartHttpServletRequest multipartRequest);
	
	
}
