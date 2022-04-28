package dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Book {

	//field
	private int no;
	private String title;
	private String link;
	private String image;
	private String author;
	private int price;
	private int discount;
	private String publisher;
	private String pubdate;
	private String isbn;
	private String description;
}
