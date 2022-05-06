package com.goodee.ex01.xml02;

public class Engine {
	
	// field
	private String fuel;  	   // 연료(디젤)
	private double efficiency; // 연비(10.5)
	private int hp;            // 출력(340)
	private int cc;			   // 배기량(2993)

	// method
	public void engineInfo() {
		System.out.println("연료타입: " + fuel);
		System.out.println("연비: " + efficiency + "km/l");
		System.out.println("출력: " + hp + "hp");
		System.out.println("배기량: " + cc + "cc");
	}
	
	// getter/setter
	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}
	
	
}
