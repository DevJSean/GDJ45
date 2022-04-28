package ex06_map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter

public class Student {

	//field
	private String name;
	private String school;

	
	//이클립스가 자동으로 생성하는 hashCode(), equals() 메소드     
	//source메뉴의 Generate hashCode() and equals()
	//or
	//lombok을 사용하면 됨.
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		return result;	
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		return true;
	}
	*/
	
}
