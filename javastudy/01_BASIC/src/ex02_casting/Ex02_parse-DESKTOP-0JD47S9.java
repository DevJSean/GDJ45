package ex02_casting;

public class Ex02_parse {

	public static void main(String[] args) {
		
		
	// "100" -> 100   casting이 아니라 parse(분석)
		
	// 1. "100" -> 100 문자열 정수를 실제 정수로 변환하는 과정, str1을 분석해서 정수로 변환하는 과정이다
	String str1 = "100";
	int a = Integer.parseInt(str1);
	System.out.println(a);

	// 2. "200" -> 200L
	String str2 = "200";
	long b = Long.parseLong(str2);
	System.out.println(b);

	// 3. "1.5" -> 1.5
	String str3 =  "1.5";
	double c = Double.parseDouble(str3);
	System.out.println(c);
	
	}
	
}
