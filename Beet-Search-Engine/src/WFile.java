import java.io.*;
public class WFile {
	String filename;
	PrintWriter pw;
	public WFile(String fname) {
		this.filename = fname;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(fname)));
		}catch(Exception e) {
			pw = null;
		}
	}
	public void write(String score) {
		try {
			pw.println(score);
		}catch(Exception ex) {
			
		}
	}
	public void close() {
		try {
			this.pw.close();
		}catch(Exception ex) {
			return;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
