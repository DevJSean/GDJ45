package ex12_upcasting;

public class Customer {

	// field
	private int money;
	private int bonusPoint;
	private Product[] cart; // Product 타입의 배열이면 Tv, Computer 모두 저장 가능
	private int idx; // Product 타입 배열의 인덱스 값을 위해서.
	
	
	// constructor
	// Main의 Customer 생성자는 디폴트라서 안만들어도 됨. 근데 Product[] cart의 길이를 지정하기 위해 만든다.
	public Customer() {                // 디폴트 생성자는 Ctrl + space bar로 만들 수 있다.
		cart = new Product[5];
	}
	
	
	// method
	
	// getter setter {  money와 bonusPoint만 필요함
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getBonusPoint() {
		return bonusPoint;
	}
	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}
	// }
	
	public void purchase(Product product) { //Product product = new Tv();가 동작하게 될 것임.
		int price = product.getPrice(); //product.getPrice()가 너무 많이 쓰여서 한번 호출(지역 변수)로 바꾼다.
		if(idx == cart.length) {
			System.out.println("더 이상 구매할 수 없습니다.");
			return;
		}
		if(money < price) {
			System.out.println("돈이 " + (price - money) + "만원 부족합니다.");
			return;
		}
		cart[idx++] = product; // 배열에 product가 들어가게 될 것.
		money -= price;
		bonusPoint += price * 0.05;
	}
	
	public void receipt() {
		if(idx == 0) {
			System.out.println("구매한 제품이 없습니다.");
			return;
		}
		int total = 0; // 총 구매 금액 누적을 위한 합계 변수 선언
		for(int i = 0; i < idx; i++) {
			System.out.println(cart[i].getName() + "\t" + cart[i].getPrice() + "만원");
			total += cart[i].getPrice();
		}
		System.out.println("-----------------------------");
		System.out.println("총 구매 금액 " + total + "만원");
		System.out.println("보너스 포인트 " + bonusPoint + "만원");
		System.out.println("남은금액 " + money);
	}
}
