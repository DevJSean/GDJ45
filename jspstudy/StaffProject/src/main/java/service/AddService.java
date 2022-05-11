package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.exceptions.PersistenceException;
import org.json.JSONObject;

import domain.StaffDTO;
import repository.StaffDAO;

public class AddService implements StaffService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String sno = request.getParameter("sno");
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");
		int salary = Integer.parseInt(request.getParameter("salary"));
		
		StaffDTO staff = StaffDTO.builder()
				.sno(sno)
				.name(name)
				.dept(dept)
				.salary(salary)
				.build();
		
		PrintWriter out = null;
		try {
			int res = StaffDAO.getInstance().insertStaff(staff);
			
			response.setContentType("application/json; charset=UTF-8");
			
			JSONObject obj = new JSONObject();
			obj.put("res", res);
			String responseText = obj.toString();
			
			out = response.getWriter();
			out.write(responseText); 
			out.flush();
			out.close();
		} catch (PersistenceException e) {
			response.setContentType("text/plain; charset=UTF-8");
			
			response.setStatus(1001);
			out = response.getWriter();
			out.write("저장할 수 없는 값이 전달되었습니다.");
			out.flush();
			out.close();
		}
	}

}
