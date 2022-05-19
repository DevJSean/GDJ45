package com.goodee.ex08.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // getter/setter/toString이 들어있다.
@AllArgsConstructor
@NoArgsConstructor

public class BookDTO {

	private Long book_no;
	private String title;
	private String author;
	private Integer price;
	private String pubDate;
	private String regiDate;
	
}
