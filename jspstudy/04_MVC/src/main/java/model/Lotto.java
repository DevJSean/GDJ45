package model;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Lotto implements MyService {

	
	//메소드
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Set<Integer> lotto = new HashSet<>();
		while(lotto.size()!= 6) {
			lotto.add((int)(Math.random() * 45) + 1);
		}
		
		request.setAttribute("result", lotto.toString());
		
		// 어디로 갈 것인가?
		ActionForward af = new ActionForward();
		af.setView("views/output.jsp"); 
		
		// 어떻게 갈 것인가?
		af.setRedirect(false); // forward
		
		// ActionForward 반환
		return af;
		
	}
}
