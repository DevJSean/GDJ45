package ex07_exception_class;

public class Temporary extends Employee {
	
	//field
	private int payPerHour;

	//constructor
	public Temporary(long empNo, String name, int payPerHour) {
		super(empNo, name);
		this.payPerHour = payPerHour;
	}
	
	//method
	@Override
	public String toString() {
		return "Temporary " + super.toString() + "[payPerHour=" + payPerHour + "]";
	}
	
	
	
	
	
	
	
	

}
