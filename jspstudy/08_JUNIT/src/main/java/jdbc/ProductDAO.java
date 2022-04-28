package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	// productDAO에서 context.xml의 내용으로 
	// DataSource dataSource를 만든다.
	
	//singleton
	private static ProductDAO dao = new ProductDAO();
	private ProductDAO() { 
	}
	public static ProductDAO getInstance() {
		return dao;
	}
	
	// field
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// method
	
	// 1. 접속 해제하기
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) con.close(); // Connection의 반납
			if(con != null) ps.close();
			if(rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 2. 전체 제품목록 가져오기
	public List<ProductDTO> selectProductList() {
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		try {
			con = MyConnection.getInstance().getConnection();
			sql = "SELECT PRODUCT_NO, NAME, PRICE, IMAGE FROM PRODUCT ORDER BY PRODUCT_NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				// DTO에서 lombok @Builder 넣은 후 사용 가능
				// Builder 패턴을 이용한 ProductDTO 생성
				// 칼럼 이름으로 메소드(setter)를 사용한다.
				ProductDTO product = ProductDTO.builder()
				.product_no(rs.getLong("PRODUCT_NO"))
				.name(rs.getString("NAME"))
				.price(rs.getInt("PRICE"))
				.image(rs.getString("IMAGE"))
				.build();
			products.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return products;
	}
	
	// 3. 제품 삽입하기
	// 이전 버전은 예외 상황 처리가 사실상 없다.
	// ProductAddService.java로 예외를 던지고, 
	// ProductAddService.java에서 예외를 처리할 수 있도록 처리한다.
	// throws Exception : insertProduct() 호출하는 곳(AddService.java)으로 예외 처리를 맡긴다.
	public int insertProduct(ProductDTO product) throws Exception {
		int res = 0;
		con = MyConnection.getInstance().getConnection();
		
		sql = "INSERT INTO PRODUCT(PRODUCT_NO, NAME, PRICE, IMAGE) VALUES (PRODUCT_SEQ.NEXTVAL, ?, ?, ?)";
		
		ps = con.prepareStatement(sql);
		
		ps.setString(1, product.getName());
		ps.setInt(2, product.getPrice());
		ps.setString(3, product.getImage());
		
		res = ps.executeUpdate();
		
		close(con, ps, null);
		
		return res;
	}
	
	// 4. 제품 상세보기
	public ProductDTO selectProductByNo(Long product_no) { // 자바 이름규칙 무시하고 db에 맞춤
		ProductDTO product = null;
		try {
			con = MyConnection.getInstance().getConnection();
			sql = "SELECT PRODUCT_NO, NAME, PRICE, IMAGE FROM PRODUCT WHERE PRODUCT_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, product_no);
			rs = ps.executeQuery();
			if(rs.next()) {
				product = ProductDTO.builder()
					.product_no(rs.getLong("PRODUCT_NO"))
					.name(rs.getString("NAME"))
					.price(rs.getInt("PRICE"))
					.image(rs.getString("IMAGE"))
					.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return product;
	}
	
	// 5. 제품 삭제
	public int deleteProduct(Long product_no) {
		int res = 0;
		try {
			con = MyConnection.getInstance().getConnection();
			sql = "DELETE FROM PRODUCT WHERE PRODUCT_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, product_no);
			res = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return res;
	}
	
	// 6. 제품 개수 구하기
	public int getProductCount() {
		int count = 0;
		try {
			con = MyConnection.getInstance().getConnection();
			sql = "SELECT COUNT(*) AS NO FROM PRODUCT";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {               // rs.next()는 무조건 있어야 하고, 한 행이면 if() 여러 행이면 while()
				count = rs.getInt("NO");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return count;
	}
	
}
