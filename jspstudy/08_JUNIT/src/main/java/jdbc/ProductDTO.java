package jdbc;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder // Builder 패턴을 이용해서 productDTO 생성
@Getter
@Setter

public class ProductDTO {

	private Long product_no; // mybatis에서만 자동 변환이 되기 때문에 여기선 변환 안됨. DB와 맞춰서 snake_case로 만듦.
	private String name;
	private Integer price;
	private String image;
	
	
	
	
}
