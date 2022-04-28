package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface EmpService {  
	// 구현하는 곳에서 try catch 매번 안하도록 예외 던지기
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
