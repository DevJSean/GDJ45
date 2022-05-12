package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.StaffDTO;
import repository.StaffDAO;

public class AddService implements StaffService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("application/json; charset=UTF-8");
		
		// 파라미터로 받아온 것들
		// sno, name, dept, salary
		String sno = request.getParameter("sno");
		
		// 이름은 not null 이 아니므로!
		String name;
		if(request.getParameter("name").isEmpty()) {
			name = "null";
		}
		else {
			Optional<String> optName = Optional.ofNullable(request.getParameter("name"));
			name = optName.orElse("null");
		}
		
		String dept =request.getParameter("dept");
		
		
		String deptSalary = "0";
		if(dept.equals("기획부")) deptSalary="5000";
		else if(dept.equals("개발부")) deptSalary ="6000";
		else if(dept.equals("영업부")) deptSalary ="7000";
		else deptSalary="4000";
		
		int salary = Integer.parseInt(deptSalary);
		
		StaffDTO staff = StaffDTO.builder()
				.sno(sno)
				.name(name)
				.dept(dept)
				.salary(salary)
				.build();
		
		PrintWriter out = null;
		try {
			int res = StaffDAO.getInstance().addStaff(staff);			
		
			JSONObject obj = new JSONObject();
			obj.put("res", res);
			
			out = response.getWriter();
			out.print(obj.toString());
			out.flush();
			out.close();			
		} catch (Exception e) {
			response.setContentType("text/plain; charset=UTF-8");
			response.setStatus(1001);
			
			out = response.getWriter();
			out.print("저장할 수 없는 값이 저장되었습니다.");
			out.flush();
			out.close();
		}
		
	}

}