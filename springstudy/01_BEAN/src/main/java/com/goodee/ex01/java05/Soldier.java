package com.goodee.ex01.java05;

import java.util.Map;

public class Soldier {

	// field
    private String name;   // 이름
    private Gun gun;       // 총기
    private Map<String, String> army;  // 부대명 + 부대위치
    
    // info() 메소드
	public void info() {
		System.out.println("이름 : " + name);
		System.out.println("총기모델 : " + gun.getModel());
		System.out.println("총알수 : " + gun.getBullet());
		System.out.println("부대명 : " + army.get("name"));
		System.out.println("부대위치 : " + army.get("location"));
	}
    
    // default constructor
    // setter injection에서 사용됨.
    public Soldier() {
	}
    
    // constructor using fields
    // constructor injection에서 사용됨.
	public Soldier(String name, Gun gun, Map<String, String> army) {
		super();
		this.name = name;
		this.gun = gun;
		this.army = army;
	}
	
	// getter/setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gun getGun() {
		return gun;
	}
	public void setGun(Gun gun) {
		this.gun = gun;
	}
	public Map<String, String> getArmy() {
		return army;
	}
	public void setArmy(Map<String, String> army) {
		this.army = army;
	}
    
    
	
}
