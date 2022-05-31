package com.goodee.exam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiSearchBook {
	
    public static void main(String[] args) {
    	
    	String clientId = "XvHSzjB4Bj49XBbfspdN";
		String clientSecret = "ARhr4OmF6r";
        
        try {
        	String query = JOptionPane.showInputDialog("책 관련 검색어를 입력하세요.");
        	String text = URLEncoder.encode(query, "UTF-8");
        	String apiURL = "ttps://openapi.naver.com/v1/search/book?query=" + text;
            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("X-Naver-Client-Id", clientId);
            requestHeaders.put("X-Naver-Client-Secret", clientSecret);
            URL url = new URL(apiURL);
        	HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            InputStream in = null;
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            	in = con.getInputStream();
            } else {
            	in = con.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                responseBody.append(line);
            }
            br.close();
            JSONObject obj = new JSONObject(responseBody.toString());
            File dir = new File("C:\\download");
            if(dir.exists() == false) {
            	dir.mkdirs();
            }
            File file = new File(dir, query + ".html");
            PrintWriter out = new PrintWriter(new FileWriter(file));
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>책검색결과</title>");
            out.println("<body>");
            JSONArray arr = (JSONArray)obj.get("items");
        	for (int i = 0; i < arr.length(); i++) {
        		JSONObject item = arr.getJSONObject(i);
        		String title = item.getString("title");
        		String link = item.getString("link");
        		String image = item.getString("image");
        		out.println("<a href=\"" + link + "\">" + title + "</a><br>");
        		out.println("<img src=\"" + image + "\" width=\"80px\">");
        		out.println("<hr>");
        	}
            out.println("</body>");
            out.println("</html>");
            out.close();
        	System.out.println(query + ".html 파일에 결과가 저장되었습니다.");
        	con.disconnect();
        } catch (Exception e) {
        	try {
        		File dir = new File("C:\\download\\log");
                if(dir.exists() == false) {
                	dir.mkdirs();
                }
                File file = new File(dir, "error_log.txt");
                PrintWriter out = new PrintWriter(new FileWriter(file));
        		out.println("MESSAGE->" + e.getMessage() + "\t" + "TIME->" + new SimpleDateFormat("yyyy-MM-dd a h:mm:ss").format(new Date()));
        		out.close();
        		System.out.println("error_log.txt 파일에 에러 로그가 저장되었습니다.");
        	} catch (Exception e2) {
        		e2.printStackTrace();
        	}
        }
    }
}
