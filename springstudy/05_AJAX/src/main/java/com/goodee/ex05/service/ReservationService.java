package com.goodee.ex05.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.goodee.ex05.domain.ReservationDTO;

public interface ReservationService {

	public ResponseEntity<ReservationDTO> detail1(HttpServletRequest request);
	       // 데이터를 감싸주는 일종의 wrapper이다.
	
	public ResponseEntity<ReservationDTO> detail2(Long no);
	
	public ResponseEntity<ReservationDTO> detail3(ReservationDTO reservation);
}
