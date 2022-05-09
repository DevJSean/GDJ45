package com.goodee.ex01.java05;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SpringBeanConfig {

	@Bean(name="gun2")
	public Gun a() {
		return new Gun("AK-47", 20);  // constructor-injection
	}
	
	@Bean(name="soldier2")
	public Soldier b() {
		Map<String, String> army = new HashMap<String, String>();
		army.put("name", "이기자");
		army.put("location",  "화천");
		return new Soldier("박중사", a(), army);  // constructor-injection
	}
	
}
