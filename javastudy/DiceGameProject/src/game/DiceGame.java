package game;

public class DiceGame {

	// field
	private Player[] players;
	private int idx;
	
	// constructor
	public DiceGame(int count) {
		players = new Player[count];
	}
	
	// method
	public void join(Player player) {
		if(idx == players.length) {
			System.out.println("더 이상 참여할 수 없습니다. 가득찼습니다.");
			return;
		}
		players[idx++] = player;
	}
	
	public void play() {
		int i = 0;
		while(true) {
			if(players[i].turn()) {
				System.out.println(players[i].getName() + "님 승리!");
				break;
			} else {
				i++;
				i %= players.length; // 3으로 나눈 나머지 값. 모듈러 연산
			}
		}

	}
	/* 내가 푼 방법
	public void play() {
		for(int i = 0; i < players.length; i++) {
			if(players[i].turn() == true) {
				System.out.println(players[i].getName()+ "님 승리!");
				break;
			}
			if(i == players.length - 1)
				i -= players.length;
		}

	}*/
}
