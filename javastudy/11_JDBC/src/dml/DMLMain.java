package dml;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connect.MyConnection;

public class DMLMain {

	// DML : INSERT, UPDATE, DELETE
	// COMMIT과 ROLLBACK을 사용할 필요가 있으나,
	// JDBC는 Auto Commit을 사용하므로 코드를 작성할 필요가 없다.
	
	// INSERT, UPDATE, DELETE문을 처리하는 자바 코드는 동일하다.
	
	// INSERT
	public static void insert1() {
		
		/* DB 접속    */  Connection con = null;
		/* 쿼리문자체 */  String sql = null;
		/* 쿼리문실행 */  PreparedStatement ps = null;
		/* 실행결과   */  int res = 0;
		
		try {
			// 1. DB접속
			// MyConnection 클래스의 getConnection 메소드를 호출한다.
			// getConnection 메소드로 넘어온 결과가 exception일 수도 있으니 전체 try catch 처리를 한다.
			con = MyConnection.getConnection();
			
			// 2. 쿼리문 작성 
			//    쿼리문 자체에는 세미콜론을 작성하지 않는다.
			sql = "INSERT INTO boards(no, title, hit, created) VALUES(boards_seq.NEXTVAL, '긴급공지', 0, TO_DATE('22/03/07', 'YY/MM/DD'))";
			
			// 3. 쿼리문 실행 인스턴스 ps 생성
			//    쿼리문을 미리 준비해서 전달해야 한다.
			ps = con.prepareStatement(sql);
			
			// 4. 쿼리문 실행
			//    1) DML(INSERT, UPDATE, DELETE)의 실행 메소드는 executeUpdate() 이다.
			//    2) executeUpdate() 메소드의 반환 값 = 실행의 결과 (이 학습에서는 정수 res)
			//      - 의미 : 작업이 수행된 행(row)의 개수가 반환된다.
			//		(1) 0 : 작업이 수행되지 않았다.
			//      (2) 1 : 작업이 수행되었다. 두 행이 변경되면 2가 반환, 더 높은 수도 나온다.
			res = ps.executeUpdate();
			
			if(res > 0) { // 0보다 크면 작업이 수행됨
				//con.commit(); 커밋하겠다. JDBC는 Auto Commit 되므로 생략해도 됨.
				System.out.println("게시글이 추가되었습니다.");
			} else {
				System.out.println("게시글이 추가되지 않았습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// con과 ps의 종료, 반납
			// try가 아닌 finally 안에 있으므로 예외 처리를 해야한다.
			try {	
				if(con != null) con.close();
				if(ps != null) ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// INSERT   변수 잡아서 삽입하는 방법
	public static void insert2() {
		/* DB 접속    */  Connection con = null;
		/* 쿼리문자체 */  String sql = null;
		/* 쿼리문실행 */  PreparedStatement ps = null;
		/* 실행결과   */  int res = 0;
		
		
		try {
			// 1. DB접속
			con = MyConnection.getConnection();
			
			// 2. 쿼리문 작성
			//    ? 자리에 값을 전달해야 실행할 수 있다.
			sql = "INSERT INTO BOARDS(no, title, hit, created) VALUES(boards_seq.NEXTVAL, ?, ?, SYSDATE)";
			
			// 3. ps 생성
			ps = con.prepareStatement(sql);
			
			// 4. ? 표시에 값을 전달하기
			String title = "필독";  // String을 전달할 때는 setString()
			int hit = 10;			// int를 전달할 때는 setInt()
			ps.setString(1, title); // 1번째 ?에 title 전달
			ps.setInt(2, hit);      // 2번째 ?에 hit 전달
			
			// 5. 쿼리문 실행
			res = ps.executeUpdate();
			
			if(res > 0) {
				System.out.println("게시글이 추가되었습니다.");
			} else {
				System.out.println("게시글이 추가되지 않았습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			MyConnection.close(con, ps, null);  // 반복되던 구문 DMLMain에 클래스 메소드로 만들어서 활용
		}
	}
	
	//UPDATE
	public static void update() {
		/* DB 접속    */  Connection con = null;
		/* 쿼리문자체 */  String sql = null;
		/* 쿼리문실행 */  PreparedStatement ps = null;
		/* 실행결과   */  int res = 0;

		try {
			con = MyConnection.getConnection();
			sql = "UPDATE boards SET title = ?, hit = ? WHERE no = ?";
			ps = con.prepareStatement(sql);
			String title = "수정제목";
			int hit = 99;
			long no = 2; // int로 처리하면 약 21억개 이상은 안들어가니까 long으로 처리
			ps.setString(1, title);
			ps.setInt(2, hit);
			ps.setLong(3, no);
			res = ps.executeUpdate();
			if(res > 0) {
				System.out.println("게시글이 수정되었습니다.");
			} else {
				System.out.println("게시글이 수정되지 않았습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			MyConnection.close(con, ps, null);  // 반복되던 구문 DMLMain에 클래스 메소드로 만들어서 활용
		}
	}
	
	//DELETE
	public static void delete() {
		Connection con = null;
		String sql = null;
		PreparedStatement ps = null;
		int res = 0;
		
		try {
			con = MyConnection. getConnection();
			sql = "DELETE FROM BOARDS WHERE no = ?";
			ps = con.prepareStatement(sql);
			long no = 1;
			ps.setLong(1, no);
			res = ps.executeUpdate();
			if(res > 0) {
				System.out.println("게시글이 삭제되었습니다.");
			} else {
				System.out.println("게시글이 삭제되지 않았습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			MyConnection.close(con, ps, null);  // 반복되던 구문 DMLMain에 클래스 메소드로 만들어서 활용
		} 
	}
	
	public static void main(String[] args) {

		//insert1();
		//insert2();
		//update();
		delete();
		
	}

}
