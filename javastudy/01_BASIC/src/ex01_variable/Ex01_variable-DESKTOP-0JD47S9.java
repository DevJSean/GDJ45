package ex01_variable;


// single comment - 한 줄짜리 주석(설명) 

/*  엔터 클릭하면 자동 완성
 * 
끝은 이렇게 마무리
 */

/*
   multiple comment
   여러 줄의 주석
*/

/*
	1. 자바 이름 규칙 (naming convention)
		1) 프로젝트 : 규칙 없다 마음대로
		2) 패키지 : 모두 소문자로 작성
		3) 클래스 : Upper Camel Case
		4) 변수, 메소드 : Lower Camel Case
		5) 상수 : Snake Case
	2. Naming Convention
		1) Upper Camel Case : MyName(첫글자를 대문자로, 두개 이상의 단어일 경우 첫 글자마다 대문자)
		2) Lower Camel Case : myName(두개 이상의 단어의 경우 첫번째 글자를 소문자로 나머지는 대문자)
		3) Snake Case : MY_NAME(모두 대문자로 적는다, 단어 사이에 밑줄)
*/

/*
	main 메소드(method)
	1. 자바 프로젝트가 실행되려면 반드시 필요하다
	2. JVM(java virtual machine)은 열려있는 main 메소드를 찾아서 실행한다.
	3. main 메소드를 못 찾으면 실행에 성공한 최근 main 메소드를 다시 실행한다.
	4. public static void main(Spring[] args){  } <- main 메소드 모양
	5. 클래스를 추가할 때 자동 등록하거나, 빠르게 자동완성(main 적고 Ctrl+space bar) 할 수 있다.
*/

// 파일의 이름이랑 클래스의 이름이랑 같아야 한다.
public class Ex01_variable {

	public static void main(String[] args) {
		
		// 출력 방법
		//System.out.println(출력할 내용)  출력 후 줄 바꿈 기능
		//System.out.print(출력할 내용)    출력만 함
		//sysout, syso  Ctrl+spacebar
		
		
		// 데이터 표기법 연습(literal) : 
		
		//숫자 
		System.out.println(45);
		System.out.println(1.5);
		System.out.println(45L);
		System.out.println(1.123456789);
		System.out.println(1.12345678901234567890); //소수 아래 16자리까지만 출력함
		
		//문자 (1글자, 작은 따옴표)
		System.out.println('a');
		System.out.println('한');
		System.out.println('\n'); // 줄 바꿈 (역슬래시로 시작하는 문자: 이스케이프)
		System.out.println('\''); // '    (이스케이프)
		System.out.println('\"'); // "    (이스케이프)
		
		//문자열 (여러 글자, 큰 따옴표)
		System.out.println("hello"); 
		System.out.println("a"); //'a'와 "a"는 다르다
		System.out.println(""); // 빈 문자열, 공백, 스페이스 넣지 않기
		
		//논리 (참, 거짓)
		System.out.println(true);  //참    따옴표 묶지 않도록 주의
		System.out.println(false); //거짓 
	}
	
}
