package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import domain.MemberDTO;
import repository.MemberDAO;

public class ListService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 응답 데이터 형식
		// JSON
		response.setContentType("application/json; charset=UTF-8");
		
		// 응답 데이터 형식
		// [
		// 		{
		//			"no": 1,
		//			"id": "user1",
		//			"name": "사용자1",
		//			"gender": "female",
		//			"address": "seoul"
		//		},
		//		...
		// ]
		List<MemberDTO> members = MemberDAO.getInstance().selectMemberList();
		JSONArray arr = new JSONArray(members); 
		
		// 응답하기
		PrintWriter out = response.getWriter();
		out.write(arr.toString());
		out.flush();
		out.close();
	}

}
