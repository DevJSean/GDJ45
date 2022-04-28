package phone;

import java.util.Scanner;

public class PhoneBook {

	//field
	private Phone[] pArray;
	private Scanner scanner;  // Scanner(유틸 패키지의 Scanner) scanner(인스턴스 이름)
	
	//constructor
	public PhoneBook() {
		scanner = new Scanner(System.in);
	}
	
	private void input() {
		
		System.out.print("인원수 >> ");
		int count = scanner.nextInt();
		System.out.println("");
		
		pArray = new Phone[count];  // 배열 길이 지정
		
	  //for(int i = 0; i < pArray.length; i++) {
		for(int i = 0; i < count; i++)	{
			System.out.print("이름과 전화번호(이름과 번호는 빈 칸 없이 입력) >> ");
			
			// pArray[i] 에는 name과 tel이라는 데이터를 저장한다.
			// 1. new phone(    ,   ); 생성자 이용 방식
			// 2. new phone();
			//    setName
			//    setTel
			String name = scanner.next();
			String tel = scanner.next();
			// 1. 생성자 방법
			pArray[i] = new Phone(name, tel);
			// 2. setter 방법
			pArray[i] = new Phone();  // 내용이 없는 빈 Phone 저장
			pArray[i].setName(name);
			pArray[i].setTel(tel);
		}	
		System.out.println("저장되었습니다.");
	}

	private String search(String name) {
		for(int i = 0; i <pArray.length; i++) {
			if(name.equals(pArray[i].getName())) {
				return pArray[i].getTel();              // 이름이 동일하면 전화번호 반환
			}
		}
		return null;     //찾아보고 같은 이름이 없으면 null값 반환
	}
	
	public void run() {
		input();
		
		while(true) {    //언제까지 입력할지 모르니까 무한루프
			System.out.println("검색할 이름 >> ");
			
			String name = scanner.next();
			if(name.equals("exit")) { // if(name.equalsIngoreCase("exit")) 대소문자 상관안해. String이니까 equals()비교
				System.out.println("프로그램 종료!");
				return; // return : run 메소드의 실행 종료, break: while(true)의 종료  동일한 효과
			}
			
			String tel = search(name);
			if(tel == null)
				System.out.println(name + "이 없습니다.");
			else
				System.out.println(name + "의 변호는 " + tel + "입니다.");
		}
	}
}
