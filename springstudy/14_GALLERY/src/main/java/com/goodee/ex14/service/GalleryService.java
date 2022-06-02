package com.goodee.ex14.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.ex14.domain.FileAttachDTO;
import com.goodee.ex14.domain.GalleryDTO;

public interface GalleryService {

	public void findGalleries(HttpServletRequest request, Model model);
	public FileAttachDTO findFileAttachByNo(Long fileAttachNo);
	public GalleryDTO findGalleryByNo(Long galleryNo);
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response); // 첨부파일은 HttpServletRequest 사용 못한다.
	public void change(MultipartHttpServletRequest multipartRequest, HttpServletResponse response); // 첨부파일은 HttpServletRequest 사용 못한다.
	public void remove(MultipartHttpServletRequest multipartRequest, HttpServletResponse response); // 첨부파일은 HttpServletRequest 사용 못한다.
	
}
