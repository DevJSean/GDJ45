package ex04_instance_array;

public class Company {

	private Employee[] employees;
	private int idx;  //인덱스 (자동으로 최초 0값을 가짐), 실제로는 직원 수를 의미함.
	
	public Company(int n) {
		employees = new Employee[n];  // null 이 5개 생김
	}
	
	
	
	// Employee를 최대 5명 가질 수 있는 Company인데 그 이상 추가하면, 더 이상 직원을 뽑을 수 없습니다.
	// Employee employee = new Employee(..) 가 Company와 CompanyName에 분리된 것임
	public void addEmployee(Employee employee) {   //new Employee를 저장할 수 있는 매개변수 준비 
		if(idx == employees.length) {
			System.out.println("더 이상 직원을 뽑을 수 없습니다.");
			return; //addEmployee 메소드 종료. void는 반환 값(return)이 없다는 뜻. 
		}           //근데 반환 타입이 void인 메소드는 메소드를 끝내고 싶을 때 return; 코드로 종료 가능. 
		employees[idx++] = employee; // 인덱스 사용 후 증가 ( 0, 1, 2 ..)
	}
	
	
	
	
	// 직원 수 세기
	public void printAllEmployee() {
		System.out.println("총 직원 수는 " + idx + "명입니다.");   // 총 직원 수는 idx 값과 같다.
		for(Employee employee : employees) {
			if(employee != null) // 직원 수가 4명으로 줄였을 때 초기화 값이 null이기 때문에 생기는 오류가 날 것을 방지
				System.out.println(employee.getName() + "(" + employee.getDept() + ")");
			}
	}
	
	// 박대리 직원 찾기
	public void inquiry(String name) {  
		if(idx == 0) {
			System.out.println("조회할 직원이 없습니다.");
			return;
		}
		for(Employee employee : employees) {    // employees 배열에 있는 데이터를 employee에 넣음
			if(employee != null) {         		// 직원이 있어야 하니까 필수적으로 들어감
				if(employee.getName().equals(name)) {  // employee.getName()과 String name을 비교(.equals로(string이니까))하려는 것.
					System.out.println("검색된 직원: " + employee.getName() + "(" + employee.getDept() + ")");
				}
			}
		}
	}
	
	
	// 박대리 직원 해고하기
	public void fireEmployee(String name,String dept) {
		if(idx == 0) {
			System.out.println("해고할 직원이 없습니다.");
			return;
		}
		
		boolean exist = false; //삭제할 직원이 존재하는가 false 초기화
		//인덱스를 조정하면서 for문을 사용해야 하는 상황. 박대리를 찾으면 그 뒷 인덱스를 앞으로 덮어가면서 써야함.
		// advance for문은 index 정보가 없기 때문에 일반 for문을 써야 함.
		for(int i = 0; i < idx; i++) {    //배열의 길이는 employees.length가 아니고 idx임. 조회해야하는 실제 직원 수여야 함. idx로 하면 null값 체크할 필요도 없다.
			if(employees[i].getName().equals(name) && employees[i].getDept().equals(dept)) {   // employees[i] : 특정 직원 하나. 배열의 중간에 있는 i 값 찾은 것.
				for(int j = i; j < idx - 1; j++) {     // 이제 i + 1 자리에 있는 사람을 i자리로 한칸씩 옮겨(복사해)줘야 함. j = i, j < idx-1 , j++
					employees[j] = employees[ j + 1 ]; // 앞으로 한칸씩 옮김.
				}
				idx--;                 // 해고 했으니까 idx 값 줄어야함
				employees[idx] = null; // 해고 했으니까 null값 줘야함
				// employee[--idx] = null; idx 값 줄이고 null값도 넣는 방법
				exist = true; // 삭제할 직원 찾았다.
				
			}	
		}
		if(exist == false)      // if(!exist) 같은 코드임. 근데 !쓰는 것을 지양.
			System.out.println(name + "(" + dept + ") 직원이 존재하지 않습니다.");   //false라면 직원이 해고된 적이 한번도 없다는 것.
		else
			System.out.println(name + "(" + dept + ") 직원이 해고되었습니다.");
	}
	
	
	
	
	
	
	
	
	
	
	
}