package com.goodee.ex16.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.ex16.service.GalleryService;


@Controller
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@GetMapping("/gallery/management")
	public String management() {
		return "gallery/manage";
	}
	
}
