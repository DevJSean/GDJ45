package ex20_interface;

// 내용이 없는 interface 활용하기 (타입으로만 사용하는 경우)
// 관례로 이러한 경우에 interface의 이름을 ~able이라는 이름으로 많이 사용한다

public class Main {

	public static void main(String[] args) {

		Dog dog = new Dog("백구");
		Cat cat = new Cat("냥이");
		Snake snake = new Snake("낼름이");
		
		Person person = new Person();
		person.feed(dog, "강아지먹이"); // 백구에게 강아지먹이를 준다.
		person.feed(cat, "고양이먹이"); // 냥이에게 고양이먹이를 준다.
		person.feed(snake, "뱀먹이");	// 낼름이에게 뱀먹이를 준다.
		
		person.walk(dog); // 백구와 산책하기
		person.walk(cat); // 냥이와 산책하기
		// person.walk(snake); // 실행을 막으시오! (실행하려고 하면 오류가 뜸)
		
	}

}
