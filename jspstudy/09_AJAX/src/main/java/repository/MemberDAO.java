package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.MemberDTO;

public class MemberDAO {

	private SqlSessionFactory factory;
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static MemberDAO getInstance() {
		return instance;
	}
	
	
	
	// 1. 회원 삽입
	public int insertMember(MemberDTO member) {
		SqlSession ss = factory.openSession(false);
		int res = ss.insert("mybatis.mapper.member.insertMember", member);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
	// 2. 전체 회원 조회
	public List<MemberDTO> selectMemberList() {
		SqlSession ss = factory.openSession();
		List<MemberDTO> members = ss.selectList("mybatis.mapper.member.selectMemberList");
		ss.close();
		return members;
	}
	
	// 3. 전체 회원 수 조회
	public int getMemberCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne("mybatis.mapper.member.getMemberCount");
		ss.close();
		return count;
	}
	
	// 4. 회원 상세 조회
	public MemberDTO selectMemberByNo(Long no) {
		SqlSession ss = factory.openSession();
		MemberDTO member = ss.selectOne("mybatis.mapper.member.selectMemberByNo", no);
		ss.close();
		return member;
	}
	
	// 5. 회원 정보 수정
	public int updateMember(MemberDTO member) {
		SqlSession ss = factory.openSession(false);
		int res = ss.update("mybatis.mapper.member.updateMember", member);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
	
	// 6. 회원 삭제
	public int deleteMember(Long no) {
		SqlSession ss = factory.openSession(false);
		int res = ss.delete("mybatis.mapper.member.deleteMember", no);
		if(res > 0) {
			ss.commit();
		}
		ss.close();
		return res;
	}
}
