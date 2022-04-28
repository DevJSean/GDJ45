package practice3;

public class Animal {

	// field
	private String name;

	//constructor
	public Animal(String name) {
		super();
		this.name = name;
	}

	// method
	
	// toString 오버라이드 : source 메뉴 
	@Override
	public String toString() {
		return "[name=" + name + "]";
	}

	// getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
