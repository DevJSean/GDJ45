package ex04_set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString

public class Book {

	//field
	private String isbn;
	private String title;
	
	
	//method
	
	// hashCode() 메소드
	// object 클래스 : 인스턴스의 참조값 반환한다. 이 인스턴스의 참조값(주소값)이 해쉬로 되어있다.
	// Book 클래스   : 제목의 글자 수로 오버라이드 (예시 중 하나일 뿐이다, 다양한 방법이 있다)
	@Override
	public int hashCode() {             // 자동완성으로 만들자
		return title.length();
	}
	
	// equals() 메소드
	// object 클래스 : 인스턴스의 참조값 비교한다.
	// Book 클래스   : isbn이 같으면 같은 책, 다르면 다른 책
	@Override
	public boolean equals(Object obj) {
		Book book = (Book)obj;
		return isbn.equals(book.isbn);
	}
	
}
