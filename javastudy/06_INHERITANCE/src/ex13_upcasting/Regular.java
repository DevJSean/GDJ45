package ex13_upcasting;

public class Regular extends Employee {

	// field
	private int salary;

	// constructor
	public Regular(String name, int salary) {
		super(name);
		this.salary = salary;
	}
	
	// method
	@Override
	public int getPay() {
		return salary;
	}
	
}
