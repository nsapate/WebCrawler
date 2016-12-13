package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchResultParser {
	private static Pattern patternDomainName;
	private Matcher matcher;
	private static final String DOMAIN_NAME_PATTERN
	= "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
	static {
		patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
	}
	public static void search(){
		googleSearch();
	}
	private static void googleSearch(){

		SearchResultParser obj = new SearchResultParser();
		//Enter your search parameter
		Set<String> result = obj.getDataFromGoogle("jsoup examples");

		PrintWriter out = null;
		try {
			out = new PrintWriter("webLinks.txt");
			for(String temp : result){
				out.println(temp.split("&")[0]);
				System.out.println(temp);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

	public String getDomainName(String url){
		String domainName = "";
		matcher = patternDomainName.matcher(url);
		if (matcher.find()) {
			domainName = matcher.group(0).toLowerCase().trim();
		}
		return domainName;
	}

	private Set<String> getDataFromGoogle(String query) {

		Set<String> result = new HashSet<String>();
		String request = "https://www.google.com/search?q=" + query + "&num=3";
		System.out.println("Sending request..." + request);

		try {

			Document doc = Jsoup
					.connect(request)
					.userAgent(
							"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
					.timeout(5000).get();

			Elements links = doc.select("a[href]");
			for (Element link : links) {

				String temp = link.attr("href");
				if(temp.startsWith("/url?q=")){
					//use regex to get domain name
					if(!temp.contains("webcache")){
						result.add(temp.substring(7));
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}
