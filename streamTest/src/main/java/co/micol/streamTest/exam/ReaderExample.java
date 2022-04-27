package co.micol.streamTest.exam;

import java.io.FileReader;
import java.io.Reader;

public class ReaderExample { //문자 입력 스트림
	public void run() {
		try {
			Reader rd = new FileReader("C:\\Temp\\hi.text");
			while(true) {
				int data = rd.read();
				if(data == -1) break;
				System.out.print((char)data);
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
