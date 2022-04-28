package ex05_output_stream;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.ToString;


/*
	1. objectOutputStream 스트림을 통해서 데이터를 보낼 때는 직렬화가 필요하다.
		스트림은 데이터를 하나하나씩 보내는 통로인데 USer는 덩어리다. userNo, userID, userName
	2. 직렬화 
		1) Serializable 인터페이스를 구현(implements)
		2) 임의의 serialVersionUID 필드 추가
 */



@AllArgsConstructor
@ToString

//m5 메소드를 위해서 인스턴스 생성

public class User implements Serializable{ //직렬화 작업
	
	
	private static final long serialVersionUID = 8893647767234402146L; // 직렬화 작업 오류 처리기능으로 자동완성 가능

	//field
	private Long userNo;
	private String userId;
	private String userName;
	
	
	
}
