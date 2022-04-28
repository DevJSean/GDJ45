package ex17_abstract;

public class Marine extends GameUnit {

	// field

	// constructor
	public Marine(String name) {
		super(name, 50, 5); // 마린, 에너지 100, 공격력 10
	}

	// method
	@Override
	public void attack(GameUnit unit) {      // 여기서 unit은 상대편
		// 30%의 확률로 한 번에 unit을 죽인다.
		if(Math.random() < 0.3) {
			unit.setEnergy(0);
			System.out.println(unit.getName() + "가 KO되었다.");
		} else {
			unit.setEnergy(unit.getEnergy() - this.getPower());
			System.out.println(unit.getName() + "의 남은 에너지는 " + unit.getEnergy());
		}
	}

	
}
