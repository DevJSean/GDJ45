package ex04_string;

public class Ex01_String {

	public static void main(String[] args) {
		
		// 1. 길이
		String str = "java";
		int length = str.length();  // 복습)메소드이기 때문에 괄호가 붙는다.
		System.out.println(length);
		
		// 2. 특정 문자 추출
		char ch = str.charAt(0);
		System.out.println(ch);
		
		// 문제: "abc123def" 문자열에서 아라비아 숫자 "123" 제외하고 출력하기 (for문 돌리는 것임)
		String str2 = "abc123def";
		for(int i = 0; i < str2.length(); i++) {
			// if(str2.charAt(i) >= 48 && str2.charAt(i) <= 57);          // String의 경우 브라켓[]이 아니고 charAt 메소드를 써야한다.
			if(str2.charAt(i) >= '0' && str2.charAt(i) <= '9') {           // 참고: 문자열 코드값 '0' : 48, ...,  '9' : 57
				continue;
			}
			System.out.println(str2.charAt(i));
		}
		
		System.out.println(); //줄 바꿈
		
		// 위 코드를 리팩토링 해보기 (다시 짜보기)
		// 기준 : 동일한 메소드를 여러 번 호출하면 성능이 안 좋다.
		//        가능하면 메소드는 한 번만 호출하기(length(): 반복분에 들어가 있어서 훨씬 더 많이 호출된다, charAt())
		String str3 = "abc123def";
		for(int i = 0, len = str3.length(); i < len; i++) { 
			char c = str3.charAt(i);
			if(c >= '0' && c <= '9') {
				continue; 
			}
			System.out.println(c);
		}

		System.out.println();
		
		
		// 3. 동등 비교    대문자와 소문자를 동일한 것으로 봐야할 때
		String str4 = "JAVA";
		boolean res1 = str4.equals("java");
		boolean res2 = str4.equalsIgnoreCase(str4);   // equalsIgnoreCase() 대소문자 동등하게 비교
		System.out.println(res1);
		System.out.println(res2);
		
		
		// 4. 일부 문자열 추출
		//    1) substring(begin) 메소드 : 인덱스 begin(포함)부터 끝까지 추출
		//    2) substring(begin, end) : 인덱스 begin(포함)부터 인덱스 end(불포함) 이전까지 추출
		
		String str5 = "hello";
		String res3 = str5.substring(2);
		String res4 = str5.substring(0, 4);
		System.out.println(res3);
		System.out.println(res4);
		
		// 5. 특정 문자열의 인덱스 반환   indexOf() 메소드
		//    1) indexOf : 발견된 첫 번째 인덱스 반환
		//    2) lastIndexOf : 발견된 마지막 인덱스 반환
		String str6 = "java";
		int idx1 = str6.indexOf("j");  // 0 
		System.out.println(idx1);
		int idx2 = str6.indexOf("a");  // 1 (발견된 첫 번째 인덱스 반환)
		System.out.println(idx2);
		int idx3 = str6.indexOf("z");  // -1 (없는 경우) 
		System.out.println(idx3);
		int idx4 = str6.lastIndexOf("j"); // 0
		System.out.println(idx4);
		int idx5 = str6.lastIndexOf("a"); // 3 (발견된 마지막 인덱스 반환)
		System.out.println(idx5);
		int idx6 = str6.lastIndexOf("z"); // -1 (없는 경우)
		System.out.println(idx6);
		
		
		// 문제: 파일명(butter.mp4)을 파일(butter), 확장자(mp4)로 분리하는 작업
		//       파일명에 타임스탬프 값 추가하기
		String origin = "butter.mp4";
		int dotIndex = origin.lastIndexOf(".");
		String filename = origin.substring(0, dotIndex);
		String extname = origin.substring(dotIndex + 1);
		String uploadname = filename + "_" + System.currentTimeMillis() + "." + extname; // 타임스탬프를 이용한 중복 방지 연습
		System.out.println(filename);
		System.out.println(extname);
		System.out.println(uploadname);
		
		
		// 6. 특정 패턴으로 시작하는가? DB에서 가져오는 데이터의 제목이라고 가정. 필독만 출력하기
		String[] notices = {
				"[필독]공지사항1",
				"공지사항2",
				"[필독]공지사항3"
		};
		
		for(int i = 0; i < notices.length; i++) {  // 괄호 없는 length는 메소드가 아닌 필드값임. 리팩토링 안해줘도 됨.
			if(notices[i].startsWith("[필독]")) {  // [필독]으로 시작하면 true, 아니면 false
				System.out.println(notices[i]);    // notices[i] : 각 제목들
			}	
		}
		
		
		// 7. 특정 패턴으로 끝나는가?
		String[] files = {
				"butter.mp4",
				"dog.jpg",
				"cat.jpg"
		};
		for(int i = 0; i < files.length; i++) {
			if(files[i].endsWith("jpg") == false) { // "jpg"로 끝나면 true, 아니면 false
				System.out.println(files[i] + " 파일은 첨부할 수 없습니다.");
			}
		}
			
		
		// 8. 불필요한 공백 제거(좌우 공백 제거) 사람들이 검색창에 불필요하게 넣는 부분
		String name = "  james dean ";  // 입력 받음 가정
		String name2 = name.trim(); 
		System.out.println(name2);
		
		
		// 9. 치환(찾아 바꾸기)
		String source = "best of best";
		String replaced = source.replace("best", "worst"); // (oldchar -> newchar) best가 두 개인데 둘 다 바뀌었는가
		System.out.println(replaced);                      // 둘 다 바뀜. 모두 바꾼다는 뜻
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
