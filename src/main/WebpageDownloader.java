package main;

import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebpageDownloader {

	public void start(String url, String tag){
		crawl(url, tag);
	}

	private void crawl(String url, String tag){
		Document doc;
		String str = null;

		PrintWriter out = null;
		try {
			doc = Jsoup.connect(url).timeout(10000).get();
			Elements tags = doc.select(tag);
			out = new PrintWriter("files/"+ url.substring(7, 16)+"_"+tag+".txt" );
			if(tag.equals("body")){
				str = Jsoup.parse(tags.toString()).text();
				out.print(str);
			}
			else if(tag.equals("a")){
				for(Element e : tags){
					out.println(e.attr("href"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
	}
}
