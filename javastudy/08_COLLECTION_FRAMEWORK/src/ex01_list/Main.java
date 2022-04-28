package ex01_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void m1() {
		// ArrayList arr = new ArrayList();
		// 이렇게 하는 것 보단 밑 방법(upcasting)을 주로 쓴다
		
		// 리스트 생성
	//	List<String> list = new ArrayList<>(); 뒷 꺽쇠 부분은 생략 가능하다.	
		List<String> list = new ArrayList<String>();
	//	super                     sub

		// List는 import 해야한다. java.util.List
		// 꺽쇠 < > 를 이용하는 방법을 제네릭이라고 한다. (타입을 정하는 방법)
		// ArrayList도 import 해야한다. java.util.ArrayList	
		
		
		// 요소 추가 add()
		list.add("화");   // 리스트의 마지막 요소로 추가
		list.add("수");   // 리스트의 마지막 요소로 추가
		list.add(0,"월"); // 인덱스 0의 요소로 추가하라.
		
		
		// 리스트 확인
		System.out.println(list); 
		
		
		// 리스트가 배열과 다른 점
		// String [] list = new String[10]; 배열을 최소 생성 시 길이를 정하고 만든다. 그리고 중간에 길이를 늘리는 게 불가능하다.(복사해야 함)
		// List<String> list = new ArrayList<String>(); 리스트는 길이를 정하지 않는다. 중간에 길이를 늘려야 할 때 알아서 늘린다.
		// List<object> list = new ArrayList<>(); 모든 데이터 타입을 저장하겠다는 의미.
		
		
		// 요소 제거
		// 인덱스에 의한 제거
		String element = list.remove(0);    // .remove()는 삭제한 인덱스에 해당하는 값을 보여줌.
		System.out.println("삭제한 요소 " + element);
		// 값을 지정(여러 개가 들어 있다면 첫 번째 값이 지워짐)
		boolean res = list.remove("화");    // 지워졌는지의 여부가 전달됨.
		System.out.println(res ? "삭제되었다." : "삭제되지 않았다.");
		
	}
	
	public static void m2() {
		
		// 리스트 초기화 (Arrays.asList()를 이용함)
		List<String> list = Arrays.asList("월", "화", "수", "금");
		// asList(T... a) : 말줄임표는 해당 타입의 데이터가 몇 개가 오든 상관 없이 다 받아주겠다는 의미.
	
		// 리스트 길이
		int size = list.size(); //size() 리스트 객체 내에 포함된 원소의 개수
		
		// 개별 요소 get()
		System.out.println(list.get(0));  // get() : 인덱스를 넣으면 해당 인덱스에 해당되는 값이 나옴
		System.out.println(list.get(1));  // 배열에서는 list[1]인 것이다.
		System.out.println(list.get(2));  
		System.out.println(list.get(size - 1)); //리스트의 마지막 요소는 (리스트의 길이 - 1)이다.
		
		
		// 수정 set()
		list.set(size - 1 , "목");
		
		// 확인
		System.out.println(list);
	
	}
	
	public static void m3() {
		
		// 리스트를 for문으로 순회하기
		
		List<String> list = new ArrayList<String>();
		
		list.add("월");
		list.add("화");
		list.add("수"); // 위에서 Arrays.asList()를 쓰는 것보다 이게 더 일반적이다.
		
		// 일반 for문
		for(int i = 0, size = list.size(); i < size; i++)  //list.size()가 조건식에 사용되어 성능에 문제가 생기지 않도록 초기식에 선언한다.
			System.out.println(list.get(i));
		
		// 향상 for문
		for(String element : list)
			System.out.println(element);
		
		
	}
	
	public static void m4() {
		
		// 제네릭(generic) 타입은 오직 참조타입만 가능하다. (첫글자가 대문자인) 클래스만 가능하다
		// 기본(primitive)타입의 wrapper(기본 타입을 참조 타입으로 정의해 놓은 것)
		// booolean              Boolean
		// int					 Integer
		// long					 Long
		// double				 Double
	
		List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
		System.out.println(list);
		
	
	}
	
	
	
	public static void main(String[] args) {
		
		m1();
		//m2();
		//m3();
		//m4();

	}

}
