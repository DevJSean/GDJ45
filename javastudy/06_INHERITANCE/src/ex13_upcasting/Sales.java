package ex13_upcasting;

public class Sales extends Regular {

	// field
	private int salesVolume;
	private double incentive;
	
	// constructor
	public Sales(String name, int salary, int salesVolume, double incentive) {
		super(name, salary);
		this.salesVolume = salesVolume;
		this.incentive = incentive;
	}
	
	// method
	public int getSalesPay() {   //밑에 getPay를 위한 메소드
		return (int)(salesVolume * incentive);
	}
	
	@Override
	public int getPay() {
		return super.getPay() + getSalesPay();
	}
}
