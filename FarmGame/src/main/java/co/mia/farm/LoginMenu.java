package co.mia.farm;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import co.mia.farm.account.AccountService;
import co.mia.farm.account.AccountServiceImpl;
import co.mia.farm.account.AccountVO;
import co.mia.farm.account.CharacterVO;

public class LoginMenu {
	private Scanner scn = new Scanner(System.in);
	public static AccountVO loginAccount = new AccountVO();
	public static CharacterVO loginCharacter = new CharacterVO();
	private AccountService as = new AccountServiceImpl();

	public void run() {
		intro();
		login();
	}
	
	private void intro() {// 시작화면
		// 수정하기_회원테이블 임시데이터

		System.out.println("----------------------------------");
		System.out.println("======     ==     =====   =     =  ");
		System.out.println("=         =  =    =    =  = = = =   ");
		System.out.println("======   ======   =====   =  =  =   ");
		System.out.println("=       =      =  =    =  =     =  ");
		System.out.println("=      =        = =     = =     =  ");
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println("-------미아팜에 오신 것을 환영합니다!------");
	}


	private void tutorialFarm() {
		// vo의 아이디를 캐릭터 테이블에서 검색
		// 없으면
		CharacterVO newCh = new CharacterVO();
		newCh.setAccId(loginAccount.getAccId());
		System.out.println("\n.oO[농장 방문이 처음이신가요? 튜토리얼을 진행합니다.]");
		System.out.print(".oO[닉네임을 정해주세요] >>> ");
		newCh.setUserNickname(scn.next());
		System.out.print(".oO[농장 이름을 정해주세요] >>> ");
		newCh.setFarmName(scn.next());
		int n = as.characterInsert(newCh);
		if (n == 1) {
			System.out.println("\n==========================================================");
			System.out.println("반갑습니다! " + newCh.getUserNickname() + "님!");
			System.out.println("\n당신은 도시 생활에 지쳐, 과거에 즐거운 한 때를 보낸 시골 별장으로 돌아왔습니다.");
			System.out.println("\n이곳에서 당신의 꿈을 펼쳐보세요!");
			System.out.println("==========================================================\n");
			StaticMenu.waitTime(2500);
		} else {
			System.out.println("농장 생성에 실패했습니다... 다시 시도해주세요");
		}
	}

	private void login() {

		int menu = -1;

		while (true) {
			System.out.println("1. 로그인 | 2. 회원가입 | 9. 종료");

			try {
				menu = scn.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("다시 입력바랍니다...");
				scn.next();
			}

			if (menu == 9) { // 종료
				System.out.println("게임을 종료합니다!");
				System.out.println("다음에 또 찾아주세요!");
				break;
			} else if (menu == 1) { // 로그인
				System.out.print("아이디 >>> ");
				String accId = scn.next();
				System.out.print("비밀번호 >>> ");
				String accPw = scn.next();
				AccountVO vo = new AccountVO(accId, accPw, "before login");
				vo = as.accountLogin(vo);
				if (StringUtils.isEmpty(vo.getAccId())) {
					System.out.println("다시 로그인해주세요!");
				} else {
					loginAccount = vo; // 계정 상수에 저장
					// 캐릭터 확인
					if (as.characterCheck(loginAccount.getAccId())) {
						tutorialFarm();
					} else {
					}
					loginCharacter = as.characterSelect(loginAccount.getAccId());
					System.out.println("농장에 입장합니다...");
					for(int i = 3; i>0;i--) {
						System.out.println(i);
						StaticMenu.waitTime(1000);
					}

					break;
				}
			} else if (menu == 2) { // 회원가입 (아이디, 비밀번호, 전화번호)
				System.out.println("[회원가입을 진행합니다]");
				System.out.print("가입 아이디 >>> ");
				String accId = scn.next();
				System.out.print("가입 비밀번호 >>> ");
				String accPw = scn.next();
				System.out.print("가입 전화번호 >>> ");
				String accPhone = scn.next();
				AccountVO newAcc = new AccountVO(accId, accPw, accPhone);
				int check = as.accountInsert(newAcc);
				// 이미 있는 아이디면 넘어가게
				if (check == 1) {
					System.out.println("환영합니다! 회원가입이 완료되었습니다!");
				} else {
					System.out.println("이미 존재하는 아이디입니다. 다시 시도해주세요.");
				}
			}
		}

	}
}
