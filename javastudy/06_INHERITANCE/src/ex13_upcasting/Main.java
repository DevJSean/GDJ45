package ex13_upcasting;

public class Main {

	public static void main(String[] args) {

		/*
		Regular r = new Regular("이대리", 200); // 이름, 급여
		System.out.println("직원명 " + r.getName() + ", 급여 " + r.getPay() + "만원");
		

		Temporary t= new Temporary("김알바", 1, 52); // 이름, 시급, 일한 시간
		System.out.println("직원명 " + t.getName() + ", 급여 " + t.getPay() + "만원");
		
		
		Sales s = new Sales("최사원", 50, 1000, 0.1); // 이름, 기본급, 판매금액, 인센티브(퍼센트)
		System.out.println("직원명 " + s.getName() + ", 급여 " + s.getPay() + "만원");
		
		*/
		Company company = new Company(10);
		company.mange();
		
	}

}
