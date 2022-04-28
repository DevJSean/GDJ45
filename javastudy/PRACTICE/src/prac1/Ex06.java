package prac1;

// 램덤으로 윷놀이를 구현하시오. 도개걸윷모 중 랜덤 생성하여 이동 횟수와 함께
// 화면에 출력하시오. 윷이나 모가 나오면 게속 랜덤 생성하여 총 이동 횟수를 계산하여 출력하시오.
// 도 : 1칸 이동
// 개 : 2칸 이동
// 걸 : 3칸 이동
// 윷 : 4칸 이동
// 모 : 5칸 이동
// 윷, 걸, 7칸 이동한다.

// 도개걸윷모 -> 배열에 저장, 이동횟수를 인덱스와 일치시켜서 사용하자

public class Ex06 {

	public static void main(String[] args) {


		String[] yutnori = new String[] { "", "도", "개", "걸", "윷", "모" };
	  //String[] yutnori = {"", "도", "개", "걸", "윷", "모"};  배열에서 ""는 더미요소(사용 안함)
		
		int total = 0;
		while (true) { // 몇 번 던질지, 언제 끝날지 모르니까 무한루프 우선 생성
			
			int move = (int)(Math.random() * 5) + 1;    // 이동횟수 == 인덱스 -> 난수 처리
			
			// 이동횟수 누적
			total += move;
			
			if (move <= 3) { // 도 개 걸의 경우
				System.out.println(yutnori[move] + ", " + total + "칸 이동한다.");
				break;
			} else {      // 윷 모
				System.out.print(yutnori[move] + ", ");
			}
			
		}

	}

}
