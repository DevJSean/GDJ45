package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.StaffDTO;
import repository.StaffDAO;

public class DetailService implements StaffService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {


		// 매개변수 처리
		Optional<String> optSno = Optional.ofNullable(request.getParameter("query"));
		String sno = optSno.orElse("0");
		
		response.setContentType("application/json; charset=UTF-8");
		
		StaffDTO staff = StaffDAO.getInstance().detailStaff(sno);
		JSONObject obj = new JSONObject(staff);
		
		PrintWriter out = response.getWriter();
		out.print(obj.toString());
		out.flush();
		out.close();
	}

}