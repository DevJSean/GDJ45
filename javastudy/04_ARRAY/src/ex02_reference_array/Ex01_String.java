package ex02_reference_array;

public class Ex01_String {

	public static void main(String[] args) {
		
		
		// 문자열 배열
		
		String[] menu = new String[3];
		
		menu[0] = "김치찌개";
		menu[1] = "된장찌개";
		menu[2] = "부대찌개";
		
		for(int i = 0; i < menu.length; i++)
			System.out.println(menu[i]);
		
		// String이 reference타입이지만 배열에서 거의 primitive처럼 사용된다. 큰 차이가 없다.
		
		
		
		
		
		
		
		
		
	}

}
