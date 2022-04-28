package ex02_writer;

import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLMain {

	public static void main(String[] args) {

		// XML 파일   안외워도 됨
		// 1. extensible Markup Language
		// 2. 사용자가 직접 태그를 생성하는 파일
		// 3. HTML 형식의 보완 형식
		
		// 태그로 구성된 문서 형식 DOM 요소.
		// Document Object Model : DOM 
		
		/* 이러한 형식인데 불필요한 태그가 많아 최근에는 잘 사용하지 않는다.
		<data>
			<date>>2022-02-14</date>
			<infection>500</infection>
			<dead>0</dead>
		</data>
		
		<data>
		<date>>2022-02-15</date>
		<infection>600</infection>
		<dead>1</dead>
		</data>
		*/
		
		try {
			
			// XML 문서 생성
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document document =  builder.newDocument();
			document.setXmlStandalone(true);
		
			
			// 태그 생성
			
			Element data = document.createElement("data");
			
			Element date = document.createElement("date");
			date.setTextContent("2022-02-14");
			
			Element infection = document.createElement("infection");
			infection.setTextContent("500");
			
			Element dead = document.createElement("dead");
			dead.setTextContent("0");
			
		
			// 태그 배치
			
			document.appendChild(data);
			data.appendChild(date);
			data.appendChild(infection);
			data.appendChild(dead);
			
			
			// XML 파일 만들기
			
			TransformerFactory transFactory = TransformerFactory.newInstance();
			
			Transformer transformer = transFactory.newTransformer();
			
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // 한글
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // 들여쓰기
			
			
			Source source = new DOMSource(document);
			StreamResult result = new StreamResult(new FileWriter("C:\\storage\\코로나.xml"));
			
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
