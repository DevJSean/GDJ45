package com.goodee.ex01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex01Application {

	// Spring Boot 앱을 실행(서버 돌리기)시켜주는 녀석이라 지우면 안 된다.
	public static void main(String[] args) {
		SpringApplication.run(Ex01Application.class, args);
	}

}
