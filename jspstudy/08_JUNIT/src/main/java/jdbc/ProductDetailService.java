package jdbc;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDetailService implements ProductService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// null 방지
		Optional<String> opt = Optional.ofNullable(request.getParameter("product_no"));
		Long product_no = Long.parseLong(opt.orElse("0")); // null값이면 0으로 바꿔서 조회되지 않도록
		
		request.setAttribute("product", ProductDAO.getInstance().selectProductByNo(product_no));
		
		request.setAttribute("contextPath", request.getContextPath()); // contextPath 값 속성에 저장 -> detail.jsp에서 el 값으로 사용하기 위함
		
		return new ActionForward("product/detail.jsp", false);
	}

}
