package ex13_upcasting;

public class Temporary extends Employee {

	// field
	private int payPerHour; // 시급
	private int times; // 일 한 시간

	// constructor
	public Temporary(String name, int payPerHour, int times) {
		super(name);
		this.payPerHour = payPerHour;
		this.times = times;
	}
	
	// method
	@Override
	public int getPay() {
		return payPerHour * times;
	}
	
}
