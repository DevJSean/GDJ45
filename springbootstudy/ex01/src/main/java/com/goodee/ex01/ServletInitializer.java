package com.goodee.ex01;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	// servlet 사용 = jsp 사용    만약 jsp 사용 안 한다면 이 클래스 파일 자체가 없을 것
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Ex01Application.class);
	}

	
}
