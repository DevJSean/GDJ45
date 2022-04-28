package ex22_Object;

import java.util.Arrays;

// Object
//	boolean equals()

// User
// boolean equals()  유저 클래스에 오브젝트 클래스의 equals()를 오버라이드 해서 같은 ID를 비교하는 것으로 만들어보자

public class Main {

	public static void main(String[] args) {

		/*
		변수			데이터		참조
						"admin"		1
						"123456"	2
		user1.userId	1			10
		user1.userPw	2			
		user2.userId	1			20
		user2.userPw	2			
		user1			10
		user2			20
		
		user1 == user2 : false
		user1.userID == user2.userID : true
		
		 */
		
		User user1 = new User("admin", "123456");
		User user2 = new User("admin", "123456");
		// 동일한 아이디는 가입할 수 없다.
		// ID is unique
		
		if(user1.equals(user2))
			System.out.println("동일한 회원");
		else
			System.out.println("다른 회원");
		// User클래스에 오버라이드를 하지 않으면 아이디가 같지만 다른 회원이라고 나온다.
		// 왜냐면 Object 클래스의 equals는 참조 값을 비교하는 것이니까!!
		
		String strUser1 = user1.toString();   // User 클래스에서 toString 메소드 오버라이드 하지 않으면 타입@참조값 으로 나온다.  
		System.out.println(strUser1);		  // User 클래스에서 equals 메소드 오버라이드를 하지 않았을 때 비교하는 값이 이 참조값이다.
		String strUser2 = user2.toString();
		System.out.println(strUser2);

	
		System.out.println(user1);  // toString 메소드는 생략할 수 있다.
		System.out.println(user2);	// strUser1에 저장하지 않아도 user1 객체를 바로 출력할 수 있다.
		
		
		User[] users = new User[2]; // 배열도 동일하게 작동한다
		users[0] = user1;
		users[1] = user2;
		System.out.println(Arrays.toString(users));
	}

}
