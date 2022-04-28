package ex03_reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	// Stream
	// 1. 데이터가 지나다니는 통로 개념으로 이해
	// 2. 일방통행(출력 스트림, 입력 스트림 따로 있다) 개념
	// 3. 항상 IOException 예외 처리가 필요하다
	
	// FileReader 클래스 : 텍스트 파일을 읽을 때 사용
	
	// FileReader 입력 단위
	// 1. 1글자 : int
	// 2. 여러글자 : char[]
	public static void m1() {
		
		try {
			
			// 입력 스트림 생성
			FileReader fr = new FileReader("C:\\storage\\m2.txt");
			
			/*
			// 문자 저장 변수
			int c = fr.read(); //read() 입력 스트림으로부터 int니까 한 글자 읽으세요 -> A가 나옴.
			System.out.println(c);   // 기본적으로 int니까 A의 코드값인 65가 나옴.
			System.out.println((char)c); // 코드값 65를 문자로 형변환.
			*/
			
			// 문자 저장 변수
			int c;
			
			/*
			// read 메소드
			// 1. 문자 반환
			// 2. -1 반환 (모두 읽은 경우)
			// read()는 사용할 때마다 한 글자씩 다음 것을 읽음
			
			while(true) {
				c = fr.read();
				if(c == -1)
					break;
				System.out.print((char)c);
			}
			*/
			
			// 실제로 사용하는 방법.!!!!!!!!!!!!!!!!!
			while(( c = fr.read() ) != -1 ) {
				System.out.print((char)c);
			}
			
			
			// 입력 스트림 닫기
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void quiz1() {
		
		//sb 인스턴스에 m2.txt 파일 내용 저장하기
		try {
			StringBuilder sb = new StringBuilder();
			FileReader fr = new FileReader("C:\\storage\\m2.txt");
			int c;
			
			while(( c = fr.read() ) != -1 ) {
				sb.append((char)c);
			}
			System.out.print(sb.toString());
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//String str3 = sb2.toString();
		//System.out.println(str3);
		
		
	}
	
	public static void m2() {
		
		try {
			
			//입력 스트림 생성
			FileReader fr = new FileReader("C:\\storage\\m2.txt");
			
			//5글자를 저장할 배열
			char[] cbuf = new char[5];
			
			//읽기
			while(true) {
				
				int readCount = fr.read(cbuf); // apple이 cbuf에 들어가고 5가 readCount에 들어간다.
				
				// m2.txt 파일 순회 과정
				//			readCount		cbuf				readCount만큼 cbuf의 글자수를 사용하면 해결
				// 순회 1	 5				 A  p  p  l  e      readCount가 5면 cbuf의 5글자를 사용하라
				// 순회 2	 5				\n  M  a  n  g
				// 순회 3    1				 o  M  a  n  g		readCount가 1이면 cbuf의 1글자를 사용하라
				//                          --  ----------
				// 				   새로 읽은 o  기존에 있던 Mang
				// 순회 4	-1
				
				if(readCount == -1)
					break;
				//System.out.print(new String(cbuf)); 그냥 이렇게 하면 위 문제 발생
				//					char를 받아서 String으로 만들어주는 new String()
				
				// 배열 cbuf 순회는 readCount만큼 처리
				for(int i = 0; i < readCount; i++)
					System.out.print(cbuf[i]);

			}
				
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void quiz2() {
		//cbuf, StringBuilder m2.txt 파일 내용 저장하기
		// 가장 많이 쓰인다.
		
		try {
			

			FileReader fr = new FileReader("C:\\storage\\m2.txt");
			
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[5];
			int readCount;
			
			while((readCount = fr.read(cbuf)) != -1) {
				//cbuf배열의 인덱스 0부터 readCount만큼만 사용
				sb.append(cbuf, 0, readCount); //append의 새로운 기능
			}
			System.out.println(sb.toString());
			fr.close();
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void m3() {
		// BufferedReader : 속도를 높여주는 보조 스트림
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\m3.txt"));
			
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[5];
			int readCount;
			
			while((readCount = br.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCount);
			}
			
			System.out.println(sb.toString());
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();		
		}
	}
	

	public static void m4() {
		
		// BufferedReader 클래스는 readLine 메소드를 지원한다.
		// readLine 메소드 : 텍스트 파일의 Line 단위를 읽어들인다.
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\m3.txt"));
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null) {
				sb.append(line).append("\n"); // 메소드 연속으로 불러내기 메소드 체이닝, 줄바꿈 기호를 포함시킨다.
			}
			System.out.println(sb.toString());
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void quiz3() {
		// 텍스트 파일 복사하기
		// m3.txt 파일 내용을 모두 읽어서 StringBuilder에 저장한 다음,
		// StringBuilder의 내용을 M3_복사본.txt 파일에 기록하시오.
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\m3.txt"));
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null) {  // null이 아니면 반복할 수 있다.
				sb.append(line).append("\n");
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\storage\\m3_복사본.txt"));

			bw.write(sb.toString());
			
			br.close();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void quiz4() {
		// StringBuilder 없이 텍스트 파일 복사하기
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\m3.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\storage\\m3_복사본.txt"));

			String line = null;
			
			while((line = br.readLine()) != null) {  // 한 줄 읽어서
				bw.write(line + "\n");				 // 그대로 쓰기
			}

			br.close();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		//m1();
		
		//quiz1();
		
		//m2();
		
		//quiz2();
		
		//m3();
		
		//m4();
		
		//quiz3();   
		
		quiz4();
	}

}
