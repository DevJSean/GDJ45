package ex03_instance_array;

public class MemberMain {

	// static 메소드는 static 메소드만 호출할 수 있다.
	// main 메소드에서 호출하기 때문에 m1 메소드도 static 처리를 해야 한다.
	//                 ↓ m1 메소드	
	public static void m1() {

		// default 처리 방식과 setter 이용한 데이터 처리(setter injection)
		Member member1 = new Member();    
		member1.setUserId("admin");
		member1.setName("관리자");
		
		System.out.println(member1.getUserId());
		System.out.println(member1.getName());
		
		// constructor를 이용한 데이터 처리(constructor injection)
		Member member2 = new Member("apple", "사과"); 
		
		System.out.println(member2.getUserId());
		System.out.println(member2.getName());
	}
	
	public static void m2() {
		
		Member[] members = new Member[2];
		
		// 아래 과정을 해야함
		// members[0] = new Member(); 위에 int나 String이었으면 여기서 그냥 10이나 이름 같은 것을 넣었겠지만 
		// members[1] = new Member(); 위에서 Member였기 때문에	new Member가 필요하다.

		// for문으로
		for(int i = 0; i < members.length; i++) {    // member배열에 필요한 데이터를 넣는 과정
			members[i] = new Member();               // reference type은 null값을 초기화로 가짐. 이 줄(new 코드)을 작성하지 않으면 다 null값이 됨. 
			members[i].setUserId("user" + i);        // 그러면 밑에서 set을 써도 자바에서 너 null값을 가지고 뭐하니? 하고 오류 띄움
			members[i].setName("회원" + i);
		}
		
		// 출력
		for(int i = 0; i < members.length; i++)
			System.out.println(members[i].getUserId() + ", " + members[i].getName());
		
	}
	
	public static void m3() {
		
		Member[] members = new Member[3];
		String[] ids = {"apple", "banana", "corn"};
		String[] names = {"사과", "바나나", "옥수수"};
		
		for(int i = 0; i < members.length; i++) {
			members[i] = new Member(ids[i], names[i]);
		}
		
		// 출력 - 향상 for문 사용  (advanced for, 향상 for문)
		for(Member member : members) 
			System.out.println(member.getUserId() + ", " +  member.getName());
		
		
	}
	
	// JVM에서는 메인 메소드가 실행된다. 실행은 여기서 해야한다. 실행할 메소드를 여기서 정한다.
	public static void main(String[] args) {

		m1();
		m2();
		m3();
		
				
	}

}
