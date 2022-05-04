package co.mia.farm;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import co.mia.farm.dto.AccountVO;

public class Menu {
	private Scanner scn = new Scanner(System.in);
	private boolean successLogin = false;
	
	//수정하기_회원테이블
	private List<AccountVO> accountList = new ArrayList<AccountVO>();
	
	public void run() {
		intro();
		successLogin = login();
		
		while(successLogin) {
			playGame();
			
		}
		
	}
	
	private void intro() {//시작화면
		//수정하기_회원테이블 임시데이터
		accountList.add(new AccountVO("admin","admin","0"));
		
		System.out.println("----------------------------------");
		System.out.println("======     ==     =====   =     =  ");
		System.out.println("=         =  =    =    =  = = = =   ");
		System.out.println("======   ======   =====   =  =  =   ");
		System.out.println("=       =      =  =    =  =     =  ");
		System.out.println("=      =        = =     = =     =  ");
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println();
	}
	
	private void playGame() {
		
	}
	
	private boolean login() {
		System.out.println("미아팜에 오신 것을 환영합니다!");
		int menu = -1;
		
		while(true) {
			System.out.println("1. 로그인 | 2. 회원가입 | 9. 종료");
			
			try {
				menu = scn.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("다시 입력바랍니다...");
				scn.nextLine();
			}

			if(menu == 9) { //종료
				System.out.println("게임을 종료합니다!");
				System.out.println("다음에 또 찾아주세요!");
				successLogin = false;
				break;
			}else if(menu ==1) { //로그인
				System.out.println("[로그인을 진행합니다]");
				System.out.print("아이디 >>> ");
				String id = scn.nextLine();
				System.out.print("비밀번호 >>> ");
				String pw = scn.nextLine();
				//수정_회원 테이블 연결
				if(id.equals("admin") && pw.equals("admin")) {
					System.out.println("안녕하세요, "+id+"농부님!");
					successLogin = true;
					break;
				}else {
					System.out.println("아이디나 비밀번호가 틀렸습니다.");
					continue;
				}
			}else if(menu ==2) { //회원가입 (아이디, 비밀번호, 전화번호)
				System.out.println("[회원가입을 진행합니다]");
				System.out.print("가입 아이디 >>> ");
				String id = scn.nextLine();
				System.out.print("가입 비밀번호 >>> ");
				String pw = scn.nextLine();
				System.out.print("가입 전화번호 >>> ");
				String phoneNum = scn.nextLine();
				System.out.println("가입을 환영합니다!");
				System.out.println("돌아가서 로그인을 해주세요.");
			}else {
				System.out.println("존재하는 아이디나 비밀번호입니다.");
			}
		}
		return successLogin;
		
		
		
	}
	//1. 회원
	//	1. 로그인 2. 회원가입 3. 종료

	
	//2. 로그인 후
	//1. 플레이 0. 로그아웃
	//1. 이동 2. 상점 3. 아이템창
	

}
