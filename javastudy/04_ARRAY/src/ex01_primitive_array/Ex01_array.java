package ex01_primitive_array;

public class Ex01_array {

	public static void main(String[] args) {

		// 배열
		// 1. 변수를 여러 개 모아놓은 자료형이다.
		// 2. 참조 타입이다. (주소, 참조 값을 저장한다)
		// 3. 자동으로 0으로 초기화된다.
		// 4. 각 변수의 구분은 인덱스로 한다.
		// 5. 인덱스(index)의 시작은 0이다.
		
		// 소괄호가 아니고 대괄호가 붙음
		// arr 변수에는 주소(참조 값)가 들어갈 수 있는 형태로 바뀜
		// new : 메모리를 만드세요 라는 뜻 (int[5] -> 메모리에 정수를 다섯 개 만드세요, 그 공간은 흩어져있지 않고 붙어있다.)
		
		int[] arr = new int[5]; 

		arr[0] = 100;
		arr[1] = 200;
		
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]); // 값을 지정하지 않으면 기본 값은 0
		System.out.println(arr[3]);
		System.out.println(arr[4]);


		
		
		
		
		
		
		
		
		
		
	}

}
