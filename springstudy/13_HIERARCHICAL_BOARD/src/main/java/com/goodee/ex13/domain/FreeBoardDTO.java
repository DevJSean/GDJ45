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
	private Integer state;
	private Integer depth;
	private Long groupNo; // freeBoardNo의 값을 가지기 때문에 Long이어야 한다.
	private Integer groupOrd;
	
}
