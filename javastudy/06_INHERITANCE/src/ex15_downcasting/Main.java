package ex15_downcasting;

public class Main {

	public static void main(String[] args) {

		// 10대의 자동차(Car, Ev, Hybrid))를 저장할 수  있는 배열 생성
		Car[] cars = new Car[10]; 

		// 10% 확률 Car, 30% 확률 Ev, 60% Hybrid를 만들어서 배열에 넣기
		for(int i = 0; i < cars.length; i++) {
			if(Math.random() < 0.1) // 0~10% 확률
				cars[i] = new Car();
			else if(Math.random() < 0.4)
				cars[i] = new Ev();
			else
				cars[i] = new Hybrid();
		}
		
		// car이면 drive() 호출
		// Ev이면 charge() 호출
		// Hybrid이면 addOil() 호출
		for(int i = 0; i < cars.length; i++) {  // cars[i] : 각 인스턴스, 안에 들어 있을 수 있는건 Car/Ev/Hybrid
			if(cars[i] instanceof Car) 			// 근데 여기서 cars[i] instanceof Car라는 if문을 먼저 써버리면 
				cars[i].drive();	   			// Car/Ev/Hybrid 모두 Car의 인스턴스라고 인식하고 첫 if문에서 전부 true가 나옴
			else if(cars[i] instanceof Ev)		// 해결하는 방법은 가장 후손(자식) 먼저 점검하는 식으로 바꿔야 한다.
				((Ev)cars[i]).charge();
			else if(cars[i] instanceof Hybrid)
				((Hybrid)cars[i]).addOil();
		}
		
		// 가장 아래 후손부터 확인해야 한다.
		for(int i = 0; i < cars.length; i++) { 
			if(cars[i] instanceof Hybrid) 			
				((Hybrid)cars[i]).addOil();	   			
			else if(cars[i] instanceof Ev)
				((Ev)cars[i]).charge();
			else if(cars[i] instanceof Car)
				cars[i].drive();
		}
		
	}

}
