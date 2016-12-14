package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class WebCrawler {

	private final static String webLinks = "webLinks.txt";
	private static WebpageDownloader wd = new WebpageDownloader();
	
	private static  void callWebPageDownloader(String url){
//		wd.start(url, "a");
		wd.start(url, "body");
		
	}
	public static void main(String args[]){

		try {
//			SearchResultParser.search();
			FileInputStream file = new FileInputStream(webLinks);
			InputStreamReader reader = new InputStreamReader(file);
			BufferedReader buff = new BufferedReader (reader);
			
			String url = null;
			while((url = buff.readLine())!= null){
				callWebPageDownloader(url);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
