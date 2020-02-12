import java.io.*;
import java.util.*;
public class cmdSearchClient {
	
	public static String database = "C:\\Raymond\\searchDB\\keywords\\";
	public static int firstQuery(String[] words) {
		for(int i = 0; i < words.length; i ++) {
			File f = new File(database + words[i] + ".keyword");
			if(f.exists() && f.isFile()) {
				return i;
			}
		}
		return words.length;
	}
	public static boolean checkIsValidQuery(String query) {
		File f = new File(database + query + ".keyword");
		if(f.exists() && f.isFile()) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			String query = sc.nextLine();
			if(query.equals("quit") || query.equals("exit") || query.length() == 0) {
				break;
			}
			query = NLP.removePunc(query);
			String[] words = query.split(" ");
			int fq = firstQuery(words);
			if(fq == words.length) {
				continue;
			}
			TextFile tf = new TextFile(database + words[fq] + ".keyword");
			Set<Webpage> results = new HashSet<>();
			while(true) {
				String title = tf.readLine();
				String url = tf.readLine();
				String desc = tf.readLine();
				String freq = tf.readLine();
				String score = tf.readLine();
				if(title == null || url == null || desc == null || freq == null || score == null) {
					break;
				}
				Webpage wp = new Webpage(title,url,desc,words[fq],Integer.parseInt(freq),Integer.parseInt(score));
				results.add(wp);
			}
			tf.close();
			if(words.length != 1) {
				for(int i = fq; i < words.length; i ++) {
					if(checkIsValidQuery(words[i])) {
						tf = new TextFile(database + words[i] + ".keyword");
						Set<Webpage> temp = new HashSet<>();
						while(true) {
							String title = tf.readLine();
							String url = tf.readLine();
							String desc = tf.readLine();
							String freq = tf.readLine();
							String score = tf.readLine();
							if(title == null || url == null || desc == null || freq == null || score == null) {
								break;
							}
							Webpage wp = new Webpage(title,url,desc,words[fq],Integer.parseInt(freq),Integer.parseInt(score));
							temp.add(wp);
						}
						results.retainAll(temp);
					}else {
						continue;
					}
				}
			}
			//results.
			List<Webpage> final_results = new ArrayList<>(results);
			final_results.sort(null);
			for(Webpage wp:final_results) {
				System.out.println(wp.title + " | "+wp.freq+" | Score: "+wp.score);
				System.out.println(wp.url);
				System.out.println(wp.desc);
				System.out.println("-------------------------------------------");
			}
		}
	}

}
