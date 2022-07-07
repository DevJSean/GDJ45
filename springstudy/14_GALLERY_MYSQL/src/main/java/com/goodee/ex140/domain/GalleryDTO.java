package com.goodee.ex140.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GalleryDTO {

	private Long galleryNo;
	private String writer;
	private String title;
	private String content;
	private String ip;
	private Long hit;
	private Date created;
	private Date modified;
	
}
