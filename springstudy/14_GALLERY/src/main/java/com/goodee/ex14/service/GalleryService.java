package com.goodee.ex14.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface GalleryService {

	public void findGalleries(HttpServletRequest request, Model model);
	public ResponseEntity<byte[]> display(Long fileAttachNo, String type);
	public void findGalleryByNo(HttpServletRequest request, Model model);
	public ResponseEntity<Resource> download(String userAgent, Long fileAttachNo);
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response); // 첨부파일은 HttpServletRequest 사용 못한다.
	public void change(MultipartHttpServletRequest multipartRequest, HttpServletResponse response); // 첨부파일은 HttpServletRequest 사용 못한다.
	public void remove(MultipartHttpServletRequest multipartRequest, HttpServletResponse response); // 첨부파일은 HttpServletRequest 사용 못한다.
	
}
