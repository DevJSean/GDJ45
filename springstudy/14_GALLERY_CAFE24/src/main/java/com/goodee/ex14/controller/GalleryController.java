package com.goodee.ex14.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String galleryList(HttpServletRequest request, Model model) {
		galleryService.findGalleries(request, model);
		return "gallery/list";
	}
	
	@GetMapping("/gallery/write")
	public String galleryWrite() {
		return "gallery/write";
	}
	
	@PostMapping("/gallery/save")
	public void gallerySave(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		galleryService.saveGallery(multipartRequest, response);
	}
	
	/*
		/gallery/display?type=thumb  썸네일 보내주기
		
		/gallery/display             원본이미지 보내주기
		/gallery/display?type=image
	*/
	
	@ResponseBody
	@GetMapping("/gallery/display")
	public ResponseEntity<byte[]> galleryDisplay(HttpServletRequest request) {
		return galleryService.display(request);		
	}
	
	@GetMapping("/gallery/detail")
	public String galleryDetail(HttpServletRequest request, Model model) {
		galleryService.findGalleryByNo(request, model);
		return "gallery/detail";
	}
	
	@ResponseBody
	@GetMapping("/gallery/download")
	public ResponseEntity<Resource> galleryDownload(HttpServletRequest request) {
		return galleryService.download(request);
	}
	
	@GetMapping("/gallery/remove")
	public void galleryRemove(HttpServletRequest request, HttpServletResponse response) {
		galleryService.removeGallery(request, response);
	}
	
	@GetMapping("/gallery/edit")
	public String galleryEdit(HttpServletRequest request, Model model) {
		galleryService.findGalleryByNo(request, model);
		return "gallery/edit";
	}
	
	@PostMapping("/gallery/modify")
	public void galleryModify(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		galleryService.modifyGallery(multipartRequest, response);
	}
	
	@GetMapping("/gallery/removeFileAttach")
	public void galleryRemoveFileAttach(HttpServletRequest request, HttpServletResponse response) {
		galleryService.removeFileAttach(request, response);
	}
	
}
