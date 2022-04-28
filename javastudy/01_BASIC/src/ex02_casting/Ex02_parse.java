package ex02_casting;

public class Ex02_parse {

	public static void main(String[] args) {
		
		
	// "100" -> 100   casting이 아니라 parse(분석)
		
	// 문자열 str1을 분석해서 정수(int)로 변환, Integer.parseInt()
	// 1. "100" -> 100 
	String str1 = "100";
	int a = Integer.parseInt(str1);
	System.out.println(a);

	// 문자열 str2를 분석해서 정수(long)로 변환, Long.parseLong()
	// 2. "200" -> 200L
	String str2 = "200";
	long b = Long.parseLong(str2);
	System.out.println(b);

	// 문자열 str3을 분석해서 실수(double)로 변환, Double.parseDouble()
	// 3. "1.5" -> 1.5
	String str3 =  "1.5";
	double c = Double.parseDouble(str3);
	System.out.println(c);
	
	
	}
	
}
