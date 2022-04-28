package ex02_list;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
/* 같은 역할을 한다.
public Song(String title, String genre) {
	super();
	this.title = title;
	this.genre = genre;
}*/
@Getter   //getter와 setter
@Setter

@ToString //toString


public class Song {
	
	//field
	private String title;
	private String genre;
	
	
	//method
	
	// removeSongByObject 메소드에서 사용 
	@Override
	public boolean equals(Object obj) {  // Object obj로 new Song();이 전달됨.
		Song song = (Song)obj; // Object 타입으로 저장된 new Song();을 바꿔줌.
		return title.equals(song.title) && genre.equals(song.genre);
	}


}
