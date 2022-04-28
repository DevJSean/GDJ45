package prac2;

public class Tv {

	// field
	private Remocon remocon; //기본 null 값


	
	//constructor
	//디폴트 생성자니까 안만들어도 된다.
	
	// getter & setter
	public Remocon getRemocon() {
		return remocon;
	}

	public void setRemocon(Remocon remocon) {
		this.remocon = remocon;
	}
	
	// method
	public void chUp() {
		remocon.chUp();
	}
	
	public void chDown() {
		remocon.chDown();
	}
	
	public void volUp() {
		remocon.volUp();
	}
	
	public void volDown() {
		remocon.volDown();
	}
}
