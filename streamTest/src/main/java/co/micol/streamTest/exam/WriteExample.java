package co.micol.streamTest.exam;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class WriteExample {
	// 멤버 변수
	// 생성자 정의
	// 멤버 메소드
	public void run() {
		try {
			OutputStream op = new FileOutputStream("c:/Temp/hi.text");
			byte[] arr = {'A','B','C','D','E'};
			op.write(arr);

			op.flush();
			op.close();
			System.out.println("출력 완료!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
