package co.micol.streamTest.exam;

import java.io.FileInputStream;
import java.io.InputStream;

public class ReadExample { //바이트 입력 스트림
	
	public void run() {
		try {
			InputStream ins = new FileInputStream("C:\\Temp\\hi.text");
			byte[] buffer = new byte[100];
			
			while(true) {
				int data = ins.read(buffer);
				if(data == -1) break;
				for(int i =0 ;i<buffer.length;i++) {
					if(buffer[i]==0) {
						break;
					}
					System.out.println(buffer[i]);
				}
			}
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
