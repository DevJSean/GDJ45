package ex01_primitive_array;

public class Ex04_array_copy {

	public static void main(String[] args) {
		
		// 배열의 길이 조정하기
		// 1. 배열의 길이는 변경할 수 없다.
		// 2. 길이가 다른 새로운 배열을 만들고, 값을 옮기고 참조값을 수정한다.
		
		
		// 배열의 길이가 5인 배열을 생성한 뒤
		// 길이가 10인 배열로 변경하기
		
		int[] arr = new int[5];
		
		for(int i = 0; i < arr.length; i++)
			arr[i] = (i + 1);
		
		// 길이가 10인 새로운 배열 만들기
		int[] temp = new int[10];
		
		// arr -> temp (배열 복사)
		/*
		temp[0] = arr[0];
		temp[1] = arr[1];
		temp[2] = arr[2];  이 과정을 반복문으로 하면 됨
		temp[3] = arr[3];
		temp[4] = arr[4];
		*/
		for(int i = 0; i < arr.length; i++)
			temp[i] = arr[i];
			
		// 참조값 변경        temp의 참조 값을  arr로 넘기는 것임. arr의 참조값이 temp와 동일한 것이 됨
		arr = temp;
		
		// 잘 됐나 확인                         
		for(int i = 0; i < arr.length; i++) 
			System.out.println(arr[i]);
		
		
		System.gc();
		
		// 기존에 arr이 가리키고 있던 공간은 아무도 사용하지 않아서 memory leak(메모리 누수)가 발생하게 됨. (자바에서는 garbage라고 부름)
		// 메모리가 계속 사용되고 있는 상황 -> 정리가 필요함 -> 컴퓨터를 재부팅하면 해결
		// 자바는 이를 개선했는데 JVM이 메모리 누수가 있는지 찾아보고 시스템에 반납함 -> garbage collector가 정리해줌 -> System.gc(); 메모리 누수공간 찾아서 반납해라 코드.
		// 자바의 전신 같은 C++ 같은 경우는 개발자가 직접 반납하는 코드를 짰어야 했음
		// 자바의 철학은 개발자를 믿지 않는다. 그래서 약속이 많다. 이대로 안하면 실행을 안해준다.
		
		
		
		
	}

}
