package ex01_primitive_array;

public class Ex03_array {

	public static void main(String[] args) {

		// 길이가 9인 배열을 만들고,
		// 구구단 2단의 결과를 저장하기
		int[] arr = new int[9];
		
		// 배열의 길이
		// length  필드 값으로 확인할 수 있다.
		System.out.println("배열의 길이는 " + arr.length); // 배열의 길이가 9입니다.
		
		// 배열 for문의 표준
		for(int i = 0; i < arr.length; i++)	
			arr[i] = 2 * (i + 1);
		
		for(int i = 0; i < arr.length; i++)
			System.out.println(arr[i]);
			
		// 윗 코드 하나의 for문으로 작성한 것.
		for(int i = 0; i < arr.length; i++) {
			arr[i] = 2 * (i + 1);
			System.out.println(arr[i]);
		}
		
		
		
		
		
		
		
		
		
	
	
	}

}
