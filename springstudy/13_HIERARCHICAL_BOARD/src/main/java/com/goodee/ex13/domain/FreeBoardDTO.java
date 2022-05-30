package com.goodee.ex13.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FreeBoardDTO {

	private Long rowNum; // 행 번호 추가를 위해서 추가
	
	private Long freeBoardNo;
	private String writer;
	private String content;
	private String ip;
	private Date created;
	private Date modified;
	private Integer state;
	private Integer depth;
	private Integer groupNo;
	private Integer groupOrd;
	
}
