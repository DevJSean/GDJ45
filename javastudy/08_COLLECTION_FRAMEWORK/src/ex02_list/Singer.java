package ex02_list;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Singer {

	//field
	private String name;
	private List<Song> songs; 
//	private int idx; 리스트에서도 인덱스 쓰이는데 이 예제에서는 안쓰임.
	private Scanner sc;
	
	//constructor
	public Singer(String name) {
		this.name = name;
		this.songs = new ArrayList<Song>(); //Song 생략 가능
		sc = new Scanner(System.in);
	}
	
	
	//method
	
	//노래 추가
	public void addSong() {
		System.out.println("***노래추가***");
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		System.out.print("장르 >>> ");
		String genre = sc.nextLine();
		songs.add(new Song(title, genre)); //리스트의 요소 추가 방법
	}
	
	
	//노래 삭제 equals 메소드를 따로 생성하여
	public void removeSongByObject() {
		System.out.println("***노래삭제***");
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		System.out.print("장르 >>> ");
		String genre = sc.nextLine();
		songs.remove(new Song(title, genre));
		// 삭제할 내용을 전달해서 제거하는 건 사전 설정 없이는 불가능하다.
		// 노래는 제목과 장르가 같아야 같은 노래라는 것을 알려주는 메소드(Song 클래스에 equals메소드를 오버라이드)를 새로 만들어야 한다.
	}
	
	//노래 삭제 인덱스 이용
	public void removeSongByIndex() {
		System.out.println("***노래삭제***");
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		System.out.print("장르 >>> ");
		String genre = sc.nextLine();
		for(int i = 0, size = songs.size(); i < size; i++) {
			Song song = songs.get(i);
			if(title.equals(song.getTitle()) && genre.equals(song.getGenre())) {
				songs.remove(i);
				return;
			}
		}
	}
	
	//노래 수정
	public void modifySong() throws RuntimeException{
		System.out.println("***노래수정***");
		System.out.print("노래번호(1~" + songs.size()+ ") >>> ");
		int songNo = sc.nextInt();
		sc.nextLine(); // 엔터 제거 입력 버퍼 제거, 엔터 제거를 하지 않으면 밑에서 입력 받는 제목에서 엔터가 입력받게 된다.
		if(songNo < 1 || songNo > songs.size())
			throw new RuntimeException("존재하지 않는 노래번호입니다.");
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		System.out.print("장르 >>> ");
		String genre = sc.nextLine();
		songs.set( songNo - 1 , new Song(title, genre));
		
		
	}
	
	
	//노래 조회
	public void findSong() throws RuntimeException{
		System.out.println("***노래조회***");
		if(songs.isEmpty()) //리스트가 비어있다면
			throw new RuntimeException("노래 목록이 존재하지 않습니다.");
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		for(Song song : songs) {
			if(title.equals(song.getTitle())) {
				System.out.println("조회결과 " + song);
				return;
			}
		}
		System.out.println("제목이 " + title + "인 노래가 없습니다."); //for문이 끝나서 나오게 된다면 일치한 노래가 없다는 뜻이다.
	}
	
	
	//전체 조회
	public void findAllSongs()throws RuntimeException{
		System.out.println("***전체노래조회***");
		System.out.println("가수 " + name);
		if(songs.isEmpty()) //리스트가 비어있다면
			throw new RuntimeException("노래 목록이 존재하지 않습니다.");
		for(Song song : songs)
			System.out.println(song);
	}

	public void manage() {
		while(true) {
			try {
				System.out.print("1.추가 2.삭제 3.수정 4.조회 5.전체 0.종료 >>> ");
				int choice = sc.nextInt();
				sc.nextLine();
				switch(choice) {
				case 1: addSong(); break;
				case 2: removeSongByIndex(); break; // 또는 removeSongByObject
				case 3: modifySong(); break;
				case 4: findSong(); break;
				case 5: findAllSongs(); break;
				case 0: System.out.println("프로그램 종료"); return;
				default: throw new RuntimeException("존재하지 않는 메뉴입니다");
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("선택 값은 정수입니다.");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}
