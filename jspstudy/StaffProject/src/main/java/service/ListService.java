package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import repository.StaffDAO;

public class ListService implements StaffService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("application/json; charset=UTF-8");
		
		JSONArray arr = new JSONArray(StaffDAO.getInstance().selectStaffList());
		
		PrintWriter out = response.getWriter();
		out.write(arr.toString());
		out.flush();
		out.close();
		
		
	}

}
