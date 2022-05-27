package com.goodee.ex12.util;

public class SecurityUtils {

	//크로스 사이트 스크립팅
	
	// new SecurityUtils 없이 메소드 호출하기 위해서  static메소드 구현
	
	public static String XSS(String s) {
		s = s.replaceAll("<", "&lt;");
		s = s.replaceAll(">", "&gt;");
		s = s.replaceAll("\"", "&quot;");
		s = s.replaceAll("\'", "&#x27");
		return s;
	}
	
	// 사용자의 입력을 Controller에서 처리하면 된다.
}
