
public class Webpage implements Comparable<Webpage>{
	public String url;
	public String title;
	public String desc;
	public int freq;
	public String keyword;
	public int score;
	public Webpage(String title, String url, String desc, String keyword, int freq, int score) {
		this.title = title;
		this.url = url;
		this.desc = desc;
		this.keyword = keyword;
		this.freq = freq;
		this.score = score;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Webpage) {
			Webpage wp = (Webpage) obj;
			return (this.url.equals(wp.url)) && (this.title.equals(wp.title));
		}else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		return this.url.hashCode();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public int compareTo(Webpage arg0) {
		if(arg0.url.equals(this.url) && this.title.equals(arg0.title) && arg0.freq == this.freq) {
			return 0;
		}
		int l = Integer.compare(arg0.score, this.score);
		if(l == 0) {
			int m = Integer.compare(arg0.freq, this.freq);
			if(m == 0) {
				return this.title.compareTo(arg0.title);
			}
			return m;
		}
		return l;
	}
}
