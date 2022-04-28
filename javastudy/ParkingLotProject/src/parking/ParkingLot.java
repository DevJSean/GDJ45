package parking;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ParkingLot {

	//field
	private String name;
	private List<Car> cars; 
	private Scanner sc;

	//constructor
	public ParkingLot(String name) {
		this.name = name;
		this.cars = new ArrayList<Car>();
		this.sc = new Scanner(System.in);
	}
	
	//method
	
	public void addCar() {

		System.out.println("=== 차량 추가하기 ===");
		System.out.print("차량번호 >>> ");
		String carNo = sc.next();
		System.out.print("모델 >>> ");
		String model = sc.next();
		boolean res = cars.add(new Car(carNo, model));
		if(res) { //if(res == true)
			System.out.println(carNo + "차량이 추가되었습니다.");
		} else {
			System.out.println(carNo + "c차량이 추가되지 않았습니다.");
		}
		System.out.println();
	}
      
	public void deleteCar() throws RuntimeException{       // 모델명 비교를 안함. 인스턴스를 직접 전달하는 방법은 안됨.
		
		System.out.println("=== 차량 삭제하기 ===");
		System.out.print("차량번호 >>> ");
		String carNo = sc.next();
		for(int i = 0, size = cars.size(); i < size; i++) {
			Car car = cars.get(i); // 자동차 인스턴스를 하나씩 꺼내오기
			if(carNo.equals(car.getCarNo())) {
				cars.remove(i);
				System.out.println(carNo + " 차량이 삭제되었습니다.");
				System.out.println();
				return; //반복문에서 제거하는 기능을 사용할 때 무조건 return;이 있어야 한다.
			}
		}
		throw new RuntimeException("대상 차량이 존재하지 않습니다.\n"); // 이 줄을 if문의 else로 넣으면 for문의  i++ 부분에 dead code가 뜬다. 
																		// 반복문의 형태를 띄고 있지만 반복문이 아니게 된다.
		/*																// 첫번째 자동차의 번호가 입력받은 번호와 다르면 다음 차로 비교해야되는데, else로 예외처리 되고 끝나버린다.
		for(Car car : cars) {   // 인스턴스를 전달하는 방법은 안되고, 인덱스를 받아야 함.
			if(carNo.equals(car.getCarNo())) {
				cars.remove(car);    여기서는 equals가 필요 없다. car변수에는 주소 값들이 들어있다. 주소 값으로 지우는 것이라 equals 필요 없다
				return;
			}
		}
		throw new RuntimeException("대상 차량이 존재하지 않습니다.");
		*/
	}																	
																		
	
	
	
	
	
	
	
	
	
	
	public void printAllCars() throws RuntimeException{ 
		
		System.out.println("=== 전체 조회하기 ===");
		System.out.println(name + " 차량 목록"); 
		if(cars.isEmpty())
			throw new RuntimeException("주차장에 등록된 차량이 없습니다.\n");
		for(Car car : cars)
			System.out.println(car); // Car클래스의 toString 동작
		System.out.println();
	}
	
	public void manage() { 
		while(true) {
			try {
				System.out.print("1.추가 2.삭제 3.전체 0.종료 >>> ");
				int no = sc.nextInt(); // InputMismatchException 발생 가능성.
				sc.nextLine(); // 에넡키 제거 목적
				switch(no) {
				case 1: addCar(); break; //break; 없으면 밑의 경우들도 실행됨.
				case 2: deleteCar(); break;
				case 3: printAllCars(); break;
				case 0: System.out.println("프로그램 종료"); return;
				default: throw new RuntimeException("존재하지 않는 메뉴입니다.\n");
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("메뉴 입력은 정수입니다.\n");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
	}//manage()
	
}//class ParkingLot
