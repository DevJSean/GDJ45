package mybatis;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class ProductDTO {

	private Long productNo; // mybatis-config에서 setting을 해줘서 SNAKE_CASE로 변환해준다.
	private String name;
	private Integer price;
	private String image;
	
	
	
	
}
