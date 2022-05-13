package co.mia.farm.game.print;

import java.util.List;
import java.util.Scanner;

import co.mia.farm.LoginMenu;
import co.mia.farm.StaticMenu;
import co.mia.farm.account.AccountService;
import co.mia.farm.account.AccountServiceImpl;
import co.mia.farm.account.AccountVO;
import co.mia.farm.account.CharacterVO;
import co.mia.farm.game.farming.FarmingMenu;
import co.mia.farm.game.farming.FieldService;
import co.mia.farm.game.farming.FieldServiceImpl;
import co.mia.farm.game.farming.InFieldVO;
import co.mia.farm.game.item.AllProductVO;
import co.mia.farm.game.item.ItemService;
import co.mia.farm.game.item.ItemServiceImpl;
import co.mia.farm.game.item.ItemVO;
import co.mia.farm.game.item.event.SecretMoneyService;
import co.mia.farm.game.status.StatusService;

public class ConsolePrintService {
	private Scanner scn = new Scanner(System.in);
	private ItemService is = new ItemServiceImpl();
	public final static int wSize = 25;
	public final static int hSize = 15;
	public static String[][] printGame = new String[hSize][wSize];
	public static int userX = 0;
	public static int userY = hSize - 1;
	private String person = "＆*";
	private FieldService fs = new FieldServiceImpl();
	private StatusService ss = new StatusService();
	private AccountService asi = new AccountServiceImpl();
	private FarmingMenu fm = new FarmingMenu();
	public static int monsterX = (int) (Math.random() * (wSize - 1));
	public static int monsterY = (int) (Math.random() * (hSize - 1));
	public static boolean monsterStayFlag = true;

	public void consolePrintRun() {
		clearScreen();
		titlePrint();
		setFieldArray();
		arrPrint();
		LoginMenu.loginCharacter.toString();
		inputKeyboard();
	}

	public static void setBasicArray() {
		for (int i = 0; i < hSize; i++) {
			for (int j = 0; j < wSize; j++) {
				printGame[i][j] = " ";
			}
		}
	}

	private void setFieldArray() {
		FieldServiceImpl fsi = new FieldServiceImpl();
		List<InFieldVO> myFields = fsi.fieldSelect();

		for (InFieldVO i : myFields) {
			if (i.getItemId() == 0) { // 비어있다
				printGame[i.getFieldY()][i.getFieldX()] = "#";
			} else if (i.getItemId() % 2 == 1) {
				printGame[i.getFieldY()][i.getFieldX()] = "Y";
			} else if (i.getItemId() % 2 == 0) {
				printGame[i.getFieldY()][i.getFieldX()] = "O";
			}
		}
		printGame[userY][userX] = person;
		printGame[monsterY][monsterX] = "@";
		printGame[0][1] = "■"; // 상점 위치
		printGame[0][wSize / 2] = "♥"; // 집

		if (SecretMoneyService.smMoney != -1 && SecretMoneyService.smY != -1 && SecretMoneyService.smX != -1) {
			printGame[SecretMoneyService.smY][SecretMoneyService.smX] = "★";
		}

	}

