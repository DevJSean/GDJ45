package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import repository.MemberDAO;

public class RemoveService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 요청 parameter
		Optional<String> optNo = Optional.ofNullable(request.getParameter("no")); // no가 null이 가능하다
		Long no = Long.parseLong(optNo.orElse("0")); // 요청 파라미터가 있으면 그냥 쓰고, 없으면 0을 쓴다
		
		// 응답 메시지 형식
		// JSON
		response.setContentType("application/json; charset=UTF-8");
		
		// 응답 메시지 예시
		// 성공 {"res": 1}
		// 실패 {"res": 0}
		JSONObject obj = new JSONObject();
		obj.put("res", MemberDAO.getInstance().deleteMember(no));
				
		// 응답하기
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();

	}
}
