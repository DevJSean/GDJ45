package domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StaffDTO {
	
	// field
	private String sno;		// 사원번호
	private String name;	// 사원명
	private String dept;	// 부서
	private int salary;		// 급여
}