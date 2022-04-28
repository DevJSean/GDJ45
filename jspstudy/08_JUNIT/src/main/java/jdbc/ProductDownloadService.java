package jdbc;

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
		response.setHeader("Content-Disposition", "attachment; filename=" + target); // filename=" + target은 파일명을 target으로 지정하는 방식. 적지 않으면 다른 이름으로 저장이 뜬다.
		response.setHeader("Content-Length", file.length() + "");
		response.setHeader("Content-Type", "application/x-msdownload");
		
		try {

			// 다운로드 대상에 연결하는 입력 스트림
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); // 서버에 저장된 파일 읽기
	
			// 응답으로 내보낼 출력 스트림
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream()); // 응답(response)으로 보내기  (파일 읽기 -> 응답으로 보내기 : 다운로드)
			
			
			// 다운로드 진행(입력 스트림으로 읽은 내용을 출력 스트림으로 보냄 : 파일 복사)
			byte[] b = new byte[1024];               // 1KB 저장소, 1KB씩 옮기는 것이다
			int readByte = 0;                        // 실제로 읽은 바이트 수
			while( (readByte = bis.read(b)) != -1) { 
				bos.write(b, 0, readByte);           // b 배열의 0부터 readByte만큼만 보내기.         
			}										 // 1025Byte였다면 첫 전송에는 1024Byte를 보내고 다음 전송 때 1Byte를 보내게 되는데
			if(bos != null) bos.close();             // 그 두 번째 전송 때 1Byte를 제외한 1023Byte는 1회차 때 보냈던 데이터라 readByte만큼만 보내야 한다.
			if(bis != null) bis.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // execute()의 실행 결과가 null이면 페이지 이동이 없다.
	}
	
}
