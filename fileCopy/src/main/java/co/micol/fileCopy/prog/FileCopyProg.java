package co.micol.fileCopy.prog;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class FileCopyProg {
	public void run() {
		try {
			Reader rd = new FileReader("C:\\Temp\\source.txt");
			Writer wt = new FileWriter("C:\\Temp\\object.txt");
			while(true) {
				int data = rd.read();
				if(data == -1) break;
				wt.write(data);
			}
			rd.close();
			wt.flush();
			wt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
