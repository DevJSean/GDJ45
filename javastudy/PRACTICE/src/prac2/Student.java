package prac2;

public class Student {

	//field
	private String name; 
	private Exam[] exams;
	private int idx; // 초기화로 0 가짐. doExam() 메소드를 위해서 필요.
	
	// 앞부분은 위에
	//Exam[] exams = new Exam[2];
	//         뒷부분은 아래에 나눠서 적은 것.
	
	//constructor
	public Student(String name) {
		this.name = name;    // 매개변수에 동일한 변수가 있을 때 this
		exams = new Exam[2]; // 매개변수가 없을 때는 안쓴다.
	}                        // 배열의 길이 2
	
	//method
	public void doExam(Exam ex) { // Ex03을 보면 Exam 타입의 매개변수 필요
		if(idx == exams.length)
			return;  // idx가 exams.lengh와 길이가 같아지면 취소,종료
		exams[idx++] = ex;
	}
	
	public double getAverage() {  // 최종 평균
		// 각 Exam 인스턴스 : exams[0], exams[1]  
		// 각 Exam의 평균   : exams[0].getAverage(), exams[1].getAverage()
		double total = 0;
		for(int i = 0; i < idx; i++) //exams.length는 배열의 길이(최대횟수). 실제로 시험을 본 횟수는 idx값임.
			total+= exams[i].getAverage();
		return total / idx;
	}
	
	public String getGrade() {  // 최종 학점평균
		double average = getAverage();
		if(average >= 90)
			return "A";
		else if(average >= 80)
			return "B";
		else if(average >= 70)
			return "C";
		else if(average >=60)
			return "D";
		else
			return "F";
	}
	
	public void info() {
		System.out.println("학생명 " + name);
		// 각 Exam 인스턴스 : exams[0], exams[1]  
		// 각 Exam의 정보   : exams[0].info(), exams[1].info()
		for(int i = 0; i < idx; i++) {
			System.out.println("===" + (i + 1) + "번째 시험 정보===");
			exams[i].info();
		}
		System.out.println("최종평균 " + getAverage() + "점(" + getGrade() + "학점)");
	}
	
	
}
