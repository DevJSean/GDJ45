package mybatis;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class ProductDAO {

	// MybatisConfig.java의 내용을 여기로 합치기
	private SqlSessionFactory factory;
	private static ProductDAO instance = new ProductDAO();
	
	private ProductDAO() {
		try {
			String resource = "mybatis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static ProductDAO getInstance() {
		return instance;
	}
	
	// 1. 전체 제품 조회하기
	public List<ProductDTO> selectProductList() {
		
		SqlSession ss = factory.openSession();
		
		List<ProductDTO> list = ss.selectList("mybatis.product.selectProductList");
		ss.close();
		return list;
	}
	
	
	// 2. 제품 등록하기
	public int insertProduct(ProductDTO product) {
		SqlSession ss = factory.openSession(false);
		
		int res = ss.insert("mybatis.product.insertProduct", product);
		
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
	// 3. 개별 제품 상세조회
	public ProductDTO selectProductByNo(Long productNo) {
		SqlSession ss = factory.openSession();
		ProductDTO product = ss.selectOne("mybatis.product.selectProductByNo", productNo);
		ss.close();
		return product;
	}
	
	// 4. 제품 삭제하기
	public int deleteProduct(Long productNo) {
		SqlSession ss = factory.openSession(false);
		int res = ss.delete("mybatis.product.deleteProduct", productNo);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
	
}
