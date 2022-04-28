package jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductRemoveService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터 product_no
		
		Long product_no = 0L;
		String strNo = request.getParameter("product_no");
		if(strNo == null || strNo.isEmpty()) { // a링크, onclick="location.href=..." 에서 정상적으로 파라미터 값이 넘어오지 않았을 때 null 값 가능.
			product_no = 0L;	 	    	   // form으로 보내는 경우 value=""를 넣지 않았을 때 빈 문자열이 넘어온다.
			// product_no는 시퀀스, 1부터 시작하기 때문에 0인 제품 번호는 존재하지 않는다.
			// 그래서 0으로 설정하면 아무 것도 지워지지 않는다.
			// 잘못된 데이터가 왔다고 DB에 null 값을 넘기면 안된다.
		} else {
			product_no = Long.parseLong(strNo);
		}
		
		int res = ProductDAO.getInstance().deleteProduct(product_no);
		
		// 삭제 성공 메세지와 함께 이동하는 방법
		try {
			if(res > 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('제품이 삭제되었습니다.')");
				out.println("location.href='/JUNIT/list.do'"); // location.href='' 는 redirect임
				out.println("</script>");
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
		// 삭제 성공 메시지 없이 이동하는 방법
		/*
		ActionForward af = null;
		if(res > 0) {
			af = new ActionForward("/JUNIT/list.do", true); // 삭제는 redirect
		}
		return af;
		*/
		
		
	}

}
