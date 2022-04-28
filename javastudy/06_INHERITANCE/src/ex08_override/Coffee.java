package ex08_override;

public class Coffee {

	// field
	private String bean; // 원두
	
	// constructor
	public Coffee(String bean) {
		this.bean = bean;
	}
	
	// method
	public void taste() {
		System.out.println("씹는맛");
	}
	
	public void info() {
		System.out.println("원두: " + bean);
	}
	
	
	
	
	
	
}
