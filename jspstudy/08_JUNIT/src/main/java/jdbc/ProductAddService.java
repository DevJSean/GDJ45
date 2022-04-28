package jdbc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProductAddService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		/* 1. 파일 업로드
		      폼에서 method="post" enctype="multipart/form-data"로 넘겼기 때문에 request 사용 불가.*/
			
		// 1) 업로드 할 디렉터리 경로 알아내기(서버 내부 realPath)
		String realPath = request.getServletContext().getRealPath("storage"); // C:\GDJ45\jspstudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\08_JUNIT\storage
			
		// 2) 업로드 할 디렉터리 없으면 만들기
		File dir = new File(realPath);
		if(dir.exists() == false) {
			dir.mkdir(); 
		}
		
		// 3) MultipartRequest 인스턴스 만들기
		//    여기서 업로드가 진행된다.
		File file = null;
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(request, realPath, 1024 * 1024 * 10, "UTF-8", new DefaultFileRenamePolicy()); 
			                                     //  첨부 가능한 최대 크기, 인코딩, 동일한 이름의 파일이 들어왔을 때 번호 지정
		} catch (IOException e) {
			error(file, response, "파일 첨부가 실패했습니다.");
		}
		
		file = mr.getFile("filename"); // 서버에 첨부된 파일을 알아내는 방법
		
		// 2. DB 저장
		// System.out.println("name : " + request.getParameter("name")); method="post" 방식은 request를 이용해서 parameter를 얻을 수 없다.
		// System.out.println("price : " + request.getParameter("price")); method="post" 방식은 request를 이용해서 parameter를 얻을 수 없다.
		// System.out.println("name : " + mr.getParameter("name")); // MultipartRequest mr 로 알아낸다.
		// System.out.println("price : " + mr.getParameter("price"));
		// System.out.println("origin : " + mr.getOriginalFileName("filename")); // 올린 사람이 지정한 이름
		// System.out.println("filesystem : " + mr.getFilesystemName("filename")); // 하드디스크에 저장되는 이름, DB에 이것을 사용해야 한다.
		String name = mr.getParameter("name");
		Integer price = Integer.parseInt(mr.getParameter("price"));
		String image = mr.getFilesystemName("filename");
		
		ProductDTO product = ProductDTO.builder().name(name).price(price).image(image).build(); // builder 패턴
		
		ActionForward af = null;
		try {
			int res = ProductDAO.getInstance().insertProduct(product);
			if(res > 0) {
				af = new ActionForward("/JUNIT/list.do", true);
			}
		} catch (SQLIntegrityConstraintViolationException e) { // UNIQUE, NOT NULL
			error(file, response, "제품명을 등록하지 않았거나\\n동일한 제품명이 이미 등록되어 있습니다.");
		} catch (SQLException e) { // COLUMN TYPE, SIZE
			error(file, response, "저장할 수 없는 데이터가 포함되어 있습니다.");
		} catch (Exception e) {
			error(file, response, "알 수 없는 예외가 발생했습니다.");
		}
		/* catch (Exception e) {
			// 예외 클래스의 이름 확인하기
			System.out.println("예외클래스명 " + e.getClass().getName()); 
			// 1. name이 null일 때
			// 2. name에 중복된 이름을 넣었을 때
			// java.sql.SQLIntegrityConstraintViolationException  SQL제약조건 위반이라는 뜻
			
			// 3. name에 값을 20 BYTE 초과해서 넣었을 때
			// java.sql.SQLException
		}*/
		
		return af;
	}
	
	// 3. 예외 처리(예외에 따른 응답 만들기)
	//    여러 번 반복되는 코드라서 따로 메소드로 만들기
	public void error(File file, HttpServletResponse response, String msg) {
		if(file != null && file.exists()) { // 파일이 null이 아니고 존재하면 (첨부가 되었으면)
			file.delete();  // 파일 삭제
		}
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + msg +"')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
			out.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}


}
