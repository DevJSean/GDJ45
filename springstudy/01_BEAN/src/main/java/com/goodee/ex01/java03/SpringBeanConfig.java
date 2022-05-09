package com.goodee.ex01.java03;

import org.springframework.context.annotation.Bean;

public class SpringBeanConfig {

	@Bean(name = "calc") // <bean id="calc">
	public Calculator aa() {
		return new Calculator();
	}

	@Bean(name = "gugudan") // <bean id="gugudan">
	public Gugudan bb() {
		return new Gugudan(aa(), 3, 5);
	}
	
}
