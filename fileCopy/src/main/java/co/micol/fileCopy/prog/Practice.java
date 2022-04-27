package co.micol.fileCopy.prog;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;

public class Practice {
	
	public void inputStreamRun() {// steam 파일을 읽어오고 싶음
		try {
			InputStream is = new FileInputStream("C:\\Temp\\hi.text");
			while(true) {
				int data = is.read(); //읽어온걸 data에 '하나씩' 저장
				if(data == -1) break; //다 읽으면 while문 탈출
				System.out.println(data); //data를 출력
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void outputStreamRun() {//stream, 문자를 파일로 출력하고 싶음
		try {
			OutputStream ops = new FileOutputStream("C:\\Temp\\outputTest.text");
			byte[] t = {67,68,69};
			ops.write(t);
			ops.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readRun() { //문자, 파일로 읽어오기
		try {
			Reader rd = new FileReader("C:\\Temp\\hi.text");
			while(true) {
				int data = rd.read();
				if(data==-1) break;
				System.out.println((char)data);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
