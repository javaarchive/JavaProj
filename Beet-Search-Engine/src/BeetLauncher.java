import java.io.File;

public class BeetLauncher {
	public static void launch(char arg,String dataabase) {
		String[] noargs = {};
		if(arg == 's') {
			System.out.println("Server started");
			try {
			WebScraper.main(noargs);
			}catch(Exception ex) {
				
			}
		}else if(arg == 'c'){
			System.out.println("Launched Client");
			cmdSearchClient.main(noargs);
		}else {
			System.out.println("ERROR: Invalid option");
		}
		
	}
	public static void main(String[] args) {
		String dbdir;
		boolean isDevPC = false;
		if((new File("C:/Users/raymo")).isDirectory() && (new File("C:\\Users\\raymo\\Videos\\Captures").isDirectory())) {
			isDevPC = true;
		}
		if(args.length == 0) {
		System.out.println("Welcome to the Beet Search Engine");
		System.out.println("There are two modes that can be accessed with this edition of beet. ");
		System.out.println("Scraper(use -s) creates new data");
		System.out.println("Client(use -c): Opens database and provides search");
		System.out.println("Scraper requires 2 files: commonwords.txt which is a list of common words or keywords to ignore and toscrape.txt a text file of urls to scrape");
		if(!isDevPC) {
			System.out.println("Please add the directory of the database after the arguments with a extra slash.");
		}else {
			System.out.println("Detetced database location");
		}
		} 
		if(args.length == 1) {
			if(isDevPC) {
				dbdir ="C:\\Raymond\\searchDB\\keywords\\";
				launch(args[0].charAt(1), dbdir);
			}else {
				System.out.println("ERROR: Not enough arguments");
			}
		}
		if(args.length == 2) {
			try {
			launch(args[0].charAt(1), args[1]);
			}catch(Exception ex) {
				System.out.println("Bad Format");
			}
		}
		
	}

}
