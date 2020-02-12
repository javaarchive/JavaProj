import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Compressor {
	public void compress(FileInputStream in, FileOutputStream out)throws IOException {
		
	}
	public void decompress(FileInputStream in, FileOutputStream out)throws IOException {
		
	}
	public int run(String[] args) throws IOException{
		if(args.length == 0) {
			System.out.println("Options: +/- <filename>, + to decompress, - to compress");
			return 0;
		}else if(args.length == 2) {
			String f = args[1];
			FileInputStream in = new FileInputStream(new File(f));
			if(args[0].equals("+")) {
				FileOutputStream out = new FileOutputStream(new File("de-"+f.substring(0,f.lastIndexOf('.'))));
				this.decompress(in, out);
				out.close();
			}else if(args[0].equals("-")) {
				FileOutputStream out = new FileOutputStream(new File( f + ".comp"));
				this.compress(in, out);
				out.close();
			}
			in.close();
			return 0;
		}
		System.out.println("Invaild combination of arguments");
		return 1;
	}
	public static void main(String[] args) throws IOException{
		System.exit((new Compressor()).run(args));
		// TODO Auto-generated method stub

	}

}
