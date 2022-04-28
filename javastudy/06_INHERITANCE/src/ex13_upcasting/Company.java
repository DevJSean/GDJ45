package ex13_upcasting;

import java.util.Scanner;

public class Company {

	// field
	private Scanner sc; // 입력받는 스캐너 방식
	private Employee[] employees;
	private int idx;
	
	// Employee[] employees = new Employee[employeeCount]; 이게 아래 위로 나뉜 것.
	
	//constructor
	public Company(int employeeCount) {
		sc = new Scanner(System.in);
		employees = new Employee[employeeCount];
	}
	
	// method
	// 추가
	public void addEmployee() {
		if(idx == employees.length) {
			System.out.println("가득찼습니다.");
			return;
		}
		System.out.println("유형(1.정규 2.임시 3.영업) >>> ");
		switch(sc.nextInt()) {
		case 1: 
			employees[idx++] = new Regular("이대리", 200);
			break;
		case 2:
			employees[idx++] = new Temporary("김주임", 1, 300);
			break;
		case 3:
			employees[idx++] = new Sales("정과장", 50, 1000, 0.1);
			break;
		}
		System.out.println("직원이 추가되었습니다.");
	}
	
	// 삭제
	public void removeEmployee() {
		System.out.println("--직원 삭제--");
		System.out.println("삭제할 직원 번호(1~" + idx + ") >>> ");
		int findNo = sc.nextInt() - 1; //이러면 뒤에서 findNo -1 하지 않고 미리 하는 것.
		if(findNo < 0 || findNo > idx) {
			System.out.println("없는 직원입니다.");
			return;
		}
		// arraycopy(이 배열 객체를 복사, 이 객체의 이 부분(인덱스)부터 복사, 저 배열 객체에 붙여넣기, 저 배열 객체의 이 부분(인덱스)부터 붙이기, 복사할 배열 요소의 수) 
		System.arraycopy(employees, findNo + 1, employees, findNo, idx -1 -findNo); //lengh : idx -1 -findNo 사진 참고
		idx--; // 배열의 마지막 값 다음이 인덱스 idx임. null값 들어있음. 한명 삭제 했으니까 인덱스를 줄인다.
		employees[idx] = null; // 그리고 하나 줄인 idx 맨 마지막에 null값 넣는다.
		System.out.println("삭제 되었습니다.");
	}
	
	// 수정
	public void modifyEmployee() {
		System.out.println("--직원 수정--");
		System.out.println("수정할 직원 번호(1~" + idx + ") >>> ");
		int findNo = sc.nextInt();
		if(findNo < 1 || findNo > idx) {
			System.out.println("없는 직원입니다.");
			return;
		}
		System.out.print("새로운 직원 이름 입력 >>> ");
		String name = sc.next();
		employees[findNo - 1].setName(name);
		System.out.println("이름이 수정되었습니다.");
	}
	
	// 조회    개인이 중복 없는 데이터(주민번호, 사원번호..) 등을 가지고 있는 게 좋다. employees 배열의 인덱스 값을 사원번호처럼 처리
	public void findEmployee() {
		System.out.println("--직원 조회--");
		System.out.print("조회할 직원 번호(1~" + idx + ") >>> ");
		int findNo = sc.nextInt();
		if(findNo < 1 || findNo > idx) {
			System.out.println("없는 직원입니다");
			return;
		}
		System.out.println(employees[findNo - 1].getName() + " " + employees[findNo - 1].getPay()); // -1 하는 이유는 첫번째 직원은 인덱스 0이니까.
	}
	
	// 전체 조회
	public void findAllEmployees() {
		System.out.println("--전체 직원 조회--");
		if(idx ==0) {
			System.out.println("직원이 없습니다.");
			return;
		}
		for(Employee emp : employees) {
			if(emp != null)   // 향상 for문은 항상 null 값 처리해야한다.
				System.out.println(emp.getName() + " " + emp.getPay());
		}
	}
	
	// 메뉴
	public void menu() {
		System.out.println("1. 추가 2. 삭제 3. 수정 4. 조회 5. 전체조회 0. 종료");
	}
	
	// 실행
	public void mange() {
		while(true) {
			menu();
			System.out.print("선택 >>> ");
			switch(sc.nextInt()) {
			case 1: addEmployee(); break; //여기서 break는 switch를 끝내는 것. 다시 무한루프로 돌아가서 메뉴 선택창 나올 것.
			case 2: removeEmployee(); break;
			case 3: modifyEmployee(); break;
			case 4: findEmployee(); break;
			case 5: findAllEmployees(); break;
			case 0: System.out.println("프로그램 종료!"); return; // return은 manage 메소드의 void 반환 값, 무한루프를 종료시킨다. 
			default: System.out.println("잘못된 선택입니다!");
			}
		}
	}
	
}
