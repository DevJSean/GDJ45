package ex10_override;

public class Main {

	public static void main(String[] args) {

		Regular r = new Regular("이대리", 200);
		System.out.println("직원명 " + r.getName() + ", 급여 " + r.getPay() + "만원");
		

		Temporary t= new Temporary("김알바", 1);
		t.setTimes(52 * 4); // 몇 시간 일했는지 setter로 전달해야 함, 52시간 4번
		System.out.println("직원명 " + t.getName() + ", 급여 " + t.getPay() + "만원");
		
		
		Sales s = new Sales("최사원", 50); // 이름, 기본급
		s.setSalesVolume(1000); //판매 금액
		s.setIncentive(0.1); //판매 금액에서 얼만큼 인센티브로 가져갈 것인가
		System.out.println("직원명 " + s.getName() + ", 급여 " + s.getPay() + "만원");
		
		
	}

}
