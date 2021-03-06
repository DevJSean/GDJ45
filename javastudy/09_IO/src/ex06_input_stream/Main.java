package ex06_input_stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	//FileInputStream : 모든 파일을 읽음 (텍스트가 아니어도 가능)
	
	// FileInputStream 입력 단위
	// 1. 1개 	 : int
	// 2. 여러개 : byte[] 
	
	public static void m1() {

		try {
			
			//입력 스트림 생성
			FileInputStream fis = new FileInputStream("C:\\storage\\b1.txt");
			
			//사용 변수 및 인스턴스
			StringBuilder sb = new StringBuilder();
			byte[] b = new byte[5]; //바이트 배열, 바이트 수는 임의로 정했음
			int readCount;
			
			// read 메소드
			// int readCount = fis.read(b); 읽은 내용은 배열 b에 저장, 읽은 바이트 수는 readCount에 저장
		
			while((readCount = fis.read(b)) != -1) {
				
				// byte[] b를 String으로 변환, 반대 과정은 OutputStream에서 했음.
				// new String(b, 0, readCount) : 배열 b의 인덱스 0부터 readCount만큼만 문자열로 변환한다.
				
				// b1.txt 파일은 오직 영문(1글자는 1바이트)으로만 구성되므로
				// byte[]에 읽는 것이 문제 없음
				
				sb.append( new String(b, 0, readCount) );
			}
			
			//확인
			System.out.println(sb.toString());
			
			//입력 스트림 닫기
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void m2() {
		
		// InputStreamReader : 바이트 기반 입력 스트림(InputStream)을 문자 기반 입력 스트림(Reader)으로 변환
		// 					   하여 한글 깨짐을 방지한다.
		
		try {
			
			// 입력 스트림 생성(InputStream)
			InputStream is = new FileInputStream("C:\\storage\\b2.bin"); //생성은 FileInputStream
			
			//문자 기반 스트림
			InputStreamReader isr = new InputStreamReader(is);
			
			// 버퍼
			BufferedReader br = new BufferedReader(isr); // 윗줄의 우변들을 이 괄호에 넣어 한 줄로 만들 수 있다.
			
			//StringBuilder에 저장
			
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			
			System.out.println(sb.toString());
			
			br.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void m3() {
		
		// 파일 복사하기
		// 소요시간 출력
		try {
			
			// 원본 읽기 스트림 생성
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\storage\\eclipse-jee-2021-03-R-win32-x86_64.zip")); //원본
			
			// 복사본 생성하기 스트림 생성
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:\\storage\\eclipse.zip")); //복사본
			
			byte[] b = new byte[1024]; // 1024바이트 = 1킬로바이트(KB)
			int readCount;
			
			// 시작 시간
			long start = System.currentTimeMillis(); //타임스탬프값 1/1000초 값
			
			// 복사
			while((readCount = bis.read(b)) != -1) {  //bis.read(b) 원본을 읽는다.
				bos.write(b, 0, readCount); // 복사본 bos를 만든다
			}
			
			// 종료 시간
			long end = System.currentTimeMillis();
			
			// 확인
			System.out.println("복사 완료 " + (end - start) * 0.001 + "초 소요" );
			
			// 스트림 닫기
			bis.close();
			bos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void m4() {
		// DataInputStream : 파일에 저장된 변수 읽는 보조 스트림
		// 바이트 기반인데 문자열이 들어왔을 때 reader로 바꾸는 과정 없이 inputstream으로 사용
		
		try {
			
			DataInputStream dis = new DataInputStream(new FileInputStream("C:\\storage\\b4.dat"));
			
			int age = dis.readInt();
			double height = dis.readDouble();
			String name = dis.readUTF(); //String은 UTF
			
			System.out.println(age + "," + height+ ","  + name);
			
			dis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {

		//m1();
		//m2();
		//m3();
		m4();
		
	}

}
