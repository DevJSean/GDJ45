package prac1;

// 다음 main 메소드를 참고하여 BankAccount 클래스를 구현하시오.

public class Ex09 {

	public static void main(String[] args) {
		
		BankAccount me  = new BankAccount("1234", 10000L);        // 계좌를 두개 만드는 행위
		BankAccount mom = new BankAccount("4321", 100000L);
		
		me.deposit(10000);  // 내 계좌에 10000원 입금
		me.deposit(-100);   // 내 계좌에 마이너스 입금 (불가)
		
		me.withdraw(5000);  // 내 계좌에서 5000원 출금
		me.withdraw(90000);   // 내 계좌에서 잔액보다 큰 금액 출금 (불가)
		
		me.inquiry();  // 계좌번호: 1234, 잔액: 15000원
		
		// 클래스 내부에서는 나 자신을 this라고 부르고 other는 me
		// mom이 this, me가 other
		mom.transfer(me, 50000);  // 엄마가 나에게 50000원 이체
		mom.transfer(me, -100);  // 실패
		mom.transfer(me, 100000000);  // 실패
		
		mom.inquiry();  // 계좌번호: 4321, 잔액: 50000원
		me.inquiry();  // 계좌번호: 1234, 잔액: 64900원

	}

}
