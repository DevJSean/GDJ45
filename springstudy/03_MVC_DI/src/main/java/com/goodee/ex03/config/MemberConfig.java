package com.goodee.ex03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex03.domain.Member;

@Configuration
public class MemberConfig {

	// 자바로 bean을 만드는 경우에는
	// bean의 이름(id)이 qualifier역할을 수행한다.
	
	
	@Bean(name="member1")
	public Member a() {
		Member res = new Member();
		res.setId("admin");
		res.setPw("123456");
		return res;
	}
	
	@Bean
	public Member member2() {
		Member res = new Member();
		res.setId("guest");
		res.setPw("qwer");
		return res;
	}
}
