package game;

// 세 명이 주사위를 세번씩 던져서 그 주사위가 같은 숫자가 나온 사람이 승리하는 게임.

public class DiceGameMain {

	public static void main(String[] args) {

		DiceGame game = new DiceGame(3); // 3명의 참가자
		
		
		game.join(new Player("호랑이")); // player를 join한다, new로 만들어야한다.
		game.join(new Player("코뿔소"));
		game.join(new Player("도마뱀"));

		
		game.play();
		
	}

}
