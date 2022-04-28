package ex04_has_a;

public class Radio {

	// field
	private RemoteCon remotecon; // 그냥 쓰면 null 값임.

	// constructor   null값이 되지 않도록 필요
	public Radio(RemoteCon remotecon) {
		this.remotecon = remotecon;
	}
	
	public void chUp() {
		remotecon.chUp();  // 그냥 쓰면 null.chUp();이 되어버림. Main에 new RemoteCon이 필요하다.

	}

	public void chDown() {
		remotecon.chDown();
	}
	
}
