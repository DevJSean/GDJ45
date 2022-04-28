package ex07_exception_class;

public class Employee {

	//field
	private long empNo;  //사원번호
	private String name; // 성명
	
	//constructor	
	public Employee(long empNo, String name) {
		super();
		this.empNo = empNo;
		this.name = name;
	}
	
	//method
	
	//getter setter
	public long getEmpNo() {
		return empNo;
	}
	public void setEmpNo(long empNo) {
		this.empNo = empNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// toString
	@Override
	public String toString() {
		return "[empNo=" + empNo + ", name=" + name + "]";
	}
		


}
