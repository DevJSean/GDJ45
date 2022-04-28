package prac1;

// 경과시간을 맞추는 게임을 작성하시오. 첫 번째 <Enter>를 누르면 해당 시점의 초시간을
// 보여주고, 두 번재 <Enter>를 누르면 해당 시점의 초 시간을 다시 보여준다.
// 여기서 10초에 근접하도록 <Enter>를 누른 사람이 이기는 게임이다.

public class Ex07 {

	public static void main(String[] args) {

		Player player[] = new Player[2];
		player[0] = new Player("강아지");  //Player("강아지") 부분을 보고 생성자를 만들어야 함을 알아낼 수 있다.
		player[1] = new Player("고양이");

		int duration1 = player[0].turn();
		int duration2 = player[1].turn();

		System.out.print(player[0].getName() + "님 결과 " + duration1 + "초, ");
		System.out.print(player[1].getName() + "님 결과 " + duration2 + "초, 승자는 ");

		if (Math.abs(10 - duration1) < Math.abs(10 - duration2)) // Math.abs() 절댓값 구하기. 시간 간격이 작은 사람이 이김
			System.out.println(player[0].getName() + "님입니다.");
		else
			System.out.println(player[1].getName() + "님입니다.");
		
	}

}

	/*
	Player p1 = new Player("철수");
	Player p2 = new Player("영희");
	
	double res1 = p1.turn();
	double res2 = p2.turn();
	
	if(Math.abs(res1 - 10) < Math.abs(res2 - 10) ) 계산 결과에 부호는 필요 없다.(절댓값)
    	Sysout.out.println(p1.getName() + "님 승리" + res1 + "초 소요.");
    else
    	Sysout.out.println(p2.getName() + "님 승리" + res2 + "초 소요.");
    	
    */