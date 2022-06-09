package com.goodee.ex15.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SignOutMemberDTO {

	private Long SignOutNo;
	private Long memberNo;
	private String id;
	private String pw;
	private String name;
	private String email;
	private Integer agreeState;
	private Date SignOut;
	
	
}
