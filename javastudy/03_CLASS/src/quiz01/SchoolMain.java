package quiz01;

public class SchoolMain {

	public static void main(String[] args) {

		School school1 = new School("철산초등학교", "철산동");
		System.out.println(school1.getName());     // getName을 보면 name이라는 필드가 있다는 것을 판단할 수 있다.
		System.out.println(school1.getLocation()); // 위치 나오게
		
		School school2 = new School();
		school2.setName("소하초등학교"); // setName을 보고 name이라는 필드가 있다는 것을 알 수 있다.
		school2.setLocation("소하동");
		System.out.println(school2.getName()); // 소하초등학교
		System.out.println(school2.getLocation()); //소하동
		

	}

}
