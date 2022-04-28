package phone;

public class Phone {

	//field
	private String name;
	private String tel;
	
	//constructor
	public Phone() {     // 디폴트 생성자 ctrl + space bar
		
	}
	public Phone(String name, String tel) {  // source 메뉴
		super();
		this.name = name;
		this.tel = tel;
	}
	
	// getter setter     source 메뉴    private 필드에 넣어주는 게 setter, 밖으로 반환해주는 게 getter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
