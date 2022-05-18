package com.goodee.prac01.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BoardDTO {

	private Long no;
	private String writer;
	private String title;
	private String content;
	private String ip;
	private Long hit;
	private String date;
	private String lastModified;
	
}
