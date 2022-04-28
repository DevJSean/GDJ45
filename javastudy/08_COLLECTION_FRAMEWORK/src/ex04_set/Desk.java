package ex04_set;

import java.util.HashSet;
import java.util.Set;

public class Desk {

	//책상에 동일한 책은 1권만 둔다. -> list 보다는 set가 좋겠다.
	
	//field
	private Set<Book> books;
	
	//constructor
	public Desk() {
		books = new HashSet<Book>();
	}
	
	
	//method
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void removeBook(Book book) {
		if(books.contains(book))   // contains() : 포함되어 있는지 점검하는 메소드 true false 반환
			books.remove(book);   // 제거
	}
	
	public void findAllBooks() {
		for(Book book : books) // 향상 for문
			System.out.println(book);
	}
	
}
