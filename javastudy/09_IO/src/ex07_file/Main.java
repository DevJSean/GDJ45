package ex07_file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

	
	public static void m1() {
		// File 클래스 : 파일/디렉토리 정보 확인
		
		// File 인스턴스 생성 방법
		// 1. new File(경로, 파일)
		// 2. new file(경로\\파일)
		File file = new File("C:\\Storage", "b1.txt");
		
		System.out.println("파일명 : " + file.getName()); // 파일 이름 출력하기
		System.out.println("경로 : " + file.getParent()); // 파일 경로 출력하기
		System.out.println("전체경로(경로+파일) : " + file.getAbsolutePath()); // 경로, 파일 출력하기
		System.out.println("수정한 날짜 : " + file.lastModified()); //수정한 날짜는 timestamp값이 나온다.
		
		// 윈도우 시간처럼 출력하기
		String lastmodified = new SimpleDateFormat("a h:mm yyyy-MM-dd").format(file.lastModified()); //포맷은 외울 필요 없음
		System.out.println(lastmodified);
		
		System.out.println("파일크기 : " + file.length() + "byte"); //파일크기는 바이트 단위로 나온다
		System.out.println("파일인가? " + file.isFile()); // true/false
		System.out.println("폴더인가? " + file.isDirectory()); // true/false
		System.out.println("존재하는가? " + file.exists()); // true/false
	}
	
	public static void m2() {
		
		// 디렉토리 조작 (폴더 만들기, 삭제하기)
		
		// 디렉토리를 지정
		File dir = new File("C:\\upload");
		
		// 존재하지 않으면 디렉터리 생성하기
		if(!dir.exists() ) { //if(dir.exists() == false)
			dir.mkdirs(); // 디렉터리 생성하기, s가 들어가는 것으로 써야 폴더 안에 여러 단계 밑의 폴더를 만들 수 있다.
		} else {
		// 존재하면 디렉터리 삭제하기
		  //dir.delete(); 바로 지우기
			dir.deleteOnExit(); // 자바 실행이 끝나면 지우기
			
		}
	}
	
	public static void m3() throws IOException{ // createNewFile() 메소드의 IOException 처리를 메소드의 호출 영역에서 처리하는 방식
		
		// 파일 조작 (파일 만들기, 삭제하기)
		File file = new File("C:\\storage", "my.txt");
		
		// 존재하지 않으면 파일 생성하기
		if(file.exists() == false) {
			file.createNewFile(); // 파일 생성하기, 파일은 IOException 처리 해야한다.
		} else {
		// 존재하면 파일 삭제하기
			file.deleteOnExit();
		}
	}
	
	public static void quiz1() {
		
		// C:\\storage 디렉터리 대상
		// 명령 프롬프트로 확인할 수 있는 정보 목록 만들기
		
		File dir = new File("C:\\storage");
		
		File[] files = dir.listFiles(); // 디렉터리(dir)에 저장된 모든 File 정보를 배열로 저장하기

		// 수정한 날짜    <DIR>      디렉터리명
		// 수정한 날짜    파일크기   파일명
		for(File file : files) {
			System.out.print(new SimpleDateFormat("yyyy-MM-dd a hh:mm").format(file.lastModified()));
			System.out.print("\t\t");
			if(file.isDirectory())
				System.out.print("<DIR>");
			else
				System.out.print(new DecimalFormat("#,##0").format(file.length())); // DecimalFormat : 천단위 구분 기호 넣기
			System.out.print("\t\t");
			System.out.println(file.getName());	
		}
	}
	
	public static void quiz2() {
		
		// C:\storage 디렉터리 대상으로 작업
		
		// 삭제할 파일명 입력 >>> eclipse.zip
		// eclipse.zip 파일이 삭제되었습니다.
		
		// 삭제할 파일명 입력 >>> sun.jpg
		// sun.jpg 파일이 존재하지 않습니다.

		Scanner sc = new Scanner(System.in);
		
		System.out.print("삭제할 파일명 입력 >>> ");
		String filename = sc.next();
		
		File file = new File("C:\\storage", filename);
		
		if(file.exists()) {
			System.out.println(filename + " 파일이 삭제되었습니다.");
			file.delete();
		} else {
			System.out.println(filename + " 파일이 존재하지 않습니다.");
		}
		sc.close();
	}
	
	public static void m4() throws IOException{
		// File + 입출력 스트림 함께 사용하기
		// 아직 만들어지지 않은 디렉토리(폴더)에 파일 만들기
		
		File dir = new File("C:\\storage"); // 폴더를 먼저 만든다.
		
		if(!dir.exists()) // 만약 폴더가 없으면
			dir.mkdirs(); // 폴더를 만들어라.
												   //getPath() 경로 알아내기,   File.separator = \\ (역슬래시 두 개)
		BufferedWriter bw = new BufferedWriter(new FileWriter(dir.getPath() + File.separator + "m.txt")); // C:\\storage\\m.txt
			
		bw.write("안녕하세요. 반갑습니다.");
				
		bw.close();
				
	}
	
	public static void quiz3() throws IOException{
		// C:\storage\m.txt 파일을
		// C:\\upload 디렉토리로 이동하기
		
		// 원본
		// C:\\storage\\m.txt
		File source = new File("C:\\storage\\m.txt");
		
		//이동할 디렉터리
		File dir = new File("C:\\upload");
		if(!dir.exists())
			dir.mkdirs();
		
		// 복사 파일
		File copy = new File(dir, source.getName()); //dir: "C:\\upload", source.getname(): m.txt
		
		
		// 복사하기
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source)); //FileInputStream(파일 전달) 
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copy));
		
		byte[] b = new byte[1024];
		int readCount; //실제 읽어들이는 byte 수는 다를 수 있어서 readCount가 필요함
		
		while((readCount = bis.read(b)) != -1)
			bos.write(b, 0, readCount);
		
		// 스트림 닫기
		bos.close();
		bis.close();
		
		// 원본 파일 삭제
		// 스트림을 닫은 후 실행하면 된다.
		// 원본 파일과 복사본 파일의 크기가 동일하면 삭제하겠다.  -> 복사가 잘 됐는지 안전하게 확인
		if(source.length() == copy.length())
			source.deleteOnExit();
		
	
	}
	
	
	public static void main(String[] args) throws IOException { // try catch 처리 없이 하는 방법.
		                                                        // main메소드를 호출하는 곳으로 예외 처리를 넘김. 
																// 그러면 자바가 알아서 처리함.


		//m1();
		//m2();
		//m3();
		//quiz1();
		//quiz2();
		//m4();
		quiz3();
		
		
	}
}
