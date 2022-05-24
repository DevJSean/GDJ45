package com.goodee.ex10.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class NoticeDTO {
	private Long noticeNo;  // mybatis-config.xml의 옵션으로 DB의 snake_case 칼럼을 자바의 camelCase 필드로 인식시켜 준다.
	private String title;
	private String content;
	private Integer hit;
	private Timestamp created;
	private Timestamp lastModified;
}
