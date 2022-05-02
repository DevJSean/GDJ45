package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException ;
	//  반환 값이 void이다.
}
