import java.io.IOException;
import java.io.InputStream;

class BitInputStream {

    private InputStream in;
    private int num = 0;
    private int count = 8;

    public BitInputStream(InputStream in) {
        this.in = in;
    }

    public int read() throws IOException {
    	int input = this.in.read();
    	if(input == -1 && this.num == 0) {
    		return -1;
    	}
        if (this.count == 8){
            this.num = input + 128;
            this.count = 0;
        }

        boolean x = (num%2 == 1);
        num /= 2;
        this.count++;

        return x ? 1:0;
    }

    public void close() throws IOException {
        this.in.close();
    }

}