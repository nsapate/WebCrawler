package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class WebCrawler {

	private final static String webLinks = "webLinks.txt";
	private static WebpageDownloader wd = new WebpageDownloader();
	
	private static  void callWebPageDownloader(String line){
		wd.start(line, "a", line.substring(7, 12));
		wd.start(line, "body", line.substring(7, 12));
		
	}
	public static void main(String args[]){

		try {
			FileInputStream file = new FileInputStream(webLinks);
			InputStreamReader reader = new InputStreamReader(file);
			BufferedReader buff = new BufferedReader (reader);
			
			String line = null;
			while((line = buff.readLine())!= null){
				callWebPageDownloader(line);
//				System.out.println(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
