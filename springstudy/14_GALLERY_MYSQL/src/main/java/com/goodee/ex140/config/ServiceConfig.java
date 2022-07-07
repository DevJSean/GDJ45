package com.goodee.ex140.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.goodee.ex140.service.GalleryService;
import com.goodee.ex140.service.GalleryServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean
	public GalleryService galleryService() {
		return new GalleryServiceImpl();
	}
	
}
