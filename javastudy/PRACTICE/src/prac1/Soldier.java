package prac1;

public class Soldier {

	// field
	private String name;
	private Gun gun;
	
	// constructor
	public Soldier(String name, Gun gun) {
		this.name = name;
		this.gun = gun;
	}
	
	// method
	public void reload(int bullet) {
		gun.reload(bullet);
	}
	
	public void shoot() {
		gun.shoot();
	}
	
	public void info() {
		System.out.print(name + " : ");
		gun.info();
	}
	
}
