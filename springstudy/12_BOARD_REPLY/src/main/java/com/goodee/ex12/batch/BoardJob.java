package com.goodee.ex12.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.goodee.ex12.mapper.BoardMapper;

// 이 클래스는 bean이라고 선언         // DBConfig에 이 애너테이션들을 적어야 한다.						   
@Component							   // @ComponentScan(basePackages = {"com.goodee.ex12.batch"})  // @Component를 com.goodee.ex12.batch에서 찾아라
public class BoardJob {				   // @EnableScheduling   // scheduling 허용한다.

	// batch 패키지 생성
	// 인터페이스 구현 없는 ~Job 클래스를 생성
	
	// quartz dependency, quartz-jobs dependency 추가
	
	// repository DI
	@Autowired
	private BoardMapper boardMapper;
	
	/*
	크론식
	1. 구성
	   초 분 시 일 월 요일 [년도]
	     		
	2. 상세
	   1) 초 : 0 ~ 59
	   2) 분 : 0 ~ 59
	   3) 시 : 0 ~ 23
	   4) 일 : 1 ~ 31
	   5) 월 : 0 ~ 11, JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
	   6) 요일 : 1(MON) ~ 7(SUN), MON, TUE, WED, THR, FRI, SAT, SUN
	     	
	3. 작성방법
	   1) * : 매번
	   2) ? : 설정 안함(일, 요일에서 작성)
	   3) / : 주기
	    a/b : a부터 b마다 동작
	   4) 작성예시 
	     	(1) 10초마다 동작         0/10 * * * * ?
	     	(2) 1분마다 동작          0 0/1 * * * ?
	     	(3) 5분마다 & 10초 후     10 0/5 * * * ? ( 10:00:10, 10:05:10, 10:10:10, ...)
	     	(4) 수요일 12시마다       0 0 12 ? * WED
	     	(5) 수요일과 금요일    
	     	   10:30, 11:30, 12:30    0 30 10~12 ? * WED,FRI 

	*/
	//        초 분 시 일 월 요일
	//cron = "*  *  *  *  *  *"   

	//0 0 * * * * : 매 시 0분 0초에 작업
	//*/15 * * * * * : 매 15초마다 작업
	//0 0 0 1,8,17,26 * * : 매달 1, 8, 17, 26일 자정에 작업
	//0 0 10-20 * * * : 매일 10시 ~ 20시 한시간 간격으로 작업
	//0 0 9-18 * * 1-5 : 월 ~ 금(평일) 9 ~ 18시 매 정각에 작업
	//0 0 */3 4 * * : 매달 4일에 3시간 간격으로 작업

	
	// @Scheduled애너테이션(크론식)
	/*
	@Scheduled(cron = "0/10 * * * * ?") // 크론식 문자열로 10초마다
	public void execute() {
		System.out.println("---쿼츠 동작 중---");
		System.out.println(boardMapper.selectBoardCount());            // 적당한 정수 값 반환하는 메소드
	}
	*/
	
}
