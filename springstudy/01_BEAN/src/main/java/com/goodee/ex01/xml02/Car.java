package com.goodee.ex01.xml02;

public class Car {

	// field
	private String model; // 모델명(xDrive 40d)
	private int price;    // 13140(만 원)
	private Engine engine;
	
	// method
	public void carInfo() {
		System.out.println("모델명: " + model);
		System.out.println("가격: " + price + "만원");
		engine.engineInfo();
	}
	
	// getter/setter
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Engine getEngine() {
		return engine;
	}
	
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
}
