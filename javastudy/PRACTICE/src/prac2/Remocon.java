package prac2;

public class Remocon {
	
	// field
	private int ch;  // 기본 0, 0~11번 
	private int vol; // 기본 0, 0~5사이 값
	private final int MAX_CH = 11; //최대 채널은 11번까지다.
	private final int MAX_VOL = 5; //최대 음량은 5이다.
	
	// constructor
	// 디폴트 형태이기 때문에 안만들어도 됨
	
	// method
	public void chUp() {
		if(ch == MAX_CH) {
			ch = 0;
			return;
		}
		ch++;
		System.out.println("현재 채널 : " + ch + "번");
	}
	
	public void chDown() {
		if(ch == 0) {
			ch = MAX_CH;
			return;
		}
		ch--;
		System.out.println("현재 채널 : " + ch + "번");
	}
	
	public void volUp() {
		if(vol < MAX_VOL) {
			vol++;
		}
	}
	
	public void volDown() {
		if(vol > 0)
			vol--;
	}
	
	
}
