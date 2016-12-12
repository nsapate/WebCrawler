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
		
//		String str = Jsoup.parse(tags.toString()).text();
//		out = new PrintWriter("files/"+ name+"_"+tag+".txt" );
//		out.print(str);
//		System.out.println(str);

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
       out.close();
    }

	}
	/*public static String html2text(String html) {
	    return Jsoup.parse(html).text();
	}
	
	public static void main(String[] args) {
	    URL url;
	    InputStream is = null;
	    BufferedReader br;
	    BufferedWriter bw ;
	    String line;
	    

	    try {
	        url = new URL("http://www.programcreek.com/2012/12/how-to-make-a-web-crawler-using-java//");
	        is = url.openStream();  // throws an IOException
	        br = new BufferedReader(new InputStreamReader(is));
	        bw = new BufferedWriter(new FileWriter("out.txt"));

	        while ((line = br.readLine()) != null) {
	        	
///	            System.out.println(line);
	            bw.write(html2text(line));
	        }
	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
	}*/
}
