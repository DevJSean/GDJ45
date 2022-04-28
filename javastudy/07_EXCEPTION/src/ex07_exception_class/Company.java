package ex07_exception_class;


import java.util.InputMismatchException;
import java.util.Scanner;


public class Company {

	//field
	private String name;  //회사명
	private Employee[] employees;
	private int idx;
	private Scanner sc;
	
	//constructor
	public Company(String name, int count) {      
		this.name = name;
		this.sc = new Scanner(System.in);
		this.employees = new Employee[count]; // count를 매개 변수로 받고 그만큼 배열의 길이를 생성하겠다.
	
	}

	// 추가
	// 배열의 Full Check 필요, 에러코드 1001
	public void addEmployee() throws EmployeeException{
		if(idx == employees.length)
			throw new EmployeeException("사원 추가 불가", 1001);
		System.out.println("===사원 추가===");
		System.out.print("고용 형태(1.정규 2.비정규) >>> ");
		System.out.print("사원번호 >>> ");
		long empNo = sc.nextLong();
		System.out.println("사원명 >>> ");
		String name = sc.next();
		switch(sc.nextInt()) {
		case 1: 
			System.out.print("기본급 >>> ");
			int salary = sc.nextInt();
			employees[idx++] = new Regular(empNo, name, salary);
			break;
		case 2:
			System.out.print("기본급 >>> ");
			int payPerHour = sc.nextInt();
			employees[idx++] = new Temporary(empNo, name, payPerHour);
			break;
		}
		System.out.println(name + "님이 추가되었습니다.");
	}
	
	// 삭제
	// 배열의 Empty Check 필요, 에러코드 2001
	public void removeEmployee() throws EmployeeException {
		if(idx == 0 )
			throw new EmployeeException("사원 삭제 불가", 2001);
		System.out.println("===사원 삭제===");
		System.out.print("삭제할 사원번호 >>> ");
		long empNo = sc.nextLong();
		for(int i = 0; i < idx; i++) {
			if(empNo == employees[i].getEmpNo()) {
				System.arraycopy(employees, i + 1, employees, i, idx -1 - i); // System.arraycopy() 끌어서 덮어쓰기 이용해서 배열의 요소 삭제하기
				employees[--idx] = null;// 마지막 인덱스 값은 항상 null값이 들어있어야 한다.
				System.out.println("사원번호" + empNo + "님을 삭제했습니다.");
				return;
			}
		}
		System.out.println("사원번호" + empNo + "님은 없습니다.");
	}
	
	
	// 조회
	// 배열의 Empty check 필요, 에러코드 3001
	public void findEmployee() throws EmployeeException {
		if(idx == 0)
			throw new EmployeeException("사원 조회 불가", 3001);
		System.out.println("===사원 조회===");
		System.out.print("조회할 사원 번호 >>> ");
		long empNo = sc.nextLong();
		for(int i = 0; i < idx; i++) {
			if(empNo == employees[i].getEmpNo()) {
				System.out.println("조회된 사원 " + employees[i]);  // employees[i]을 .getName() 쓰지 않고 단순 출력시키면 
				return;	  											// (Employee에 오버라이드 한)toString이 동작한다.
			}
		}
		System.out.println("사원번호 " + empNo + " 사원이 없습니다.");
	}

	
	// 전체조회
	// 배열의 Empty Check 필요, 에러코드 3001
	public void findAllEmployees() throws EmployeeException {
		if(idx == 0)
			throw new EmployeeException("사원 조회 불가", 3001);
		System.out.println("===전체 사원 목록===");
		for(int i = 0; i < idx; i++)
			System.out.println((i + 1) + "번째 사원 " + employees[i]);	//오버라이드 된 toString이 사용됨.
	}

	// 메뉴
	public void menu() {
		System.out.println("===========================");
		System.out.println("[" + name + "] 관리프로그램");
		System.out.println("====== 1. 사원 추가 =======");
		System.out.println("====== 2. 사원 삭제 =======");
		System.out.println("====== 3. 사원 조회 =======");
		System.out.println("====== 4. 전체 조회 =======");
		System.out.println("====== 0. 관리 종료 =======");
		System.out.println("===========================");
	}
	
	
	public void manage() {

		while(true) {
			try {
				menu();
				System.out.print("선택(1,2,3,4,0) >>> ");
				switch(sc.nextInt()) {
				case 1: addEmployee(); break; // 반복문 중지 - > 무한루프로 다시 실행
				case 2: removeEmployee(); break;
				case 3: findEmployee(); break;
				case 4: findAllEmployees(); break;
				case 0: System.out.println("프로그램 종료"); return; // 메소드의 종료
				default: System.out.println("잘못된 선택입니다.");
				}
			} catch(InputMismatchException e) {
				System.out.println("잘못된 입력입니다. 다시 시도하세요.");
				sc.next();
			} catch(EmployeeException e) {
				System.out.println(e.getMessage() + "[에러코드 : " + e.getErrorCode() + "]");
			}
		} // while(true)
		
	} //manage()
	
} // class company {}
