package ex04_set;

public class Main {

	public static void main(String[] args) {

		Desk desk = new Desk();
		
		// 추가 1. 동일한 인스턴스의 경우
		// 인스턴스의 참조값(저장된 주소)이 동일하기 때문에 중복 검사 가능
		Book book = new Book("1234", "어린왕자");
		desk.addBook(book);
		desk.addBook(book); // 하나의 인스턴스에 같은 책을 두번 넣어도 한 권만 들어간다.

		// 추가 2. 다른 인스턴스의 경우(하지만 내용은 같음)
		// 인스턴스의 참조값(저장된 주소)가 다르기 때문에 중복 검사 불가능하다.
		// 인스턴스의 내용을 비교해서 중복 검사할 수 있도록 
		// 		Book 클래스에 hashCode() & equals() 메소드를 오버라이드 해야 한다.
		desk.addBook(new Book("5678", "소나기"));
		desk.addBook(new Book("5678", "소나기"));  //그런데 이런식으로 서로 다른 두 개의 인스턴스를 만들면 내용을 비교하지 못하고 중복으로 들어가진다.

		
		// 삭제
		desk.removeBook(new Book("1234", "어린왕자"));
		
		
		desk.findAllBooks();
		
		
	}

}
