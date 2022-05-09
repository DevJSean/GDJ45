package com.goodee.ex01.xml06;

public class BMICalculator {

	// field
	private Calculator calculator;
	private double height; // 키(cm)
	private double weight; // 몸무게(kg)
	private double bmi;    // BMI : 몸무게(kg) / 키(cm) * 키(cm)
	private String health; // 건강상태
	//       BMI < 20 : 저체중
	// 20 <= BMI < 25 : 정상체중
	// 25 <= BMI < 30 : 과체중
	// 30 <= BMI      : 비만
	
	// height와 weight를 작성하면 자동으로 bmi와 health가 작성되는 종속관계이다.
	// 이때, setter로 이 종속관계를 해결하기에는 불편한 점이 많다.
	// 그래서 생성자로 해결한다.
	
	// constructor
	// height, weight 정보를 이용해서 bmi, health 값을 구해야 되므로 bmi, health 값은 받아 오지 않는다.
	public BMICalculator(Calculator calculator, double height, double weight) {
		// 전달 받은 정보 저장하기
		this.calculator = calculator;
		this.height = height;
		this.weight = weight;
		
		// bmi 계산하기
		height /= 100; // 키 단위를 cm -> m 수정하는 작업
		this.bmi = this.calculator.div(weight, this.calculator.mul(height, height)); // weight / (height * height)
		
		// health 계산하기
		if(this.bmi >= 30) { this.health = "비만"; }
		else if(this.bmi >= 25) { this.health = "과체중"; }
		else if(this.bmi >= 20) { this.health = "정상체중"; }
		else { this.health = "저체중"; }
	}
	
	// BMICalculator에서는 getter/setter가 하나도 쓰이지 않는다.
	
	/// info() 메소드
	public void info() {
		System.out.println("height : " + height + "cm");
		System.out.println("weight : " + weight + "kg");
		System.out.println("BMI : " + bmi);
		System.out.println("health : " + health);
		
		
	}
	
}
