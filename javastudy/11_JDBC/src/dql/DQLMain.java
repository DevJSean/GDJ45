package dql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.MyConnection;
import dto.Board;

public class DQLMain {

	/*
	jdbc DQL의 실행 결과는 ResultSet 사용
	ResultSet은 한줄씩 읽어온다
    ResultSet은 항상 next()를 함께 써야 한다.
	
	getint()
	getLong()
	getString()
	getDate()
	
	getint("no")
	getint(1) 방법이 있다.
	
	
	*dto 패키지*
	DB에서 데이터(행)을 받아오거나
	자바에서 데이터를 DB로 보낼 때
	
	행의 각 칼럼 값의 타입과 맞는 필드로 따로 저장하는 인스턴스(클래스)를 만든다.
	
	DB와 자바 사이 옮겨지는 동안에는 DTO, 자바에 저장되면 VO, 서로 같은 것.
	DTO : Data Transfer Object
	VO  : Value Object 
	*/
	
	// 결과 집합(ResultSet)이 1개인 경우, 단일 행 쿼리문
	// 결과 행 1개 -> Board board
	public static void selectOne() {
		
		/* DB 접속    */  Connection con = null;
		/* 쿼리문자체 */  String sql = null;
		/* 쿼리문실행 */  PreparedStatement ps = null;
		/* 실행결과   */  ResultSet rs = null;;  // 이 부분이 DML과 다르다
		
		try {
			
			// 1. DB접속
			con = MyConnection.getConnection();
			
			// 2. 쿼리문 작성
			//    WHERE절의 조건으로 PK/UNIQUE 칼럼의 동등비교(=) 진행하면 결과집합은 단일 행이거나 없다.
			sql = "SELECT NO, TITLE, HIT, CREATED FROM BOARDS WHERE NO = ?"; 
			
			// 3. ps 생성
			ps = con.prepareStatement(sql);
			
			// 4. ? 표시에 값을 전달하기
			long findNo = 2;
			ps.setLong(1, findNo); // 1번째 ?에 findNo 전달하기
			
			// 5. 쿼리문 실행
			//    SELECT문 실행은 executeQuery() 메소드를 이용한다.
			//    executeQuery() 메소드의 반환 값은 ResultSet이다.
			//    ResultSet의 각 행(Row)은 next() 메소드를 호출해서 확인한다.
			//    결과 집합이 1개인 경우에는 분기문 if문을 이용해서 결과 집합의 유무를 확인한다.
			rs = ps.executeQuery();
			if(rs.next()) { // next() 메소드는 결과 행(ROW)이 있으면 true, 없으면 false 반환한다.
				/*
				 | no | title | hit | created  |
				 | 2  | 제목  | 10  | 22/03/10 | <- rs.next() 메소드로 가져올 수 있다.

				 rs에 저장된 행(ROW) 전체 데이터를 칼럼(column)으로 분리하는 방법
				 1. 칼럼의 이름
					1) rs.getLong("no")
					2) rs.getString("title")
					3) rs.getInt("hit")
					4) rs.getDate("created")
				 2. 칼럼의 번호(1부터 시작)
				 	1) rs.getLong(1)
				 	2) rs.getString(2)
				 	3) rs.getInt(3)
				 	4) rs.getDate(4)
				 */
				
				// DTO 작업 없이 하나의 행을 변수에 저장해서 출력하기
				long no = rs.getLong("no");
				String title = rs.getString("title");
				int hit = rs.getInt("hit");
				Date created = rs.getDate("created");
				System.out.println("글번호 " + no);
				System.out.println("제목 " + title);
				System.out.println("조회수 " + hit);
				System.out.println("작성일 " + created);
				
				// DTO 작업 후 , 읽어들인 데이터를 하나의 인스턴스로 만들고 getter 이용해서 출력하기
				Board board = new Board();
				board.setNo(no);
				board.setTitle(title);
				board.setHit(hit);
				board.setCreated(created);
				System.out.println("글번호 " + board.getNo());
				System.out.println("제목 " + board.getTitle());
				System.out.println("조회수 " + board.getHit());
				System.out.println("작성일 " + board.getCreated());
				
				
				
				
			} else {
				System.out.println("조회된 행(ROW)이 없습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// con, ps, rs의 종료
			// try가 아닌 finally 안에 있으므로 예외 처리를 해야한다.
			// 예외처리를 MyConnection 클래스에서 처리 메소드를 만들어서 처리
			MyConnection.close(con, ps, rs); 
		}
	}
	
	// 결과 집합(ResultSet)이 여러 개인 경우, 다중행 쿼리문
	// 결과 행 여러 개 -> List<Board> boards
	public static void selectList() {
		
		Connection con = null;
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			con = MyConnection.getConnection();
			
			sql = "SELECT no, title, hit, created FROM boards";
			ps = con.prepareStatement(sql);
			
			// 쿼리문 실행
			// ResultSet의 각 행(ROW)은 next() 메소드를 호출해서 확인한다.
			// 결과 집합이 여러 개인 경우에는 반복문 while문을 이용해서 결과 집합의 각 행을 하나씩 호출해서 확인한다.
			rs = ps.executeQuery();
			
			List<Board> boards = new ArrayList<Board>(); //반복문 시작 전 리스트 생성 
			// while 반복문이 한번씩 돌 때마다 boards 리스트에 한 줄 생성
			while(rs.next()) {
				
				// rs를 통해서 한 행을 가져와
				// Board board 생성(결과 행 한 줄이 board가 된다.)
				Board board = new Board();
				board.setNo(rs.getLong(1));
				board.setTitle(rs.getString(2));
				board.setHit(rs.getInt(3));
				board.setCreated(rs.getDate(4));
				
				// ArrayList에 board 저장(결과 행 1개를 boards 리스트에 add한다.)
				boards.add(board);
			}
			
			// 결과 집합 확인
			for(Board res : boards) {
				System.out.println("글번호 " + res.getNo());
				System.out.println("제목 " + res.getTitle());
				System.out.println("조회수 " + res.getHit());
				System.out.println("작성일 " + res.getCreated());
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			MyConnection.close(con, ps, rs);
			
		}
		
		
	}
	
	
	
	
	
	public static void main(String[] args) {

		//selectOne();
		selectList();
		
	}

}
