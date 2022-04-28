package ex10_override;

public class Temporary extends Employee{
	
	// field
	private int payPerHour; // 시급
	private int times; // 일한 시간

	
	// constructor
	public Temporary(String name, int payPerHour) {      // 이름과 시급만 사용하고 얼마나 일했는지 times는 setter를 이용할 예정
		super(name);
		this.payPerHour = payPerHour;
	}


	// method
	
	// getter setter
	public int getPayPerHour() {
		return payPerHour;
	}
	public void setPayPerHour(int payPerHour) {
		this.payPerHour = payPerHour;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	
	@Override
	public int getPay() {
		return payPerHour * times;
	}
	
	
}
