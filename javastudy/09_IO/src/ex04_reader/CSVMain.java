package ex04_reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVMain {

	public static void main(String[] args) {

		// String line 에 있음       날짜, 확진자, 사망자           해결 -> BufferedReader : readLine()
		// String[] tokens 에 있음   {"날짜", "확진자", "사망자"}   해결 -> String : split(",")
		// List<String> list 에 있음 ["날짜", "확진자", "사망자"]   해결 -> Arrays : asList()
		
		
		// ex02_writer의 CSVMain 반대 과정임.
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\storage\\코로나.csv"));
			
			String line = null;
			List<List<String>> body = new ArrayList<List<String>>(); //리스트 안에 리스트 [ [], [] ] 모양
			while((line = br.readLine()) != null) {
				String[] tokens = line.split(",");   // 한 줄에 배열을 나누는 것을 토큰이라 한다.
				List<String> list = Arrays.asList(tokens);
				body.add(list);
			}
			System.out.println(body);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
