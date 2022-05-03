package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.MemberDTO;
import repository.MemberDAO;

public class DetailService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 요청 Parameter
		Optional<String> optNo = Optional.ofNullable(request.getParameter("no")); // no가 null이 가능하다
		Long no = Long.parseLong(optNo.orElse("0")); // 요청 파라미터가 있으면 그냥 쓰고, 없으면 0을 쓴다
		
		// 응답 데이터 형식
		// JSON
		response.setContentType("application/json; charset=UTF-8");
		
		// 응답 데이터 예시(성공)
		// {
		//      "result": true,
		//      "member": {
		// 		    "no": 1,
		//          "id": "user1",
		//          "name": "사용자1",
		//          "gender": "female",
		//          "address": "seoul"
		//      }
		// }
		// 응답 데이터 예시(실패)
		// {
		//    "result": false
		// }
		
		MemberDTO member = MemberDAO.getInstance().selectMemberByNo(no);
		// boolean result = (member != null); 아래와 같다
		boolean result = false;
		if(member != null) {
			result = true;
		}
		
		JSONObject obj = new JSONObject(); // DTO, VO, BEAN : 데이터를 여러 개 가지고 있는 객체
				// 없는 번호를 요청하면 MemberDAO.getInstance().selectMemberByNo(no) null값을 가지고 JSONObject를 만들지 말라는 예외가 뜬다.
		obj.put("result", result);
		obj.put("member", member == null ? null : new JSONObject(member));
		
		
		// 응답하기
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();
	}

}
