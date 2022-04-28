package ex20_interface;

public class Person {

	public void feed(Pet pet, String food) {
		System.out.println(pet.getName() + "에게 " +  food + "를 준다.");
	}
	
	public void walk(Walkable pet) { // 매개변수로 걸을 수 있는 애들만(타입이 Walkable인 애) 받자
		System.out.println(((Pet)pet).getName() + "와 산책하기."); // Walkable 타입으로 매개변수를 받았기 때문에 pet으로 downcasting 해야함.
	}
	
	
}
