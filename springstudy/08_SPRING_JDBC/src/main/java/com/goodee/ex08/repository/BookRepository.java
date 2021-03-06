package com.goodee.ex08.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.goodee.ex08.domain.BookDTO;

@Repository // 없어도 잘 동작하는 애너테이션
public class BookRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate; // DBConfig의 bean 불러오기
	                                   // Connection, PreparedStatement, ResultSet을 내부에서 처리해주는 객체이다.
	private String sql;
	
	public List<BookDTO> selectBookList() {
		
		sql = "SELECT BOOK_NO, TITLE, AUTHOR, PRICE, PUBDATE, REGIDATE FROM BOOK ORDER BY BOOK_NO DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BookDTO.class));
		
	}
	
	public BookDTO selectBookByNo(Long book_no) {
		
		sql = "SELECT BOOK_NO, TITLE, AUTHOR, PRICE, PUBDATE, REGIDATE FROM BOOK WHERE BOOK_NO = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BookDTO.class), book_no);

	}
	
	public int insertBook(BookDTO book) {
		
		return jdbcTemplate.update(new PreparedStatementCreator() { // 원래 인터페이스는 new 할 수 없지만 익명 객체를 생성하는 방법으로 해결한다.
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				sql = "INSERT INTO BOOK VALUES(BOOK_SEQ.NEXTVAL, ?, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'))";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, book.getTitle());
				ps.setString(2, book.getAuthor());
				ps.setInt(3, book.getPrice());
				ps.setString(4, book.getPubDate());
				return ps;
			}
		}); // return int;
	}
	
	public int updateBook(BookDTO book) {
		sql = "UPDATE BOOK SET TITLE = ?, AUTHOR = ?, PRICE = ? WHERE BOOK_NO = ?";
		return jdbcTemplate.update(sql, new PreparedStatementSetter() { // 위의 PreparedStatementCreator()보다 코드를 조금 더 줄일 수 있다.
			@Override													// ps가 이미 만들어져있다.
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, book.getTitle());
				ps.setString(2, book.getAuthor());
				ps.setInt(3, book.getPrice());
				ps.setLong(4, book.getBook_no());
			}
		}); //return int;
	}
	
	public int deleteBook(Long book_no) {
		sql = "DELETE FROM BOOK WHERE BOOK_NO = ?";
		return jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, book_no);
			}
		});  // return int;
	}
	
}