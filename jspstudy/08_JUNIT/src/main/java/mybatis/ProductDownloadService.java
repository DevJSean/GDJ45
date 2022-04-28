package mybatis;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDownloadService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String realPath = request.getServletContext().getRealPath("storage"); // 경로
		String target = request.getParameter("target"); // 파일명
		
		File file = new File(realPath, target);
				
		// 응답 헤더
		response.setHeader("Content-Disposition", "attachment; filename=" + target); 
		response.setHeader("Content-Length", file.length() + "");
		response.setHeader("Content-Type", "application/x-msdownload");
		
		try {

			// 다운로드 대상에 연결하는 입력 스트림
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); // 서버에 저장된 파일 읽기
	
			// 응답으로 내보낼 출력 스트림
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream()); // 응답(response)으로 보내기 
			
			
			// 다운로드 진행(입력 스트림으로 읽은 내용을 출력 스트림으로 보냄 : 파일 복사)
			byte[] b = new byte[1024];               
			int readByte = 0;                      
			while( (readByte = bis.read(b)) != -1) { 
				bos.write(b, 0, readByte);                  
			}										
			if(bos != null) bos.close();            
			if(bis != null) bis.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}

}
