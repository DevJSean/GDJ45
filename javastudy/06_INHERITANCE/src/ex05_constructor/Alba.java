package ex05_constructor;

public class Alba extends Student{

	// field
	private String company;
	
	// constructor
	public Alba(String name, String school, String company) {
		super(name, school); // Student 클래스의 생성자를 "먼저" 호출해야 한다. super(name, school);을 this.company=company;보다 먼저 해야한다.
		this.company = company;
	}
	
	
	// method
	
	public void work() {
		System.out.println(company + "에서 일한다.");
		System.out.println(getName() + "가 " + company + "을 다닌다");
	}


	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
}