	private void arrPrint() {
		for (int i = 0; i < wSize - 3; i++) {
			System.out.printf("%2s", "ㅡ");
		}
		System.out.println();

		for (int i = 0; i < hSize; i++) {
			for (int j = 0; j < wSize; j++) {
				if (j == 0) {
					System.out.print("|");
				}
				System.out.printf("%2s", printGame[i][j]);
				if (j == wSize - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
		}
		for (int i = 0; i < wSize - 3; i++) {
			System.out.printf("%2s", "ㅡ");
		}
		System.out.println();
		printGame[userY][userX] = " "; // 프린트하고 유저가 원래 있던 자리 돌려놓기
		printGame[monsterY][monsterX] = " ";
	}

	private void inputKeyboard() {
		System.out.println("( i : 아이템창 열기 || k : 밭 갈기.메우기 || q: 게임 설명 || quit : 로그아웃 )");
		System.out.print("( a : [왼쪽] ← || d : [오른쪽] → || w : [위] ↑ || s : [아래] ↓ ) >>> ");
		String input = scn.next();
		if (input.equalsIgnoreCase("a")) {
			userX--;
			monsterMove();
		} else if (input.equalsIgnoreCase("d")) {
			userX++;
			monsterMove();
		} else if (input.equalsIgnoreCase("w")) {
			userY--;
			monsterMove();
		} else if (input.equalsIgnoreCase("s")) {
			userY++;
			monsterMove();
		} else if (input.equals("quit")) {
			System.out.print("로그아웃 하시겠어요? (y/n) >>> ");
			String ans = scn.next();
			if (ans.equalsIgnoreCase("y")) {
				System.out.printf("%s 아이디를 로그아웃 합니다.\n", LoginMenu.loginAccount.getAccId());
				asi.characterModify();
				LoginMenu.loginCharacter = new CharacterVO();
				LoginMenu.loginAccount = new AccountVO();
				LoginMenu.checkGame = false;
				StaticMenu.waitTime(1500);
				clearScreen();
				return;
			}

		} else if (input.equalsIgnoreCase("i")) { // 아이템창 열기
			// 아이템창 열기
			List<ItemVO> myItems = is.itemAllSelect();
			is.itemsPrint(myItems);
			// 아이템 선택
			if (myItems.size() != 0) {
				System.out.print("(상세보기 : d || 먹기 : e || 종료 : 그 외 버튼) >>> ");
				String menu = scn.next();
				System.out.println();
				if (menu.equalsIgnoreCase("d")) {
					itemDetail(myItems);
				} else if (menu.equalsIgnoreCase("e")) {
					itemEating();
				}
			}

		} else if (input.equalsIgnoreCase("q")) {
			clearScreen();
			System.out.println("==========================================================================");
			System.out.printf("%50s\n\n", "※게임설명※");
			System.out.println("방향키 : (a : ← || d : → || w : ↑ || x : ↓) 을 입력하고 엔터를 치면 이동 가능합니다.\n");
			System.out.println(".o0[ 농작물의 상태는 땅 위로 나타나는 표기로 알 수 있어요! ]");
			System.out.println("[  ] | 공백 상태는 초기의 땅을 나타냅니다.");
			System.out.println("[ # ] | 밭 갈기(k 입력)를 통해서 농작물을 심을 수 있는 상태로 만들 수 있어요.");
			System.out.println("[ Y ] | 농작물이 자라는 중일 때는 Y로 표시됩니다. 이 때 해당 위치로 가면 농작물이 완전히 자랄 때까지 얼마나 남았는지 알 수 있어요.");
			System.out.println("[ O ] | 완전히 자랐을 때 O로 표시됩니다. 수확해보세요!\n");
			System.out.println(".o0[ 씨앗을 구매하고 싶거나 아이템을 판매하고 싶으면 상점으로 가세요! ]");
			System.out.println("[ 상점 : ■ ] | 해당 위치로 가면 상점에서 씨앗을 구매할 수 있습니다.");
			System.out.println("          | 씨앗 및 작물을 판매할 수도 있습니다.");
			System.out.println("          | 레벨이 올라갈 수록 살 수 있는 작물이 많아져요!\n");
			System.out.println(".o0[ 체력이 없어서 자꾸 쓰러진다면 집으로 가보세요! ]");
			System.out.println("[ 집 : ♥ ] | 해당 위치로 가면 소모된 HP를 충전할 수 있습니다.\n");
			System.out.println(".o0[ 과도한 소비로 소지금이 떨어졌을 때 별을 찾으세요! ]");
			System.out.println("[ 비상금 : ★ ] | 해당 위치로 가면 랜덤으로 비상금을 얻을 수 있습니다.\n");
			System.out.println(".o0[ 가지고 있는 아이템의 정보가 궁금하다면 아이템 창을 확인해보세요! ]");
			System.out.println("[ 아이템창 : i ] | 아이템 창의 아이템을 먹어서 체력을 채울 수 있습니다.");
			System.out.println("              | 아이템 창 내의 아이템에 대해서 상세 정보를 볼 수 있습니다.\n");
			System.out.println(".o0[ 갈았던 땅을 다시 메우고 싶다면 해당 위치로 가서 k를 입력해보세요! ]");
			System.out.println("[ 땅 메우기 : k ] | 메우고 싶은 땅 위에서 k를 입력하면 메웠던 땅이 사라집니다.\n");
			System.out.println("==========================================================================");
			System.out.println("다 읽으셨다면 아무키나 입력해 빠져나가세요 >>> ");
			scn.next();

		} else if (input.equalsIgnoreCase("k")) {
			InFieldVO fvo = fm.checkMyField();
			if (fvo.getFieldX() == -1 && fvo.getFieldY() == -1 && fvo.getItemId() == -1) { // 땅이 없으면 땅을 파고
				int n = fs.fieldAddHere();
				if (n == 1) {
					System.out.println("밭 갈기에 성공했습니다!");
					// 체력 줄기
					ss.descHp(10);
				}
			} else { // 땅이 있으면 메우기
				System.out.print("밭을 메울까요? (y/n) >>> ");
				String ans = scn.next();
				boolean flag = false;

				if (ans.equalsIgnoreCase("y")) {
					AllProductVO apv = is.itemGetproduct(fvo.getItemId());
					// 작물이 있으면 수확하고 메우기
					if (fvo.getItemId() % 2 == 0 && fvo.getItemId() >= 100) {
						System.out.print("작물이 존재합니다. 수확하고 밭을 메울까요? (y/n) >>> ");
						ans = scn.next();
						if (ans.equalsIgnoreCase("y")) {
							ItemVO myItem = is.itemOneSelect(apv.getItemId()); // 현재 필드에 심겨진 작물이 내 아이템 창에 있는지 확인
							if (myItem.getItemID() == -1 && myItem.getItemCnt() == -1) { // 가방에 없다
								is.itemInsert(fvo.getItemId(), 1);
							} else { // 가방에 있다
								is.itemUpdateCnt(myItem.getItemID(), myItem.getItemCnt() + 1);
							}
							System.out.printf("%s %d개를 수확했습니다!\n", apv.getItemName(), 1);
							StaticMenu.waitTime(1000);
							fs.fieldUpdateZero(fvo);
							// hp 감소
							ss.descHp(apv.getCHp());
							// 경험치 증가
							ss.incExp(apv.getCExp());
							// 캐릭터 정보 db에 저장
							asi.characterModify();
							flag = true;
						} else {
							System.out.println("밭 메우기를 취소했습니다.");
						}
					} else if (fvo.getItemId() % 2 == 1) { // 씨앗 상태면 없애고 메우기
						System.out.printf("밭에 심겨진 %s(이)가 덜 자랐습니다. 기다리지 않고 밭을 메울까요? (y/n) >>> ", apv.getItemName());
						ans = scn.next();
						if (ans.equals("y")) {
							System.out.printf("%s(을)를 버렸습니다...\n", apv.getItemName());
							StaticMenu.waitTime(1000);
							flag = true;
						} else {
							System.out.println("밭 메우기를 취소했습니다.");
						}
					} else {
						System.out.println("밭을 메울게요.");
						flag = true;
					}

					if (flag) {
						for (int i = 0; i < 2; i++) {
							System.out.println("밭 메우는중...");
							StaticMenu.waitTime(1000);
						}
						fs.fieldDrop(fvo);
						System.out.println("밭 메우기 완료!");
					}
					StaticMenu.waitTime(1000);

				}

			}
		}

		else {
			System.out.println("다시 입력");
		}

		// x,y가 범위를 벗어났을 때
		if (userX < 0) { // 왼쪽 오버
			userX++;
		} else if (userX > wSize - 1) { // 오른쪽 오버
			userX--;
		} else if (userY < 0) { // 위쪽 오버
			userY++;
		} else if (userY > hSize - 1) {
			userY--;
		}
	}

	private void itemEating() {
		int n = -1;
		List<ItemVO> myCrops = is.itemKindSelect(0);
		is.itemsPrint(myCrops);
		if (myCrops.size() == 0) {
			System.out.println("먹을만한게 없습니다...");
			return;
		}
		System.out.print("먹을 아이템을 선택해주세요 >>> ");
		try {
			n = scn.nextInt();
		} catch (Exception e) {
			System.out.println("숫자를 선택해주세요!");
			scn.nextLine();
		}
		if (n > 0 && n < myCrops.size() + 1) {
			n--;
			CharacterVO ch = LoginMenu.loginCharacter;
			for (int i = 0; i < 2; i++) {
				System.out.println("먹는중...");
				StaticMenu.waitTime(1000);
			}
			AllProductVO cropInfo = is.itemGetproduct(myCrops.get(n).getItemID());
			ch.setUserHp(ch.getUserHp() + cropInfo.getCHp()); // 먹는거에 따라서 hp 달라지는거
			asi.characterModify();
			is.itemUpdateCnt(myCrops.get(n).getItemID(), myCrops.get(n).getItemCnt() - 1);
			if (myCrops.get(n).getItemCnt() <= 0) {
				is.itemDelete(myCrops.get(n));
			}
			System.out.println("체력이 회복됐습니다!");
			StaticMenu.waitTime(1000);
		}

	}

	public static void clearScreen() {
		for (int i = 0; i < 40; i++) {
			System.out.println("");
		}
	}

	private void titlePrint() {
		for (int i = 0; i < wSize; i++) {
			System.out.printf("%2s", "=");
		}
		System.out.println();

		for (int i = 0; i < wSize / 2 - 3; i++) {
			System.out.printf("%2s", " ");
		}
		System.out.printf("◎ %3s의 %s농장 ◎", LoginMenu.loginCharacter.getUserNickname(),
				LoginMenu.loginCharacter.getFarmName());

		System.out.println();
		for (int i = 0; i < wSize; i++) {
			System.out.printf("%2s", "=");
		}
		System.out.println();
		System.out.println();

	}

	private void itemDetail(List<ItemVO> myItems) {
		int n = -1;
		System.out.print("상세보기 할 아이템을 선택해주세요 >>> ");
		try {
			n = scn.nextInt();
		} catch (Exception e) {
			System.out.println("숫자를 선택해주세요!");
			scn.nextLine();
		}
		if (n > 0 && n < myItems.size() + 1) {
			n--;
			AllProductVO itemInfo = is.itemGetproduct(myItems.get(n).getItemID());
			System.out.println("=================================");
			System.out.printf("%s의 상세정보 입니다.\n", itemInfo.getItemName());
			itemInfo.toStringDetail();
			System.out.println("종료하려면 아무 버튼이나 눌러주세요.");
			System.out.println("=================================");
			scn.next();
		}
	}

	public static void exitIfCancel() { // userX나 userY의 위치에서 벗어나기
		if (userY + 1 < hSize) {
			userY++;
		} else if (userX + 1 < wSize) {
			userX++;
		} else if (userX - 1 >= 0) {
			userX--;
		} else if (userY - 1 >= 0) {
			userY--;
		}
	}

	private void monsterMove() {
		while (monsterStayFlag) {
			System.out.println("멧돼지 옮겨다님");
			int x = monsterX;
			int y = monsterY;

			// 0 : 왼쪽
			// 1 : 오른쪽
			// 2 : 위쪽
			// 3 : 아래쪽
			int rnd = (int) (Math.random() * 4);
			if (rnd == 0) {
				x--;
			} else if (rnd == 1) {
				x++;
			} else if (rnd == 2) {
				y--;
			} else if (rnd == 3) {
				y++;
			}

			if (x < 0) { // 왼쪽 오버
				x++;
			} else if (x > wSize - 1) { // 오른쪽 오버
				x--;
			} else if (y < 0) { // 위쪽 오버
				y++;
			} else if (y > hSize - 1) {
				y--;
			}

			if (monsterX != x || monsterY != y) { // 하나라도 다르면
				monsterX = x;
				monsterY = y;
				break;
			}
		}

	}

}
