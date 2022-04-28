package prac1;

public class Gun {

	// field
	private String model; // null
	private int bullet;  // 0, 현재 총알
	private final int FULL_BULLET = 6;  // 최대 총알, final처리는 더 이상 값이 바뀌지 않는 최종 값(상수 값)이라는 뜻. 상수 값는 대문자로 쓰도록 약속됨.
	
	// constructor
	public Gun(String model, int bullet) {
		this.model = model;
		this.bullet = bullet > FULL_BULLET ? FULL_BULLET : bullet;  // 최대 총알만큼만 저장 가능
	}
	
	// 장전 
	public void reload(int bullet) {
		if (this.bullet + bullet <= FULL_BULLET) {
			this.bullet += bullet;
			System.out.println(bullet + "발 장전되었다. 현재 " + this.bullet + "발");
		} else {
			int realBullet = FULL_BULLET - this.bullet;
			this.bullet = FULL_BULLET;
			System.out.println((realBullet) + "발 장전되었다. 현재 " + this.bullet + "발");
		}
	}
	// this.bullet+=bullet;
	// if(this.bullet > FULL_BULLET)
	//    this.bullet= FULL_BULLET);
	
	
	// 쏘기
	public void shoot() {
		if (bullet > 0) {
			bullet--;
			System.out.println("탕! " + bullet + "발 남았다.");
		} else {
			System.out.println("총알이 없다.");
		}
	}
	
	// 출력
	public void info() {
		System.out.println(model + "(" + bullet + ")");
	}
	
}
