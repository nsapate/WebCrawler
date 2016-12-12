package main;

import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebpageDownloader {

	public void start(String url, String tag, String name){
		crawl(url, tag, name);
	}

	private void crawl(String url, String tag, String name){
		Document doc;
		String str = null;

		PrintWriter out = null;
		try {
			doc = Jsoup.connect(url).get();
			Elements tags = doc.select(tag);
			out = new PrintWriter("files/"+ name+"_"+tag+".txt" );
			if(!tag.equals("a")){
				str = Jsoup.parse(tags.toString()).text();
				out.print(str);
			}
			else{
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
