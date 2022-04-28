package ex04_instance_array;

public class CompanyMain {

	
	public static void main(String[] args) {


		// Employee를 최대 5명 가질 수 있는 Company 생성
		
		Company company = new Company(5);
		//                       ↑ 생성자
		//                      이것을 보면  public Company(int n) { } 이렇게 해야 한다는 것이 나옴.
		
		// 직원 추가
		company.addEmployee(new Employee("이사원", "기획부"));
		company.addEmployee(new Employee("김과장", "개발부"));
		company.addEmployee(new Employee("박대리", "영업부"));
		company.addEmployee(new Employee("최과장", "기술부"));
		company.addEmployee(new Employee("박대리", "개발부"));
		
		
		// 반환타입이 있는지 없는지 알아내는 방법
		// company.printAllEmployee();
		// 메소드 호출 결과를 저장할 변수가 없으니 반환 타입이 없다. -> void 타입임.
		// int a = company.printAllEmployee()
		// 메소드 실행 결과가 a에 저장되어야 하니까 int가 반환타입임
		
		// 모든 직원 정보 출력 (총 직원 수 세기)
		company.printAllEmployee();
		
		// 박대리 직원 조회
		company.inquiry("박대리");
		
		// 박대리 직원 해고                         배열에서 삭제하는 기능 - 배열의 중간 부분을 삭제한다고 치면, 
		company.fireEmployee("박대리", "개발부"); //그 뒷 데이터를 복사해서 덮어쓰고 마지막 데이터를 null값으로 바꾸는 식.
		
		
	}

}
