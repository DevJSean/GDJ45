package com.goodee.movie.batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.goodee.movie.domain.MovieDTO;
import com.goodee.movie.mapper.MovieMapper;
import com.goodee.movie.util.SecurityUtils;

					   
@Component							   
public class MovieJob {				  

	@Autowired
	private MovieMapper movieMapper;

	
	@Scheduled(cron = "0 0/1 * * * ?") 
	public void execute() {
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("column", "GENRE");
		searchMap.put("query", "코미디");
		
		List<MovieDTO> list = movieMapper.selectMovie(searchMap);

		if(list.isEmpty()) {
			try {
				File file = new File("C:\\GDJ45\\springstudy\\MovieProject\\error.txt");
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write("코미디 검색 결과가 없습니다.");
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				File file = new File("C:\\GDJ45\\springstudy\\MovieProject\\코미디.txt");
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				for(int i = 0, length = list.size(); i < length; i++) {
					MovieDTO movie = list.get(i);
					bw.write("제목 : " + movie.getTitle() + "\n");
					bw.write("장르 : " + movie.getGenre() + "\n");
					bw.write("개요 : " + movie.getDescription() + "\n");
					bw.write("평점 : " + movie.getStar() + "\n");
				}
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
