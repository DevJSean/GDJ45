package com.goodee.ex05.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.goodee.ex05.domain.ReservationDTO;

public class ReservationServiceImpl implements ReservationService {

	@Override
	public ResponseEntity<ReservationDTO> detail1(HttpServletRequest request) {
		
		Optional<String> optNo = Optional.ofNullable(request.getParameter("no"));
		Long no = Long.parseLong(optNo.orElse("0"));

		// 파라미터 no가 전달되지 않았다면 no의 값은 0이다.
		// no의 값이 0인 경우 조회가 불가능한 경우이다.
		
		// ResponseEntity<T>
		//    Ajax처리할 때 쓰는 클래스
		// 1. 실제 응답 데이터는 T 타입이다.  (실제로 보낼 데이터는 T에 해당하는 ReservationDTO이다.)
		// 2. HttpHeaders 클래스를 이용해서 응답 데이터의 ContentType(여기선 JSON)을 지정한다.
		//    produces를 사용하지 않는다.
		// 3. 응답 코드(HttpStatus)를 저장한다. (1이면 성공, 0이면 실패)
		
		ResponseEntity<ReservationDTO> responseEntity = null;
		
		// ResponseEntity로 전달할 결과 데이터 
		ReservationDTO reservation = new ReservationDTO(no, "예약자");
		
		// ResponseEntity로 전달할 응답 데이터의 Content-Type
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");
		
		if(no > 0) {  	// 사실 if문으로 성공/실패 처리를 하지 않아도 된다.
			// 성공
			responseEntity = new ResponseEntity<ReservationDTO>(reservation, header, HttpStatus.OK); // ajax의 success로 전달
		} else {
			// 실패
			responseEntity = new ResponseEntity<ReservationDTO>(null, header, HttpStatus.BAD_REQUEST); // ajax의 error로 전달 
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<ReservationDTO> detail2(Long no) {
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<>(new ReservationDTO(no, "예약자"), header, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ReservationDTO> detail3(ReservationDTO reservation) {
		
		
		return null;
	}

}