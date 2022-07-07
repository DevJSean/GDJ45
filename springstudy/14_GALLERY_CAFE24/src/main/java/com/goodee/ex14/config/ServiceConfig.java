package com.goodee.ex14.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.goodee.ex14.batch.DeleteAttachScheduler;
import com.goodee.ex14.service.GalleryService;
import com.goodee.ex14.service.GalleryServiceImpl;

@EnableScheduling
@Configuration
public class ServiceConfig {

	@Bean
	public GalleryService galleryService() {
		return new GalleryServiceImpl();
	}
	
	@Bean
	public DeleteAttachScheduler deleteAttachScheduler() {  // need @EnableScheduling
		return new DeleteAttachScheduler();
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSizePerFile(1024 * 1024 * 10);  // 하나당 최대 10MB (생략하면 -1 사용, 제한 없다는 뜻)
		multipartResolver.setMaxUploadSize(1024 * 1024 * 50);         // 전체 최대 50MB (생략하면 -1 사용, 제한 없다는 뜻)
		return multipartResolver;
	}
	
}
