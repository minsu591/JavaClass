package co.mia.farm;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import co.mia.farm.account.AccountService;
import co.mia.farm.account.AccountServiceImpl;
import co.mia.farm.account.AccountVO;
import co.mia.farm.account.CharacterVO;
import co.mia.farm.game.item.event.TutorialService;
import co.mia.farm.game.print.ConsolePrintService;

public class LoginMenu {
	private Scanner scn = new Scanner(System.in);
	public static AccountVO loginAccount = new AccountVO();
	public static CharacterVO loginCharacter = new CharacterVO();
	private AccountService as = new AccountServiceImpl();
	public static boolean checkGame = false;
	public static boolean checkLogin = true;
	private TutorialService ts = new TutorialService();

	public void run() {
		login();
		if (!StringUtils.isEmpty(LoginMenu.loginAccount.getAccId())) {
			checkGame = true;
		}
		
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
		System.out.println("\n.oO[농장 방문이 처음이신가요?]");
		System.out.print(".oO[닉네임을 정해주세요] >>> ");
		newCh.setUserNickname(scn.next());
		System.out.print(".oO[농장 이름을 정해주세요] >>> ");
		newCh.setFarmName(scn.next());
		System.out.println(".oO[선택할 캐릭터를 번호로 정해주세요! (그 외의 정보를 입력하면 1번 캐릭터가 삽입됩니다.)]");
		System.out.println("[ 1. ˘ᗜ˘ || 2. ·з· || 3. ๑'ٮ'๑ || 4. ∗❛⌄❛∗ ]");
		System.out.print(">>> ");
		int ch = -1;
		try {
			ch= scn.nextInt();
		}catch(Exception e) {
			System.out.println("1번 캐릭터가 삽입됩니다.");
			scn.next();
		}
		String character = "˘ᗜ˘";
		if(ch == 2) {
			character = "·з·";
		}else if(ch == 3) {
			character = "๑'ٮ'๑";
		}else if(ch == 4) {
			character = "∗❛⌄❛∗";
		}
		newCh.setUserCharacter(character);
		int n = as.characterInsert(newCh); //캐릭터 정보 삽입
		loginCharacter = as.characterSelect(loginAccount.getAccId());
		
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
		ts.run();
	}
	
	
	
