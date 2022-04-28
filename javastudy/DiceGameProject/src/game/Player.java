package game;

import java.util.Arrays;

public class Player {

	// field
	private String name;

	// constructor
	public Player(String name) {
		this.name = name;
	}

	// method
	
	// getter setter {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	// }
	public boolean turn() {
		int[] arr = new int[3]; 
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 6) + 1; // +1은 괄호 안이나 밖이나 상관 없음
		}
		System.out.println("===" + name + "님의 주사위 결과===");
		System.out.print(Arrays.toString(arr)); // Arrays import 필요. 배열을 출력하려고 할 때 많이 사용
		boolean res = true;
		for(int i = 1; i < arr.length; i++) {
			if(arr[0] != arr[i]) {               // 0번 인덱스의 값과 각 인덱스의 값을 비교 
				res = false;
				break;
			}
		}
		if(res) {
			System.out.println(" 성공!");
			return res;
		} else {
			System.out.println(" 실패!");
			return res;
		}
	}
	
	
	/*  내가 푼 방법
	public boolean turn() {
		System.out.println("===" + name + "님의 주사위 결과===");
		int[] arr = new int[3]; 
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 6) + 1; // +1은 괄호 안이나 밖이나 상관 없음
			System.out.print(arr[i] + " ");
		}
		if(arr[0] == arr[1] && arr[1] == arr[2]) {
			System.out.println("성공!");
			return true;
		}
		else {
			System.out.println("실패!");
			return false;
		}
	}
	*/

	
	
}
