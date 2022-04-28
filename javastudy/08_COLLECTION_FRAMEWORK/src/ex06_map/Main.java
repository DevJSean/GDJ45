package ex06_map;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		// Student 인스턴스를 key로 사용하고,
		// Student의 점수를 value로 사용하는 Map
		
		
		Map<Student, Integer> scores = new HashMap<Student, Integer>();
		scores.put(new Student("철수", "서울대학교"), 90); 
		scores.put(new Student("영희", "고려대학교"), 95); 
		scores.put(new Student("영수", "연세대학교"), 100); 

		// "철수"의 점수를 80점으로 수정하기
		scores.put(new Student("철수", "서울대학교"), 80); // 근데 이렇게 하면 철수 90점, 철수 80점이 둘 다 저장된다.
														   // 이전의 철수와 지금 만든 철수는 내용과 관련 없이 주소 때문에 다른 키라고 판단한다.
														   // 다르다고 판단한 기준은 HashMap이다.
														   // Object 클래스의 자식인 Student클래스에 hashCode() equals()를 오버라이드 해야 제대로 수정된다.
		
		//Student 인스턴스의 동등 비교를 위해서
		//Student 클래스에 hashCode(), equals() 메소드의 오버라이드가 필요하다.
		
		System.out.println(scores);
		
		// scores 순회
		// 이름 철수, 학교 서울대학교, 점수 80점
		// 이름 영희, 학교 고려대학교, 점수 95점
		// 이름 영수, 학교 연세대학교, 점수 100점
		
		for(Map.Entry<Student, Integer> entry : scores.entrySet()) {
			Student key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println("이름 " + key.getName() + ", 학교 " + key.getSchool() + ", 점수 " + value + "점");
		}
		
	}

}
