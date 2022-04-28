package ex10_override;

public class Regular extends Employee {
	
	// field
	private int salary; // 정규직은 정해진 기본급이 있으니까 필드로 둔다

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
