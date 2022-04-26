package co.micol.prj.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashtableExample {
	private Map<String, String> map;
	Scanner scn = new Scanner(System.in);
	
	public void infoCollection() {
		map = new HashMap<String, String>();
		map.put("spring", "1");
		map.put("summer", "2");
		map.put("fall", "3");
		map.put("winter", "4");
	}
	
	public void logIn() {
		while(true) {
			System.out.println("아이디를 입력해주세요 : ");
			String id = scn.next();
			System.out.println("비밀번호를 입력해주세요 : ");
			String passwd = scn.next();
			
			if(map.get(id).equals(passwd)) {
				System.out.println("로그인에 성공했습니다.");
				break;
			}
			
		}
	}

}
