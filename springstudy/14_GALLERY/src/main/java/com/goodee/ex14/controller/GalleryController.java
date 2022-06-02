package com.goodee.ex14.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.ex14.service.GalleryService;

@Controller
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/gallery/list")
	public String list(HttpServletRequest request, Model model) {
		galleryService.findGalleries(request, model);
		return "gallery/list";
	}
	
	@GetMapping("/gallery/savePage")
	public String savePage() {
		return "gallery/save";
	}
	
	@PostMapping("/gallery/save")
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		galleryService.save(multipartRequest, response);
	}
	
	/*
	 	/gallery/display?type=thumb 썸네일 보내주기
	 	/gallery/display            원본이미지 보내주기
	 	/gallery/display?type=image 원본이미지 보내주기
	*/
	@GetMapping("/gallery/display")
	@ResponseBody // jsp가 아니라 값 반환했다.
	public ResponseEntity<byte[]> display(Long fileAttachNo, @RequestParam(value="type", required=false, defaultValue="image") String type) {
		return galleryService.display(fileAttachNo, type);
	}
}














