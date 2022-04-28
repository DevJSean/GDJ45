package prac2;

// 평균 계산

// 학점 : A, B, C, D, F 중 하나

public class Exam {

	//field
	private int kor;
	private int eng;
	private int math;
	
	//constructor
	// 디폴트 형태 생성자
	public Exam() {
		kor = (int)(Math.random() * 101);  //0에서 100의 값,  원래 scanner 해야되는데 그냥 보여주기용 난수 처리
		eng = (int)(Math.random() * 101);  //0에서 100의 값
		math = (int)(Math.random() * 101);  //0에서 100의 값
	
	}
	
	// method
	public double getAverage() {
		return (kor + eng + math) / 3.0;
	}
	
	public String getGrade() {
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
		System.out.println("국어 " + kor + "점, 영어 " + eng + "점, 수학 " + math + "점");
		System.out.println("평균 " + getAverage() + "점(" + getGrade() + "학점)");
	}
	
	
}
