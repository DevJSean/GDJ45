package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import domain.StaffDTO;
import repository.StaffDAO;

public class ListService implements StaffService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException{

		
		response.setContentType("application/json; charset=UTF-8");
		List<StaffDTO> staffList = StaffDAO.getInstance().selectStaffList();
		//[{ }, { }, { }]
		JSONArray arr = new JSONArray(staffList);
		
		
		PrintWriter out = response.getWriter();
		out.print(arr.toString());
		out.flush();
		out.close();
	}

}