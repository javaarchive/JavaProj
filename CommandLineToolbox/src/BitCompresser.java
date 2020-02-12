import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class BitCompresser extends Compressor {
	@Override
	public void compress(FileInputStream in, FileOutputStream out)throws IOException {
		BitInputStream2 data = new BitInputStream2(in);
		PrintStream ps = new PrintStream(out);
		int prev = data.read();
		int c = 1;
		if(prev == 1) {
			ps.println(0);
		}
		while(true) {
			int i = data.readBits(1);
			System.out.println(i);
			if(i == -1) {
				break;
			}
			if(i != prev) {
				ps.println(c);
				prev = i;
				c =0 ;
				c++;
			}else {
				c++;
			}
			
		}
	}
	@Override
	public void decompress(FileInputStream in, FileOutputStream out)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		BitOutputStream bos = new BitOutputStream(out);
		int i = 0;
		String str = br.readLine();
		while(str != null && str.length() > 0) {
			int num = Integer.parseInt(str);
			boolean val = (i%2) == 0;
			for(int j = 0; j < num;j++) {
				System.out.println(!val);
				bos.write(!val);
			}
			str = br.readLine();
			i++;
		}
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.exit((new BitCompresser()).run(args));
	}

}
