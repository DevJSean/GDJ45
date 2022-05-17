package com.goodee.ex06;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goodee.ex06.config.BoardConfig;
import com.goodee.ex06.domain.BoardDTO;
import com.goodee.ex06.repository.BoardRepository;


// JUnit4 단위 테스트 선언
@RunWith(SpringJUnit4ClassRunner.class)

// 단위 테스트를 할 때, 이 bean을 만들어서 수행할 것
@ContextConfiguration(classes = {BoardConfig.class}) // 자바 배열 {}

// root-context.xml로 bean을 만들었으면 아래처럼 하면 된다.
//@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/"})

public class BoardTestCase {

	// BoardRepository가 필요하다.(DI)
	@Autowired
	private BoardRepository boardRepository;
	
	// 단위 테스트의 메소드 이름은 한글로 해도 좋다.
	
	
	@Test
	public void 목록테스트() {
		
		List<BoardDTO> boards = boardRepository.selectBoards();
		assertEquals(0, boards.size()); // assertEquals(목록의 개수를 0으로 예상한다, 실제 목록 개수)
	}

}
