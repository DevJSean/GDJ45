package ex17_abstract;

public abstract class GameUnit {

	// field
	private String name;
	private int energy;
	private int power;
	private boolean alive; // boolean isAlive라고 써도 같은 결과가 나온다

	
	// constructor
	public GameUnit(String name, int energy, int power) { 
		super();
		this.name = name;
		this.energy = energy;
		this.power = power;
		alive = this.energy > 0;  // alive는 따로
	}
	
	//method
	
	// getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEnergy() {
		return energy;
	}
	// 수정
	public void setEnergy(int energy) {
		this.energy = energy;
		if(this.energy < 0) 
			this.energy = 0;        // energy가 음수가 나오지 않도록 설정
		setAlive(this.energy > 0);  // energy가 0보다 크면 true
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public boolean isAlive() { // boolean 타입은 get이 안붙고 is가 자동으로 붙는다.
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	// 추상 메소드
	public abstract void attack(GameUnit unit);
	
}
