package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.StaffDTO;

public class StaffDAO {
	
	// singleton, factory 작업
	
	// field
	private SqlSessionFactory factory;
	
	// 싱글톤 작업
	private static StaffDAO instance = new StaffDAO();
	
	// 생성자
	public StaffDAO() {
		try {
			String resource ="mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static StaffDAO getInstance() {
		return instance;
	}
	
	private final String MAPPER = "mybatis.mapper.staff.";
	
	// 메소드!
	// 모든 메소드들은 factory로 부터 SqlSession을 얻는다!
	// SqlSession ss = facotry.openSession();
	// ss.select~~.. 등등
	// ss.close()
	
	
	public List<StaffDTO> selectStaffList(){
		
		SqlSession ss = factory.openSession();
		List<StaffDTO> staffList = ss.selectList(MAPPER + "selectStaffList");
		ss.close();		
		
		return staffList;
	}
	
	public StaffDTO detailStaff(String sno) {
		
		SqlSession ss = factory.openSession();
		StaffDTO staff = ss.selectOne(MAPPER + "detailStaff", sno);
		ss.close();
		
		return staff;
	}
	
	public int addStaff(StaffDTO staff) {
		SqlSession ss = factory.openSession(false);
		int res = ss.insert(MAPPER + "addStaff", staff);
		if(res>0) ss.commit();
		ss.close();
		
		return res;
	}
	

	
}