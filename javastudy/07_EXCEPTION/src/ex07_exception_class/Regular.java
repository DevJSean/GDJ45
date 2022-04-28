package ex07_exception_class;

public class Regular extends Employee {

	//field
	private int salary; //기본급

	//constructor
	public Regular(long empNo, String name, int salary) {
		super(empNo, name);
		this.salary = salary;
	}

	//method
	@Override
	public String toString() {
		return "Regular" +  super.toString() + "[salary=" + salary + "]";
	}              //여기서 super는 Employee
	
	
	
	
	
	
}
