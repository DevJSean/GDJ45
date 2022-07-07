package com.goodee.ex14.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface GalleryService {
	
	// 갤러리 목록
	public void findGalleries(HttpServletRequest request, Model model);

	// 갤러리 상세 보기
	public void findGalleryByNo(HttpServletRequest request, Model model);
	public ResponseEntity<byte[]> display(HttpServletRequest request);
	public ResponseEntity<Resource> download(HttpServletRequest request);
	
	// 갤러리 삽입
	public void saveGallery(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	
	// 갤러리 삭제
	public void removeGallery(HttpServletRequest request, HttpServletResponse response);
	
	// 갤러리 수정
	public void modifyGallery(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	
	// 첨부 파일 삭제
	public void removeFileAttach(HttpServletRequest request, HttpServletResponse response);
	
}