	private void login() {

		int menu = -1;

		while (true) {
			ConsolePrintService.clearScreen();
			intro();
			System.out.println("[ 1. 로그인 | 2. 회원가입 | 3. 회원정보 수정 | 4. 랭킹 확인 | 9. 종료 ]");
			System.out.print(">>> ");

			try {
				menu = scn.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("다시 입력바랍니다...");
				scn.next();
			}

			if (menu == 9) { // 종료
				System.out.println("로그인 시스템을 종료합니다.");
				LoginMenu.checkLogin = false;
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
					}else {
						loginCharacter = as.characterSelect(loginAccount.getAccId());
					}
					System.out.println("\n농장에 입장합니다...");
					for(int i = 3; i>0;i--) {
						System.out.println(i);
						StaticMenu.waitTime(1000);
					}

					checkGame = true;

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
					System.out.println("환영합니다! 회원가입이 완료되었습니다!\n");
				} else {
					System.out.println("이미 존재하는 아이디입니다. 다시 시도해주세요.\n");
				}
				StaticMenu.waitTime(1000);
			} else if(menu == 4) { //랭킹 확인
				ConsolePrintService.clearScreen();
				List<CharacterVO> chList = as.characterAllSelect();
				System.out.print("[ 1. 명예의 전당 | 2. 아이디별 순위 확인하기 ] >>> ");
				int c = -1;
				try {
					c = scn.nextInt();		
				}catch(Exception e) {
					System.out.println("랭킹 확인이 취소되었습니다.");
					scn.next();
				}
				if(c == 1) { //명예의 전당
					System.out.printf("%30s\n","[※ 축 하 합 니 다 ※]");
					
					for(int i = 0;i<3;i++) {
						chList.get(i).printRanking(i+1);
					}
					System.out.print("종료하려면 아무키나 입력하세요 >>> ");
					scn.next();
				}else if (c ==2) { //아이디별 순위 확인하기
					System.out.print("랭킹을 조회할 아이디를 입력해주세요 >>> ");
					String s = scn.next();
					boolean flag = true;
					for(int i = 0; i<chList.size();i++) {
						if(s.equals(chList.get(i).getAccId())) {
							chList.get(i).printRanking(i+1);
							flag = false;
							break;
						}
					}
					System.out.print("종료하려면 아무키나 입력하세요 >>> ");
					scn.next();
					if(flag) {
						System.out.println("해당 아이디의 캐릭터가 존재하지 않습니다.");
						StaticMenu.waitTime(1000);
					}
				}
				
				
				
				
			} else if(menu == 3) {
				System.out.println("[회원 정보 수정을 진행합니다]");
				System.out.print("수정할 아이디를 입력해주세요 >>> ");
				String modId = scn.next();
				System.out.print("수정할 비밀번호를 입력해주세요 >>> ");
				String modPw = scn.next();
				AccountVO vo = new AccountVO();
				vo.setAccId(modId);
				vo.setAccPw(modPw);
				vo = as.accountLogin(vo);
				StaticMenu.waitTime(1500);
				if(!StringUtils.isEmpty(vo.getAccId())) {
					while(true) {
						ConsolePrintService.clearScreen();
						System.out.println("수정할 정보를 선택해주세요.");
						System.out.print("[ 1. 비밀번호 | 2. 농장 이름 | 3. 닉네임 | 4. 회원 탈퇴 | 5. 나가기 ] >>> ");
						try {
							menu =scn.nextInt();							
							scn.nextLine();
						}catch(Exception e) {
							System.out.println("숫자를 입력해주세요.");
							scn.nextLine();
						}
						if(menu ==1) {
							System.out.println("비밀번호 변경을 진행합니다.");
							System.out.print("변경할 비밀번호를 입력해주세요 >>> ");
							String newPw = scn.next();
							if(newPw.equals(vo.getAccPw())) {
								System.out.println("이전 비밀번호와 동일합니다. 다시 시도해주세요...");
								StaticMenu.waitTime(1000);
							}else {
								System.out.println("입력한 비밀번호로 비밀번호 변경을 진행합니다.");
								vo.setAccPw(newPw);
								as.accountUpdate(vo);
								System.out.println("비밀번호 변경이 완료되었습니다.");
								System.out.println("로그인 페이지로 가서 로그인해주세요.");
								StaticMenu.waitTime(2000);
							}
						}else if(menu ==2 ) {
							System.out.println("농장 이름 변경을 진행합니다.");
							CharacterVO myCh = as.characterSelect(vo.getAccId());							
							if(!StringUtils.isEmpty(myCh.getFarmName())) { //기존 캐릭터 있으면 false
								System.out.print("변경할 농장 이름을 입력해주세요 >>> ");
								String name = scn.next();
								myCh.setFarmName(name);
								as.characterUpdate(myCh, 0);
								System.out.println("농장 이름 변경이 완료되었습니다.");
								System.out.println("로그인 페이지로 가서 로그인해주세요.");
								StaticMenu.waitTime(2000);
							}else {
								System.out.println("로그인해서 캐릭터를 먼저 생성해주세요.");
								StaticMenu.waitTime(2000);
							}
						}else if(menu ==3) {
							System.out.println("닉네임 변경을 진행합니다.");
							CharacterVO myCh = as.characterSelect(vo.getAccId());
							if(!StringUtils.isEmpty(myCh.getFarmName())) { //기존 캐릭터 있으면 false
								System.out.print("변경할 닉네임을 입력해주세요 >>> ");
								String name = scn.next();
								myCh.setUserNickname(name);
								as.characterUpdate(myCh, 1);
								System.out.println("닉네임 변경이 완료되었습니다.");
								System.out.println("로그인 페이지로 가서 로그인해주세요.");
								StaticMenu.waitTime(2000);
							}else {
								System.out.println("로그인해서 캐릭터를 먼저 생성해주세요.");
								StaticMenu.waitTime(2000);
							}
						}else if(menu ==5) {
							System.out.println("정보 수정을 종료합니다.");
							ConsolePrintService.clearScreen();
							break;
						}else if(menu == 4) {
							System.out.println("회원 탈퇴를 진행합니다.");
							System.out.print("탈퇴하면 모든 회원 정보가 사라지고 복구가 불가능합니다. 계속하시겠습니까? (y/n) >>> ");
							String ans = scn.next();
							if(ans.equalsIgnoreCase("y")) {
								System.out.print("회원 탈퇴 진행을 위해 비밀번호를 재확인하겠습니다. 비밀번호를 입력해주세요. >>>");
								String pw = scn.next();
								if(pw.equals(vo.getAccPw())) {
									System.out.println("회원 정보를 삭제합니다.");
									int n = as.accountDelete(vo);
									if(n==1) {
										System.out.println("삭제 완료 되었습니다.");
										StaticMenu.waitTime(1000);
										ConsolePrintService.clearScreen();
										break;
									}
								}else {
									System.out.println("비밀번호가 다릅니다. 다시 시도해주세요.");
									StaticMenu.waitTime(1000);
								}
							}
						}
						
					}
				}	
				
			}
		}

	}
}
