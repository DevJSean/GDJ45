package domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Emp {

	private Long empNo;  // sesquence
	private String name;
	private String dept;
	private Date hired;  // Oracle의 sysdate (오늘)
}
