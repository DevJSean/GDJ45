package ex02_reference_array;

public class Ex02_advanced_for {

	public static void main(String[] args) {
		
		
				
		// 향상 for         primitive 타입의 배열 보다 reference 타입의 배열에서 많이 쓰인다.
		// 1. 인덱스 없이 사용한다.
		// 2. 형식
		//    for(변수 : 배열) { }       배열의 모든 요소들을 해당 변수로 옮겨서 사용
		
		
		// 스트링 배열의 초기화
		String[] menu = {"김치찌개", "된장찌개", "부대찌개"};
		
		
		for(String food : menu)
			System.out.println(food);
		

		
	}

}
