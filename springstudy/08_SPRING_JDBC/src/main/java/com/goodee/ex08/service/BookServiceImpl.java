package com.goodee.ex08.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex08.domain.BookDTO;
import com.goodee.ex08.repository.BookRepository;

@Service  // 없어도 잘 동작하는 애너테이션
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<BookDTO> findBooks() {
		return bookRepository.selectBookList();
	}

	@Override
	public BookDTO findBookByNo(Long book_no) {
		return bookRepository.selectBookByNo(book_no);
	}

	@Override
	public int save(BookDTO book) {
		return bookRepository.insertBook(book);
	}

	@Override
	public int change(BookDTO book) {
		return bookRepository.updateBook(book);
	}

	@Override
	public int remove(Long book_no) {
		return bookRepository.deleteBook(book_no);
	}
	
	@Override
	public void transaction() {
		// 성공하는 데이터
		bookRepository.insertBook(new BookDTO(null, "테스트", "테스트", 1, "테스트", null));
		// 실패하는 데이터
		bookRepository.insertBook(new BookDTO(null, null, null, null, null, null)); // title과 author가 not null이다.
		
		// 트랜잭션이 동작한다면, 둘 다 삽입되지 않아야 한다.
		// All or Nothing (원자성) : 모두 수행되거나, 하나도 수행되지 않는다.
	}

}