package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.StaffDTO;

public class StaffDAO {

	private SqlSessionFactory factory;
	private static StaffDAO instance = new StaffDAO();
	
	private StaffDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static StaffDAO getInstance() {
		return instance;
	}
			
	
	
	// 1. 전체 사원 조회
	public List<StaffDTO> selectStaffList() {
		SqlSession ss = factory.openSession();
		List<StaffDTO> staffs = ss.selectList("mybatis.mapper.staff.selectStaffList");
		ss.close();
		return staffs;
	}
	
	// 2. 사원 번호로 조회
	public StaffDTO selectStaffByNo(String searchSno) {
		SqlSession ss = factory.openSession();
		StaffDTO staff = ss.selectOne("mybatis.mapper.staff.selectStaffByNo", searchSno);
		ss.close();
		return staff;
	}
	
	// 3. 사원 등록
	public int insertStaff(StaffDTO staff) {
		SqlSession ss = factory.openSession(false);
		int res = ss.insert("mybatis.mapper.staff.insertStaff", staff);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
	
}
