package com.goodee.ex11.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private Integer salary;
	private Double commissionPct;
	private Integer managerId;
	private Integer departmentId;
	
	private String departmentName; // 부서이름을 가져오려고 외부조인 했는데, DTO에 departmentName 필드가 없어서
								   // departmentName을 담을 수 없는 상황이라
								   // 추가로 필드를 등록한다.
	
}