package prac1;

// 다음 main 메소드를 참고하여 Gun 클래스와 Soldier 클래스를 구현하시오.

public class Ex10 {

	public static void main(String[] args) {
		
		Gun gun = new Gun("K2", 2);  // 총알이 2개 들어있고 모델이 K2인 gun
		Soldier soldier = new Soldier("람보", gun);  // 이름이 람보이고 gun을 가진 soldier
		
	//	Soldier soldier = new Soldier("람보", new Gun("K2", 2));  윗 두줄과 이 한줄은 동일하다.
		
		
		soldier.shoot();  // 빵야! 1발 남았다.
		soldier.shoot();  // 빵야! 0발 남았다.
		soldier.shoot();  // 헛빵!
		
		soldier.reload(3);  // 3발이 장전되었다. 현재 3발.
		soldier.reload(6);  // 3발이 장전되었다. 현재 6발.
		soldier.reload(6);  // 0발이 장전되었다. 현재 6발.
		
		soldier.info();


	}

}
