package jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.do")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductController() {
        super();
    }

    
    // 메소드가 훨신 더 많을 때 사용하는 방법. 이외에도 많은 경우가 있는데, 일단은 doGet에서 하는 것으로 한다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		
		ProductService service = null;
		
		ActionForward af = null;
		
		
		switch(command) {
		// 단순 이동
		case "addPage.do":
			af = new ActionForward("product/add.jsp", false);
			break;
			
		// DB 다녀오기
		case "list.do":
			service = new ProductListService();
			break;
		case "add.do":
			service = new ProductAddService();
			break;
		case "detail.do":
			service = new ProductDetailService();
			break;
		case "download.do":
			service = new ProductDownloadService();
			break;
		case "remove.do":
			service = new ProductRemoveService();
			break;
		}
		
		if(service != null) {
			af = service.execute(request, response);
		}
		
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
	}
}
