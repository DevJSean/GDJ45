package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.MemberDTO;
import repository.MemberDAO;

public class ListService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 응답 데이터 형식
		// JSON
		response.setContentType("application/json; charset=UTF-8");
		
		// 응답 데이터 형식
		// {
		// 	"count": 3,
		//  "members": [
		// 		{
		//			"no": 1,
		//			"id": "user1",
		//			"name": "사용자1",
		//			"gender": "female",
		//			"address": "seoul"
		//		},
		//		...
		//   ]
		// }
		JSONObject obj = new JSONObject();
		obj.put("count", MemberDAO.getInstance().getMemberCount()); // JSONObject는 put으로 넣는다
		obj.put("members", MemberDAO.getInstance().selectMemberList()); // String key, Collection<?> value   : collection으로는 list와 set가 가능하다
		
		// 응답하기
		PrintWriter out = response.getWriter();
		out.write(obj.toString());
		out.flush();
		out.close();
	}

}
