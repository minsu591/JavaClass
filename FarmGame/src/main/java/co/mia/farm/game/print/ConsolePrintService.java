package co.mia.farm.game.print;

import java.util.List;
import java.util.Scanner;

import co.mia.farm.GameMenu;
import co.mia.farm.LoginMenu;
import co.mia.farm.StaticMenu;
import co.mia.farm.account.AccountService;
import co.mia.farm.account.AccountServiceImpl;
import co.mia.farm.account.AccountVO;
import co.mia.farm.account.CharacterVO;
import co.mia.farm.game.farming.FieldService;
import co.mia.farm.game.farming.FieldServiceImpl;
import co.mia.farm.game.farming.InFieldVO;
import co.mia.farm.game.item.AllProductVO;
import co.mia.farm.game.item.ItemService;
import co.mia.farm.game.item.ItemServiceImpl;
import co.mia.farm.game.item.ItemVO;
import co.mia.farm.game.item.event.SecretMoneyService;
import co.mia.farm.game.shop.ShopMenu;
import co.mia.farm.game.status.StatusService;

public class ConsolePrintService {
	private Scanner scn = new Scanner(System.in);
	private ItemService is = new ItemServiceImpl();
	public final static int wSize = 25;
	public final static int hSize = 15;
	public static String[][] printGame = new String[hSize][wSize];
	public static int userX = 0;
	public static int userY = 0;
	private String person = "＆*";
	private ShopMenu sm = new ShopMenu();
	private FieldService fs = new FieldServiceImpl();
	private StatusService ss = new StatusService();
	private AccountService asi = new AccountServiceImpl();
	private SecretMoneyService sms;

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
				printGame[i][j] = "□";
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
		printGame[0][1] = "■"; // 상점 위치
		printGame[0][wSize / 2] = "♥";
		if(SecretMoneyService.smMoney != -1 && SecretMoneyService.smY != -1 && SecretMoneyService.smX != -1) {
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
		printGame[userY][userX] = "□";
	}

	private void inputKeyboard() {
		System.out.print("(a : 왼쪽 / d: 오른쪽 / w : 위 / x : 아래 / i : 아이템창 열기 / k : 땅 파기 / quit : 로그아웃) >>> ");
		String input = scn.next();
		if (input.equals("a")) {
			userX--;
		} else if (input.equals("d")) {
			userX++;
		} else if (input.equals("w")) {
			userY--;
		} else if (input.equals("x")) {
			userY++;
		} else if (input.equals("quit")) {
			System.out.print("로그아웃 하시겠어요? (y/n) >>> ");
			String ans = scn.next();
			if(ans.equalsIgnoreCase("y")) {
				System.out.printf("%s 아이디를 로그아웃 합니다.",LoginMenu.loginAccount.getAccId());
				asi.characterModify();
				LoginMenu.loginCharacter = new CharacterVO();
				LoginMenu.loginAccount = new AccountVO();
				LoginMenu.checkGame = false;
				return;
			}
			
		} else if (input.equals("i")) { // 아이템창 열기
			// 아이템창 열기
			List<ItemVO> myItems = is.itemAllSelect();
			is.itemsPrint(myItems);
			// 아이템 선택
			System.out.print("(상세보기 : d || 먹기 : e || 종료 : 그 외 버튼) >>> ");
			String menu = scn.next();
			System.out.println();
			if (menu.equals("d")) {
				itemDetail(myItems);
			} else if (menu.equals("e")) {
				itemEating();
			}

		} else if (input.equals("k")) {
			int n = fs.fieldAddHere();
			if (n == 1) {
				System.out.println("성공적으로 땅을 팠습니다!");
				// 체력 줄기
				ss.descHp(10);
				asi.characterModify();
				StaticMenu.waitTime(1000);
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
			AllProductVO itemInfo = is.itemGetproduct(myCrops.get(n).getItemID());
			for (int i = 0; i < 2; i++) {
				System.out.println("먹는중...");
				StaticMenu.waitTime(1000);
			}
			ch.setUserHp(ch.getUserHp() + 10); //먹는거에 따라서 hp 달라지는거
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
	
	public static void exitIfCancel() { //userX나 userY의 위치에서 벗어나기
		if(userY+1 < hSize) {
			userY++;
		}else if(userX+1 < wSize) {
			userX++;
		}else if(userX-1 >= 0) {
			userX--;
		}else if(userY-1 >= 0) {
			userY--;
		}
	}

}
