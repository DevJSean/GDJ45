package com.goodee.ex05.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goodee.ex05.domain.MemberDTO;

public class MemberServiceImpl implements MemberService {
	
	@Override
	public String detail1(HttpServletRequest request) { // 컨트롤러에서 받아온 request에는 파라미터 id 와 pw가 들어있다.
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		return "입력 아이디 : " + id + " 입력 비밀번호 : " + pw; // 컨트롤러에 반환할 텍스트
		
	}
	
	
	// MemberService 인터페이스에서 만든 추상 메소드 중 아직 구현되지 않은 메소드는
	// 자동완성으로 바로 만들 수 있다.
	@Override
	public MemberDTO detail2(String id, String pw) {   // 컨트롤러에서 받아온 id와 pw
		
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPw(pw);
		
		return member; // 컨트롤러에 반환하는 memberDTO 객체 (bean)
	}
	
	
	@Override
	public Map<String, Object> detail3(MemberDTO member) { // 컨트롤러에서 받아온 MemberDTO 객체
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", member.getId());
		map.put("pw", member.getPw());
		
		return map; // 컨트롤러에 반환하는 Map
	}
	
	
	@Override
	public MemberDTO detail4(Map<String, Object> map) {
		
		MemberDTO member = new MemberDTO();
		member.setId(map.get("id").toString()); // map의 value는 Object이므로 꺼내서 캐스팅해야 한다.
		member.setPw(map.get("pw").toString());
		
		return member; // 컨트롤러에 반환하는 MemberDTO
	}
	
}
