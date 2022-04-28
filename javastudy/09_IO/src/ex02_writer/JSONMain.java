package ex02_writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

// JSON 파일
// 1. JavaScript Object Notation
// 2. 자바스크립트 객체 표현법


// JSON 이라는 텍스트 포맷이다.
// 다양한 언어에서 사용됨.

// CSV와 XML과 다르게 JSON은 자바 자체 기능으로 해결하기 어렵다.
// JSON은 외부 라이브러리의 도움을 받아야 한다.
// https://www.json.org/json-en.html 맨 밑에 가면 라이브러리들이 있다.
// 많이 사용되는 외부 라이브러리들
// JSON-java  -----이거 받아서 jar폴더에 넣었음.
// json-simple
// google-gson
// jackson

// 원하는 라이브러리를 build path - external jar file 해서 적용한다.

// JSON in JAVA 라이브러리       자바로 작업할 때는...
// 1. JSONObject 클래스 : { }  -> Map으로 작업
// 2. JSONArray  클래스 : [ ]  -> ArrayList으로 작업 
// JSONObject는 Map기반으로 돌아간다. put, set...


// 형식
/*
[
	{
		"date": "2022-02-14",
		"infection": 500,
		"dead": 0
	},
	{
		"date": "2022-02-15",
		"infection": 600,
		"dead": 1
	},
	{
		"date": "2022-02-16",
		"infection": 700,
		"dead": 2
	}
	
]
*/

public class JSONMain {

	public static void m1() {
		//JSONObject 클래스
		JSONObject data = new JSONObject();
		data.put("date", "2022-02-16");
		data.put("infection", 500);
		data.put("dead", 0);
		System.out.println(data);
		
		//JSONArray 클래스
		JSONArray array = new JSONArray();
		array.put(data);
		System.out.println(array);
		
		// 위의 형식 만들어보기
		List<Object> content1 = Arrays.asList("2022-02-14","500","0");
		List<Object> content2 = Arrays.asList("2022-02-15","600","1");
		List<Object> content3 = Arrays.asList("2022-02-16","700","2");
		List<List<Object>> list = Arrays.asList(content1, content2, content3);
		
		JSONArray array2 = new JSONArray();
		for(List<Object> data2 : list) {
			JSONObject obj = new JSONObject();
			obj.put("date", data2.get(0));
			obj.put("infection", data2.get(1));   // 리스트에서 요소 가져올 때 get()
			obj.put("dead", data2.get(2));
			array2.put(obj);
		}
		System.out.println(array2);
		System.out.println();

		
		//코로나.json 만들기
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\storage\\코로나.json"))) {	
			bw.write(array2.toString());
		} catch(IOException e) { 
			e.printStackTrace();
		}
	}
	
	public static void m2() {
		/*
		 	{
		 		"name": "민경태", 
		 		"age": 45,							 이런 모양새이다.
		 		"info": {                     		 JSONObject obj
		 			"hobbies": [       		         obj.put("name", "민경태");
		 				"넷플릭스", 				 obj.put("age", 45);
		 				"디즈니플러스",				 obj.put("info", new JSONObject()); 
		 				"티빙"						 obj.put("friends", new JSONArray());
		 			],
		 			"address": "서울시",
		 			"phone": "010-1111-1111"
		 		},
		 		"friends": [ <-리스트
		 			{         <- 맵이 두 개 들어감.
		 				"name": "철수",
		 				"contact": "010-2222-2222"
		 			},
		 			{
		 				"name": "영희",
		 				"contact": "010-3333-3333"
		 			}
		 		]
		 	}
		 		
		 */
		/*
		JSONObject data = new JSONObject();
		JSONObject info = new JSONObject();
		JSONArray hobbies = new JSONArray();
		JSONArray friends = new JSONArray();
		JSONObject friendsInfo1 = new JSONObject();
		JSONObject friendsInfo2 = new JSONObject();
		
		friendsInfo1.put("name", "철수");
		friendsInfo1.put("contact", "010-2222-2222");
		
		friendsInfo2.put("name", "영희");
		friendsInfo2.put("contact", "010-3333-3333");

		friends.put(friendsInfo1);
		friends.put(friendsInfo2);
		
		hobbies.put("넷플릭스");
		hobbies.put("디즈니플러스");
		hobbies.put("티빙");
		
		info.put("hobbies", hobbies);
		info.put("address", "서울시");
		info.put("phone", "010-1111-1111");
		
		data.put("name", "민경태");
		data.put("age", 45);
		data.put("info", info);
		data.put("friends", friends);
		
		System.out.println(data);
		*/
		
		// 맵, 리스트 방식
		Map<String, Object> person = new HashMap<String, Object>();
		
		person.put("name", "민경태");  //person
		person.put("age", 45);         //person
		
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("hobbies", Arrays.asList("넷플릭스", "디즈니플러스", "티빙")); // 리스트로 취미 세가지 넣기
		info.put("address", "서울시");
		info.put("phone", "010-1111-1111");
		person.put("info", info);      //person
		
		List<Map<String, String>> friends = new ArrayList<Map<String,String>>();
		Map<String, String> friend1 = new HashMap<String, String>();
		friend1.put("name", "철수");
		friend1.put("contact", "010-2222-2222");
		Map<String, String> friend2 = new HashMap<String, String>();
		friend2.put("name", "영희");
		friend2.put("contact", "010-3333-3333");
		friends.add(friend1);   // 리스트에 저장할 땐 add()
		friends.add(friend2);
		person.put("friends", friends);	  //person

		JSONObject obj = new JSONObject(person);  // Map을 JSONObject로 변환.
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\storage\\person.json"))) {	
			//bw.write(obj.toString());
			obj.write(bw, 4, 0); //예시처럼 줄바꿈하기.     JSON-java의 기능임   person.json파일 확인
		} catch(IOException e) { 
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {

		//m1();
		m2();
	}
}
