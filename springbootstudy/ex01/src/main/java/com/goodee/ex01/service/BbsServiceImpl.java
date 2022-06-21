package com.goodee.ex01.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goodee.ex01.mapper.BbsMapper;
import com.goodee.ex01.util.MyFileUtils;

@Service
public class BbsServiceImpl implements BbsService{

	@Autowired
	private BbsMapper bbsMapper;
	
	@Override
	public Map<String, Object> uploadSummernoteImage(MultipartHttpServletRequest multipartRequest) {
		
		// 에디터에 첨부된 파일
		// summernote 이미지 첨부는 한 장만 가능해서 getFile
		MultipartFile multipartFile = multipartRequest.getFile("file"); 
		
		// 저장할 파일명
		String saved = MyFileUtils.getUuidName(multipartFile.getOriginalFilename());
		
		// 저장할 경로
		String path = "C:" + File.separator +"upload" + File.separator + "summernote";
		
		// 경로가 없으면 만들기
		File dir = new File(path);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// 저장할 File 객체
		File file = new File(dir, saved);
		
		// File 객체 저장
		try {
			multipartFile.transferTo(file);
		} catch (Exception e) {
			try {
				FileUtils.forceDelete(file); // 예외 발생하면 삭제
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		// 저장된 파일의 경로를 반환
		Map<String, Object> map = new HashMap<>();
		map.put("src", "/getImage/" + saved);    
		return map;
	}
}
