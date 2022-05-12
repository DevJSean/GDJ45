package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.StaffDTO;
import repository.StaffDAO;

class StaffTestCase {

	@BeforeEach
	void 직원등록테스트() {
		
		StaffDTO staff = StaffDTO.builder()
				.sno("99999")
				.name("김기획")
				.dept("기획부")
				.salary(5000)
				.build();
		
		int res = StaffDAO.getInstance().addStaff(staff);
		assertEquals(1, res, "직원등록실패");
	}
	

	@Test
	void 직원조회테스트() {
		StaffDTO staff = null;
		staff = StaffDAO.getInstance().detailStaff("99999");
		
		assertNotNull(staff);
	}

}