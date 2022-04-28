package quiz01;

public class School {

	//필드 field
	private String name;
	private String location;
	
	//생성자 constructor
	
	//school1부분
	/*
	public School(String name, String location) {
		this.name = name;
		this.location = location;
	}
	
	//school2부분
	public School() {
		
	}
	*/
	public School() {  // 빈 공간에 Ctrl+spacebar- school() - constructor
		
	}
	public School(String name, String location) {    // 빈공간에 우 클릭 - source - generate constructor using field
		super(); //지워도 되는 부분                     혹은 화면 상단에 source
		this.name = name;
		this.location = location;
	}

	//메소드 method
	//school1 부분
	/*
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	
	//school2 부분
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	*/
	public String getName() {    // 빈 공간에 우클릭 - source - generate Getters and Setters - select all
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
