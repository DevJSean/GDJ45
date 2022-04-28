package ex2_method;

public class VendingMachineMain {

	public static void main(String[] args) {


		VendingMachine vm = new VendingMachine();  //자판기의 이름은 vm
		//클래스명                메소드명
		
		
		// 메소드 호출(call)
		// 인수 : 메소드에 전달하는 값 (500, 1)
		String coffee1 = vm.getCoffee(500, 1);
		String coffee2 = vm.getCoffee(1000, 2);
		
		System.out.println(coffee1);
		System.out.println(coffee2);
		
	}

}
