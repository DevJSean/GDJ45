package com.goodee.ex12.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BoardDTO {

	private Long rn;	// 순번으로 rn을 사용하고 싶어서 DTO에 추가시켰다.
	
	private Long boardNo;
	private String writer;   // 사용자 ID가 들어올 것임, USER라는 테이블의 외래키라고 가정
	private String title;
	private String content;
	private Long hit;
	private String ip;
	private Date created;
	private Date modified;
}
