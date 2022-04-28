package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyConnection {

	// field
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final static String USER = "SCOTT";
	private final static String PASSWORD = "1111";
	
	// method
	// 인스턴스에 의한 호출, 일반 메소드
	// MyConnection mycon = new Myconnection();
	// Connection con = myCon.getConnection();
	
	// 클래스에 의한 호출, 클래스 메소드(static 처리)
	// Connection con = MyConnection.getConnection();
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			// Oracle JDBC(ojdbc6.jar)에 있는 OracleDriver 클래스 로드 작업			
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);  // DriverManager의 getConnection 메소드로 DB 접속 얻어내기
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("OracleDriver 클래스를 찾을 수 없습니다.", e);
		} catch (SQLException e) {
			throw new RuntimeException("DB접속정보를 확인하세요.", e);
		}
		return con;  // 이제부터 MyConnection.getConnection()을 하면 connection을 얻어낼 수 있다.
	}
	
	// 이 구문 finally에서 매번 사용하니까 클래스 메소드로 만들어서 사용.
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) { //rs는 DQL에서 사용
		try {
			if(con != null) con.close();
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
