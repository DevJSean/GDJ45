package ex03_set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {
	
	
	public static void m1() {

		// hash 세트 생성
		Set<String> set = new HashSet<String>(); //java.util.Set, java.util.HashSet import 필요
		
		
		//추가
		set.add("JAVA");
		set.add("DB");
		set.add("WEB");
		set.add("SPRING");
		set.add("JAVA"); //중복 저장 시도
		
		//확인
		System.out.println(set); // 저장한 순서대로 나오지 않는다.
		
		//삭제 
		//인덱스가 없기 때문에 인덱스를 이용할 수 없다.
		boolean res = set.remove("JAVA");
		System.out.println(res ? "삭제되었다." : "삭제되지 않았다.");
		
		//수정은 없다.
		
		//크기
		int size = set.size();
		System.out.println("세트 길이 =" + size);
		
		// 일반 for문 순회
		// 불가능하다. 인덱스가 없기 때문이다.
		
		// 향상 for문 순회
		for(String str : set)
			System.out.println(str);
		
	}

	public static void m2() {
		// 리스트 -> 세트 변환   (중복된 값들이 사라질 것)
		// 리스트
		List<String> list = new ArrayList<String>();
		list.add("국어");
		list.add("영어");
		list.add("수학");
		list.add("국어");
		list.add("영어");
		list.add("수학");  //리스트는 중복 가능
		System.out.println(list);
		
		// 세트
		Set<String> set = new HashSet<String>(list); // 리스트를 세트로 변환
		System.out.println(set); // 순서와 중복이 사라짐
	}
	
	public static void m3() {
		
		// 반복자(Iterator) 활용하기
		// hasNext(): 존재유무 찾기, next() : 확인된 데이터 꺼내기 
		
		// 세트
		Set<String> set = new HashSet<String>(Arrays.asList("국어", "영어", "수학"));
		
		// 반복자 생성
		// 세트(Collection)에 부착
		Iterator<String> itr = set.iterator(); // 타입 이름 Iterator, 메소드 이름 iterator
		
		// 반복자 사용
		// hasNext() : 존재하는 데이터 유무 반환
		// next() : hasNext()로 파악한 데이터 자체 반환
		while(itr.hasNext()) { //잡히는 게 있으면
			String element = itr.next(); //그것을 빼서 element에 저장하고
			System.out.println(element); //출력
		}
	}
	
	public static void m4() {
		// 로또 번호 6개 랜덤 생성하기
		// 중복된 번호 생성 방지
		Set<Integer> lotto = new HashSet<Integer>();
		
		while(lotto.size() != 6) // 반복할 때마다 개수를 확인하는 방식으로 해야함
			lotto.add((int)(Math.random() * 45 ) + 1);  //set는 중복을 검사하지 않아도 됨. 중복이 방지된다.
		System.out.println(lotto);
	}
	
	public static void main(String[] args) {

		m4();
		
	}

}
