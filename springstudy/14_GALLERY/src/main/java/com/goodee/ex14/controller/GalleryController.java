package com.goodee.ex14.controller;

import java.io.File;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.ex14.domain.FileAttachDTO;
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
		
		// 보내줘야 할 이미지 정보(path, saved) 읽기
		FileAttachDTO fileAttach = galleryService.findFileAttachByNo(fileAttachNo);
		
		// 보내줘야 할 이미지
		File file = null;
		switch(type) {
		case "thumb":
			file = new File(fileAttach.getPath(), "s_" + fileAttach.getSaved());
			break;
		case "image":
			file = new File(fileAttach.getPath(), fileAttach.getSaved());
			break;
			
		}
		
		// ResponseEntity
		ResponseEntity<byte[]> entity = null;
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK); // FileCopyUtils.copyToByteArray(file) 파일을 바이트 배열로 바꿔주기
																											  // 스프링에서 제공
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
}














