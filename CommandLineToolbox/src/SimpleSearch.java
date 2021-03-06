import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.*;
public class SimpleSearch {
	
	public static void main(String[] args) throws IOException{
		String default_url = "https://artofproblemsolving.com";
		if(args.length == 1) {
			default_url = args[1];
		}
		String[] blockedFileTypes = {".jpg",".jpeg",".bmp",".gif",".ico",".mp3",".mp4",".js",".css"};
		Queue<String> urls = new LinkedList<>();
		Pattern p = Pattern.compile("/(\\S+\\.(cn|com|net|org|edu|gov)(\\/\\S+)?)/");
		ArrayList<String> results = new ArrayList<>();
		urls.add(default_url);
		InputStream is;
		while(!urls.isEmpty()) {
			String query = urls.poll();
			
			String output = "";
			try {
		        URL url = new URL(query);
		        is = url.openStream();  // throws an IOException
		        BufferedReader br = new BufferedReader(new InputStreamReader(is));
		        String line;
		        while ((line = br.readLine()) != null) {
		            System.out.println(line);
		            output = output + line + "\n";
		        }
		        
		        if (is != null) is.close();
		        results.add(query);
		        
		    } catch (Exception ioe) {
		    	System.out.println("Internal Error "+ioe.toString());
		         //ioe.printStackTrace();
		         continue;
		    } 
		        
			 Matcher matcher = p.matcher(output);
			 while (matcher.find()) {
				 String url = matcher.group();
				 if(url.startsWith("//")) {
					 url = "http:" + url;
				 }
				 System.out.println(url);
				 boolean isValid = true;         				
				for(String filetype:blockedFileTypes) {
					 if(url.endsWith(filetype)) {
						 isValid = false;
				 	}
				 }		
				 if(isValid) {
					 urls.add(url); 
				 }
				 
			 }
			 if(results.size() > 25) {
				 PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("search.out")));
				 for(String u: results) {
					 pw.println(u);
				 }
				 pw.close();
				 break;
			 }
		}
	}

}
