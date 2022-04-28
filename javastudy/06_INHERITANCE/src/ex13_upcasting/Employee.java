package ex13_upcasting;

public class Employee {

	// field
	private String name;

	// constructor
	public Employee(String name) {
		this.name = name;
	}
	
	// method
	// getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPay() {
		return 0;
	}
}
