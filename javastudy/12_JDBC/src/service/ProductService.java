package service;

import java.util.List;
import java.util.Scanner;

import dao.ProductDAO;
import dto.Product;


// ProductService에서 입력을 받으면
// ProductDAO로 데이터를 전달하고
// DAO에서 DB로 전달하는 흐름.

public class ProductService {

	// field
	private Scanner sc;
	private ProductDAO dao;
	
	// 생성자
	public ProductService() {
		sc = new Scanner(System.in);
		dao = ProductDAO.getInstance();
	}
	
	// 제품 등록
	public void addProduct() {
		System.out.println("=== 제품등록 ===");
		System.out.print("제품명 >>> ");
		String name = sc.next();
		System.out.print("제품가격 >>> ");
		int price = sc.nextInt();
		
		int res = dao.insertProduct(name, price); //반환 값으로 정수 값, 성공시 1 실패시 0
		if(res > 0)
			System.out.println("=== " + name + " 등록 성공===");
		else
			System.out.println("=== " + name + " 등록 실패===");
	}
	
	// 제품 삭제
	public void removeProduct() {
		System.out.println("===제품삭제===");
		System.out.print("제품번호 >>> ");
		long no = sc.nextLong();
		
		// 입력받은 no 값으로 제품의 이름을 알아낸다.
		// dto Product를 이용
		Product product = dao.selectProductByNo(no);
		if(product == null) {
			System.out.println("해당 제품이 없습니다.");
			return;
		}
		String name = product.getName();
		System.out.print(name + " 제품을 삭제할까요(y/n)? ");
		
		// y/n을 String으로 처리
		String yn = sc.next();
		if(yn.equalsIgnoreCase("y")) { // 대소문자 둘 다 처리
			int res = dao.deleteProduct(no);
			if(res > 0)
				System.out.println("=== " + name + " 삭제 완료===");
			else
				System.out.println("=== " + name + " 삭제 실패===");
		} else {
			System.out.println(name + " 제품 삭제가 취소되었습니다.");
		}
		
		/* y/n을 char으로 처리
		char yn = sc.next().charAt(0); // char입력 방법은 없고 String으로 받아서 charAt으로 변환해야 한다.
		if(yn == 'y' || yn == 'Y') { // 대소문자 둘 다 처리
			int res = dao.deleteProduct(no);
			if(res > 0)
				System.out.println("=== " + name + " 삭제 완료===");
			else
				System.out.println("=== " + name + " 삭제 실패===");
		} else {
			System.out.println(name + " 제품 삭제가 취소되었습니다.");
		} */
	}
	
	// 제품 수정
	public void modifyProduct() {
		System.out.println("=== 제품 수정 ===");
		System.out.print("제품번호 >>> ");
		long no = sc.nextLong();
		
		Product product = dao.selectProductByNo(no);
		if(product == null) {
			System.out.println("해당 제품이 없습니다.");
			return;
		}
		System.out.println("현재 제품번호 " + no + "번 제품은 " + product.getName() + ", 가격은 " + product.getPrice() + "원 입니다.");
		
		System.out.print("수정할 제품명 >>> ");
		String name = sc.next();
		System.out.print("수정할 제품가격 >>> ");
		int price = sc.nextInt();
		
		int res = dao.updateProduct(no, name, price); //반환 값으로 정수 값, 성공시 1 실패시 0
		if(res > 0)
			System.out.println("=== " + name + "(으)로 수정 성공 ===");
		else
			System.out.println("=== 수정 실패 ===");
		
	}
	
	// 제품 조회
	public void findProduct() {
		System.out.println("=== 제품 조회 ===");
		System.out.print("제품번호 >>> ");
		long no = sc.nextLong();
		// 제품 삭제 때 만들었던 selectProductByNo를 이용
		Product product = dao.selectProductByNo(no);
		if(product == null) 
			System.out.println("제품번호 " + no + "인 제품은 없습니다.");
		else
			System.out.println("조회결과 " + product); // Product 클래스에 toString이 오버라이드 되어있어야 함
	}
	
	// 일반적인 전체 조회
	public void findAllProducts() {
		System.out.println("=== 전체 조회 ===");
		List<Product> products = dao.selectProductList();
	
		// 조회 결과가 없는지 점검
		// if(products.size() == 0)  // products는 ProductDAO의 selectProductList 메소드에서
		if(products.isEmpty()) {     // new ArrayList<Product>()로 만들어놨기 때문에 null이 나올 수가 없다. 따라서 size나 isEmpty 메소드 이용
			System.out.println("저장된 제품이 없습니다.");
			return;
		}
		
		// 결과 집합 확인
		for(Product res : products) {
			System.out.println(res);
		}
	}
	
	// 데이터가 매우 많은 경우, 범위 지정하여 전체 조회
	public void findPartProducts() {
		System.out.println("=== 범위 조회 ===");
		System.out.print("시작 >>> ");
		int begin = sc.nextInt();
		System.out.print("끝 >>> ");
		int end = sc.nextInt();
		
		List<Product> products = dao.selectProductPartList(begin, end);
		if(products.isEmpty()) {
			System.out.println("지정된 범위에 저장된 제품이 없습니다.");
			return;
		}
		for(Product res : products) {
			System.out.println(res);
		}
	}
	
	
	// 실행
	public void execute() {
		while(true) {
			System.out.print("1.추가 2.삭제 3.수정 4.조회 5.전체 6. 범위 0.종료 >>> ");
			int choice = sc.nextInt();
			sc.nextLine(); // 0~6 숫자 입력 후 누른 Enter키 제거.
			switch(choice) {
			case 1: addProduct(); break;
			case 2: removeProduct(); break;
			case 3: modifyProduct(); break;
			case 4: findProduct(); break;
			case 5: findAllProducts(); break;
			case 6: findPartProducts(); break;
			case 0: System.out.println("프로그램을 종료합니다."); return;
			default : System.out.println("선택을 다시 하세요.");
			}
		}
	}
	

}
