
public class NLP {
	public static String removePunc(String instring) {
		return instring.replace(",", "").replace("?"," ").replace(".", "").replace("!", "").toLowerCase();
		
	}
}
