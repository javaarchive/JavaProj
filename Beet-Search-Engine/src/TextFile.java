import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TextFile {
	String filename;
	BufferedReader br;
	public TextFile(String name) {
		this.filename = name;
		try {
			this.br = new BufferedReader(new FileReader(name));
		}catch(Exception ex) {
			this.br = null;
		}
	}
	public List<String> getLines(){
		List<String> lines = new ArrayList<>();
		String line = "";
		while(line != null) {
			line = this.readLine();
			if(line == null) {
				break;
			}
			lines.add(line);
		}
		this.close();
		return lines;
	}
	public String readLine() {
		try {
			return this.br.readLine();
		}catch(Exception ex) {
			return "";
		}
	}
	public void close() {
		try {
			this.br.close();
		}catch(Exception ex) {
			return;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
