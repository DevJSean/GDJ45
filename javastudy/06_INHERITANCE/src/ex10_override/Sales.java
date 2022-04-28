package ex10_override;

public class Sales extends Regular{ // Sales도 기본급으로 salary가 있으니 부모를 Regular로 둔다

	// field
	private int salesVolume;
	private double incentive;

	
	// constructor
	public Sales(String name, int salary) {  // regular의 생성자를 호출한다. generate constructor from superclass
		super(name, salary);                 // 위 필드들을 사용하지 않으니까

	}
	
	// method
	
	// setter getter
	public int getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}
	public double getIncentive() {
		return incentive;
	}
	public void setIncentive(double incentive) {
		this.incentive = incentive;
	}

	public int getSalesPay() {
		return (int)(salesVolume * incentive);  // 먼저 double 곱하기 계산을 하고 반환타입인 int로 형변환
	}
	
	@Override
	public int getPay() {
		// 기본급 + 판매수당
		return super.getPay() + getSalesPay(); // 기본급은 슈퍼클래스의 필드를 가져올 수 있는게 아니고, 슈퍼클래스의 메소드를 호출해서 해결한다.
	}



	
}
