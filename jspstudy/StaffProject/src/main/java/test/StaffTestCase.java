package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.StaffDTO;
import repository.StaffDAO;

class StaffTestCase {

	@BeforeEach
	void 등록테스트() {
		
		StaffDTO staff = StaffDTO.builder()
				.sno("99999")
				.name("김기획")
				.dept("기획부")
				.salary(5000)
				.build();
		int res = 0;
		try {
			res = StaffDAO.getInstance().insertStaff(staff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(1, res, "사원 등록에 문제가 있습니다.");
	}
	
	
	@Test
	void 조회테스트() {
		
		StaffDTO staff = StaffDAO.getInstance().selectStaffByNo("99999");
		assertNotNull(staff);
	}

}
