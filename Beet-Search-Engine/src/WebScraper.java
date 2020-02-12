import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.*;
public class WebScraper {
	public static int rate(Document doc, String keyword) {
		int score = 0;
		final int H1_SCORE = 20;
		final int P_SCORE = 1;
		final int TITLE_SCORE = 15;
		for(Element e:doc.getElementsByClass("h1")) {
			Map<String,Integer> freq = freq(Arrays.asList(e.text().split(" ")));
			try {
				score += freq.get(keyword) * H1_SCORE;
			}catch(NullPointerException ex) {
				
			}
		}
		for(Element e:doc.getElementsByClass("p")) {
			Map<String,Integer> freq = freq(Arrays.asList(e.text().split(" ")));
			try {
				score += freq.get(keyword) * P_SCORE;
			}catch(NullPointerException ex) {
				
			}
		}
		for(Element e:doc.getElementsByClass("title")) {
			Map<String,Integer> freq = freq(Arrays.asList(e.text().split(" ")));
			try {
				score += freq.get(keyword) * TITLE_SCORE;
			}catch(NullPointerException ex) {
				
			}
		}
		return score;
	}
	public static Map<String,Integer> freq(List<String> keywords){
		Map<String,Integer> freq = new HashMap<>();
		for(String keyword:keywords) {
			if(freq.containsKey(keyword)) {
				freq.put(keyword, freq.get(keyword)+1);
			}else {
				freq.put(keyword, 1);
			}
		}
		return freq;
	}
	public static String database = "C:\\Raymond\\searchDB\\keywords\\"; // No extra \\
	public static void addToDB(String url,String title, String desc, List<String> keywords, Document document) {
		Map<String,Integer> freq = freq(keywords);
		Set<String> keywords2 = (new HashSet<>(keywords));
		for(String keyword:keywords2) {
			int score = rate(document,keyword);
			File f = new File(database + keyword + ".keyword");
			if(!f.exists()) {
				if(f.isDirectory()) {
					continue;
				}
				WFile wf = new WFile(database + keyword + ".keyword");
				wf.write(title);
				wf.write(url);
				wf.write(desc);
				wf.write(Integer.toString(freq.get(keyword)));
				wf.write(Integer.toString(score));
				wf.close();
			}else {
				List<Webpage> urls = new ArrayList<>();
				TextFile tf = new TextFile(database + keyword + ".keyword");
				while(true) {
					String oldtitle = tf.readLine();
					String oldurl = tf.readLine();
					String olddesc = tf.readLine();
					String oldfreq = tf.readLine();
					String oldscore = tf.readLine();
					if(oldtitle == null || oldurl == null || olddesc == null || oldfreq == null || oldscore == null) {
						break;
					}
					Webpage wp = new Webpage(oldtitle, oldurl, olddesc, keyword, Integer.parseInt(oldfreq), Integer.parseInt(oldscore));
					urls.add(wp);
				}
				/*
				urls.sort(new Comparator<Webpage>(){
					public int compare(Webpage x,Webpage y) {
						int m = Integer.compare(x.freq, y.freq);
						if(m==0) {
							//System.out.println(x.title+" "+y.title);
							return x.title.compareTo(y.title);
						}
						return m;
					}
				});
				*/
				Collections.sort(urls);
				TreeSet<Webpage> hs = new TreeSet<Webpage>(urls);
				//hs.
				//Collections.sort(hs);
				hs.add(new Webpage(title,url,desc,keyword,freq.get(keyword),score));
				WFile wf = new WFile(database + keyword + ".keyword");
				for(Webpage wp:hs) {
					wf.write(wp.title);
					wf.write(wp.url);
					wf.write(wp.desc);
					wf.write(Integer.toString(wp.freq));
					wf.write(Integer.toString(score));
				}
				wf.close();
			}
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TextFile f = new TextFile("toscrape.txt");
		HashSet<String> commonwords = new HashSet<>((new TextFile("commonwords.txt").getLines()));
		while(true) {
			String url = f.readLine();
			if(url == null) {
				break;
			}
			try {
			Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36").get();
			List<String> keywords = new ArrayList<>();
			String title = doc.title();
			System.out.println(url);
			System.out.println(title);
			for(String s: NLP.removePunc(title).split(" ")) {
				//System.out.println(s);
				if(!commonwords.contains(s) && s.length()>3) {
					keywords.add(s);
				}
			}
			
			for(String s: NLP.removePunc(doc.body().text()).split(" ")) {
				//System.out.println(s);
				if(!commonwords.contains(s) && s.length()>3) {
					keywords.add(s);
				}
			}
			
			addToDB(url, title, doc.body().text().substring(0, 50), keywords, doc);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
