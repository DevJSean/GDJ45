package com.goodee.ex01.xml06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml/context06.xml");
											//GenericXmlApplicationContext이랑 같다
		
		GymMember member = ctx.getBean("gymMember", GymMember.class);
		
		member.info(); // 이름, 등록 과정, 키, 몸무게, bmi, 건강상태 출력하기
		
		ctx.close();
	}

}
