package ex05_output_stream;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	// FileOutputStream : 모든 파일을 생성함
	
	// FileOutputStream 출력 단위
	// 1. 1개 	 : int
	// 2. 여러개 : byte[] 
	
	
	public static void m1() {
		
		try {
			
			// 출력 스트림 생성
			FileOutputStream fos = new FileOutputStream("C:\\storage\\b1.txt");
			
			// 출력 데이터
			String str = "Apple Mango Banana"; //String을 직접 쓸 수 없고 byte[] 바이트배열로 바꿔야 한다.
			byte[] b = str.getBytes(); // getBytes() :  String을 byte[]로 변환시킬 수 있다.
			
			// 출력
			// write 메소드
			fos.write(b);

			// 출력 스트림 닫기
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void m2() {
		
		// FileOutputStream 한글 처리
		// 한글은 1글자가 2바이트이다.
		// 한글은 1글자가 3바이트인 경우도 있다. (Charset이 UTF-8인 경우)
		
		try {
			FileOutputStream fos = new FileOutputStream("C:\\storage\\b2.bin"); //크기 확인만 하기 위해 bin파일로 만듦. bin파일은 열어보는 파일이 아니다.
			
			String str = "안녕하세요 반갑습니다";
			byte[] b = str.getBytes(Charset.forName("UTF-8")); //한글처리   StandardCharsets.USF_8
			
			fos.write(b);
			
			fos.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void m3() {
		// BufferedOutputStream : 속도를 높여주는 보조 스트림
		
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:\\storage\\b3.bin"));
		
			String str = "hello";
			byte[] b = str.getBytes();
			
			bos.write(b, 0, 4); // 인덱스 0부터 4개만 출력하겠다   -> 4 바이트 bin파일이 만들어짐.
			
			bos.close();
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void m4() {
		// DataOutputStream : 변수 그대로 출력하는 보조 스트림
		
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream("C:\\storage\\b4.dat"));
			
			int age = 45;
			double height = 177.5;
			String name = "민경태";
			
			dos.writeInt(age);
			dos.writeDouble(height);
			dos.writeUTF(name); // String대신 writeUTF()
			
			dos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void m5() {
		// ObjectOutputStream : 객체(인스턴스)를 그대로 출력하는 보조 스트림
		
		// List에 임의의  User 인스턴스 3개 저장하기
		List<User> users = Arrays.asList(new User(1L, "apple", "사과"), new User(2L, "banana", "바나나"), new User(3L, "mango", "망고"));
		
		// User 인스턴스 1개 저장
		User user = new User(4L, "cherry", "체리");
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\storage\\b5.dat"));
			
			// List 그대로 출력하는 방법
			oos.writeObject(users);
			
			// User 인스턴스 그대로 출력하는 방법
			oos.writeObject(user);
			
			oos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void m6() {
		// ObjectInputStream : 객체(인스턴스)를 그대로 입력받는 보조 스트림
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\storage\\b5.dat"));
		
			List<User> users = (List<User>)ois.readObject();
			for (User user : users) {
				System.out.println(user);
			}
			
			User user = (User)ois.readObject();
			System.out.println(user);
			
			ois.close();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();    // 변환하라고 하는 객체가 없을 때
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
			
		//m1();
		//m2();
		//m3();
		//m4();
		//m5();
		m6();
	}

}
