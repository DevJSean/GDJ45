package com.goodee.ex01.java01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
	@Configuration
	Bean을 만드는 java class 파일
	spring bean configuration file과 같은 역할을 한다.
*/
@Configuration

public class SpringBeanConfig {

	// 메소드 1개 = bean 1개
	
	// bean을 만드는 메소드는 모두 @Bean이 필요하다.
	
	/* Song */
	
	// 1. 반환 타입 : Song        <bean class="com.goodee.ex01.java01.Song" ...
	// 2. 메소드명  : mySong      <bean ... id="mySong">
	
	@Bean  // bean을 만드는 메소드라는 뜻
	public Song mySong() {
		
		// Song 객체를 만들어서 반환하기
		// setter injection을 하든 constructor injection을 하든 자유
		// song은 setter, Singer constructor
		
		Song res = new Song();
		res.setTitle("좋은 사람"); // setter injection <property name="title" value="좋은사람" />
		res.setGenre("balad");     // setter injection <property name="genre" value="balad" />
		
		return res;
	}
	
	@Bean
	public Singer mySinger() { // <bean class="Singer" id="mySinger">
		
		// Singer 객체를 만들어서 반환하기
		
		Singer singer = new Singer("김형중", mySong()); // constructor injection 
		
		// <bean class="Singer" id="mySinger">
		//     <constructor-arg value="김형중" />
		//     <constructor-arg ref="mySong" />
		// </bean>

		return singer;
	}
}
