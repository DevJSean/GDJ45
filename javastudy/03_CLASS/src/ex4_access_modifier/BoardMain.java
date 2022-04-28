package ex4_access_modifier;

public class BoardMain {

	public static void main(String[] args) {


		Board board1 = new Board();
		
		//메소드를 통해 데이터를 저장하는 방법
		board1.setBoardNo(1);
		board1.setTitle("필독");
		
		System.out.println("글번호: " + board1.getBoardNo());
		System.out.println("글제목: " + board1.getTitle());
		
		board1.info(); //info 메소드 호출
		
		
		
		// 그냥 이 아랫줄 방법으로 하면 됨.
		Board board2 = new Board(2, "공지사항");
		board2.info();
		
	}

}
