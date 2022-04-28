package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// lombok 처리
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString // ProductService 클래스의 findProduct 메소드에서 사용

public class Product {

	// field
	private long no;
	private String name;
	private int price;
	
	
	
}
