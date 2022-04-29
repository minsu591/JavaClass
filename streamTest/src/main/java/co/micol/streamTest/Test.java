package co.micol.streamTest;

import java.sql.Date;
import java.util.Random;

public class Test {
	public static void main(String[] args) {
		//a와 b는 같은 주소값
		String a = "hihi";
		String b = "hihi";
		System.out.println(a==b);
		System.out.println(a.equals(b));
		
		//c와 d는 다른 주소값
//		Scanner scn = new Scanner(System.in);
//		scn.next(); //String 선언으로 받아옴
		String c = new String("1");
		String d = new String("1");
		String e = c+d;
		System.out.println(e);
		System.out.println(c==d);
		System.out.println(c.equals(d));
		
		StringBuilder sb = new StringBuilder();
		sb.append("a");
		sb.append("b");
		sb.append("c");
		System.out.println(sb.toString());
		
		String exam = "1,2,3,4,5,6,7";
		String[] examResult = exam.split(",",3);
		for(String s : examResult) {
			System.out.println(s);
		}
		
		//================================
		Math.random();
		Random rd = new Random();
		long time = System.currentTimeMillis(); //현재 시간 찾는거
		long time2 = System.nanoTime(); //
		System.out.println(time);
		System.out.println(time2);
		rd.setSeed(time);
		System.out.println(rd.nextInt(10));
		
		Date sqlDate = new Date(50);
		System.out.println(sqlDate);
		
	}

}
