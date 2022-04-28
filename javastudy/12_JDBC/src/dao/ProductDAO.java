package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dto.Product;



// Singleton Pattern
// 1. 인스턴스를 하나만 만들 수 있는 패턴이다.
// 2. 방법
//     1) 인스턴스를 하나 선언한다 (dao)
//     2) 인스턴스를 외부에서 못 만들게 한다. (private 생성자)
//     3) getInstance() 메소드를 호출하면 선언한 인스턴스를 생성한 다음 반환한다.
//		  								  이미 생성했다면 그냥 반환한다.

public class ProductDAO {
	
	// dao는 필드이므로 기본 null 상태이다.
	// 클래스 메소드인 getInstance() 메소드에서 사용되려면
	// 클래스 변수로 설정해야 한다.
	private static ProductDAO dao;
	

	// private 생성자
    // private이므로 클래스 외부에서는 생성자를 호출할 수 없다. (인스턴스 생성을 할 수 없다. new ProductDAO 불가능)
	private ProductDAO() {
	}

	// 인스턴스 생성 방법 말고 getInstance() 메소드를 호출해야 하므로
	// 클래스 메소드로 처리해야 한다. (static 처리 후, 클래스 이름으로 호출, ProductDAO dao = ProductDAO.getInstance() )
	public static ProductDAO getInstance() {
		if(dao == null)
			dao = new ProductDAO();  // dao 인스턴스 만들어주는 작업
		return dao;
	}

	// 이후 getInstance()로 호출할 때, 첫 메소드 호출에서는 dao가 null이므로 dao가 완성이 되고
	// 그 다음 getInstance() 호출부터는 만들어 둔 dao를 반환만 한다.
	// ProductDAO dao1 = ProductDAO.getInstance() 
	// ProductDAO dao2 = ProductDAO.getInstance()
	// dao1과 dao2는 동일한 인스턴스이다.
	
	
	// 이전까지는 접속에 관련된 필드와 getConnection을 쿼리 실행 메소드마다 적었지만
	// 앞으로 DAO 클래스에 필드, getConnection을 넣어두고 여기서 처리한다.
	
	// field
	private Connection con;
	private String sql;
	private PreparedStatement ps;
	private ResultSet rs;
	
	// 접속, 외부에서 호출할 일이 없으므로 private
	private Connection getConnection() { 
		try {
			// db.properties 파일 내용 읽기
			InputStream in = new FileInputStream("db.properties");
			Properties properties = new Properties();
			properties.load(in);
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			
			// OracleDriver 로드
			Class.forName("oracle.jdbc.OracleDriver");
			
			// 접속 반환
			return DriverManager.getConnection(url, user, password);
			
		} catch (FileNotFoundException e) {
			throw new RuntimeException("db.properties 파일이 존재하지 않습니다.");
		} catch (IOException e) {
			throw new RuntimeException("db.properties 파일을 읽을 수 없습니다.");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("OracleDriver를 로드할 수 없습니다.");
		} catch (SQLException e) {
			throw new RuntimeException("DB 접속을 확인하세오.");
		}
	}
		
	// 접속 해제, 외부에서 호출할 일이 없으므로 private
	private void close() {
		try {
			if(con != null) con.close();
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 제품 등록
	public int insertProduct(String name, int price) {
		int res = 0;
		try {
			// 접속
			con = getConnection();// 동일한 클래스 내의 getConnection() 메소드 호출
			
			// 쿼리문 만들기
			sql = "INSERT INTO PRODUCT(no, name, price) VALUES(product_seq.NEXTVAL, ?, ?)";
			
			// ps 생성
			ps = con.prepareStatement(sql);
			
			// ?에 값 전달
			ps.setString(1, name);
			ps.setInt(2, price);
			
			// 쿼리문 실행
			res = ps.executeUpdate();
			
			// 반환 값으로 성공 실패 확인은 ProductService에서 처리 
			// 제품 등록이니까 출력할 필요 없음
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(); // 동일한 클래스 내의 close() 메소드 호출
		}
		return res; // int res 값 반환
	}
	
	// 제품 조회
	// 반환 타입이 dto 패키지의 Product임
	public Product selectProductByNo(long no) {
		Product product = null;
		try {
			con = getConnection();
			
			sql = "SELECT no, name, price FROM product WHERE no = ?";
			
			ps = con.prepareStatement(sql);
			
			ps.setLong(1, no);
			
			rs = ps.executeQuery();  // private ResultSet rs; ResultSet을 사용해야 executeQuery를 처리할 수 있음.
			if(rs.next()) {  // 행 단위로 데이터를 확인하면서 가지고 오는 next 메소드, 반드시 있어야 한다.
				product = new Product(); // 위에 선언만 한 product 만들기 방법(1. setter로 만들기 2.생성자로 만들기)
				product.setNo(rs.getLong("no"));       //rs.getLong(1)
				product.setName(rs.getString("name")); //rs.getString(2)
				product.setPrice(rs.getInt("price"));  //rs.getInt(3)
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return product;
	}
	
	
	// 제품 삭제
	public int deleteProduct(long no) {
		int res = 0;
		try {
			con = getConnection();
			
			sql = "DELETE FROM product WHERE no = ?";
			
			ps = con.prepareStatement(sql);
			
			ps.setLong(1, no);
			
			res = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return res;
	}
	

	// 제품 수정
	public int updateProduct(long no, String name, int price) {
		int res = 0;
		try {
			con = getConnection();
			
			sql = "UPDATE product SET name = ?, price = ? WHERE no = ?";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setLong(3, no);
			
			// 쿼리문 실행
			res = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return res;
		
	}
	
	// 전체 조회
	public List<Product> selectProductList() {
		
		List<Product> products = new ArrayList<Product>();// 리스트 생성 
		
		try {
			
			con = getConnection();
			
			sql = "SELECT no, name, price FROM product";
			
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			// while 반복문이 한번씩 돌 때마다 products 리스트에 한 행 생성
			while(rs.next()) {
				
				// rs를 통해서 한 행을 가져와
				// Product product 생성(결과 행 한 줄이 product가 된다.)
				Product product = new Product(rs.getLong("no"), rs.getString("name"), rs.getInt("price")); // 생성자 전달 방식
				
				// products ArrayList에 product 저장(결과 행 1개를 products 리스트에 add한다.)
				products.add(product);
				
				// products.add(new Product(rs.getLong("no"), rs.getString("name"), rs.getInt("price"));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return products;
	}

	// 범위 조회
	public List<Product> selectProductPartList(int begin, int end) {
		
		List<Product> products = new ArrayList<Product>();
		try {
			
			con = getConnection();
			
			sql = "SELECT p.no, p.name, p.price " +
					"FROM (SELECT ROW_NUMBER() OVER(ORDER BY no) AS rn, no, name, price " +
					        "FROM product) p " +
				   "WHERE p.rn BETWEEN ? AND ?";
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, begin);
			ps.setInt(2, end);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product product = new Product(rs.getLong("no"), rs.getString("name"), rs.getInt("price"));
				products.add(product);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return products;
	}
	
	
}
