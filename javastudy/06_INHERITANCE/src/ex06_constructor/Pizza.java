package ex06_constructor;

public class Pizza {

	// field
	private String dough;
	private int cheese;
	
	// constructor
	public Pizza(String dough, int cheese) {
		this.dough = dough;
		this.cheese = cheese;
	}
	
	// method
	public String getDough() {
		return dough;
	}
	public void setDough(String dough) {
		this.dough = dough;
	}
	public int getCheese() {
		return cheese;
	}
	public void setCheese(int cheese) {
		this.cheese = cheese;
	}
}
