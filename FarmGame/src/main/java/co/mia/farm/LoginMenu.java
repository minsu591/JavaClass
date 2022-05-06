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
	public AccountVO loginAccount = new AccountVO();
	private AccountService as = new AccountServiceImpl();

	public AccountVO run() {
		intro();
		login();
		return loginAccount;
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
		//vo의 아이디를 캐릭터 테이블에서 검색
		//없으면
		CharacterVO newCh = new CharacterVO();
		newCh.setAcc_id(loginAccount.getAccId());
		System.out.println("농장 방문이 처음이신가요? 튜토리얼을 진행합니다.");
		System.out.print("닉네임을 정해주세요 >>> ");
		newCh.setUser_nickname(scn.next());
		System.out.print("농장 이름을 정해주세요 >>> ");		
		newCh.setFarm_name(scn.nextLine());
		scn.nextLine();
		int n = as.characterInsert(newCh);
		if(n==1) {
			System.out.println("==========================================================");
			System.out.println("반갑습니다! "+newCh.getUser_nickname()+"님!");
			System.out.println("당신은 도시 생활에 지쳐, 과거에 즐거운 한 때를 보낸 시골 별장으로 돌아왔습니다.");
			System.out.println("이곳에서 당신의 꿈을 펼쳐보세요!");
			System.out.println("==========================================================");
		}else {
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
				if(StringUtils.isEmpty(vo.getAccId())) {
					System.out.println("다시 로그인해주세요!");
				}else {
					loginAccount = vo; //로그인 계정에 저장
					//캐릭터 확인
					if(as.characterCheck(loginAccount)) {
						tutorialFarm();
					}else {
						System.out.println("다시 찾아주셔서 감사합니다!");
					}
					System.out.println("농장에 입장합니다...");
					try {
						for(int i =3 ; i>0;i--) {
							System.out.println(i);
							Thread.sleep(1000);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
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
	// 1. 회원
	// 1. 로그인 2. 회원가입 3. 종료

	// 2. 로그인 후
	// 1. 플레이 0. 로그아웃
	// 1. 이동 2. 상점 3. 아이템창

}
