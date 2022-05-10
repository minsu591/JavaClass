package co.mia.farm.game.print;

import java.util.List;
import java.util.Scanner;

import co.mia.farm.GameMenu;
import co.mia.farm.LoginMenu;
import co.mia.farm.StaticMenu;
import co.mia.farm.game.farming.FieldService;
import co.mia.farm.game.farming.FieldServiceImpl;
import co.mia.farm.game.farming.InFieldVO;
import co.mia.farm.game.item.ItemService;
import co.mia.farm.game.item.ItemServiceImpl;
import co.mia.farm.game.item.ItemVO;
import co.mia.farm.game.shop.ShopMenu;

public class ConsolePrintService {
	private Scanner scn = new Scanner(System.in);
	private ItemService is = new ItemServiceImpl();
	public final static int wSize = 25;
	public final static int hSize = 15;
	public static String[][] printGame = new String[hSize][wSize];
	public static int userX = 0;
	public static int userY = 0;
	private String person = "(º-º)";
	private ShopMenu sm = new ShopMenu();
	private FieldService fs = new FieldServiceImpl();
	

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
		printGame[0][1] = "■"; //상점 위치
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
		System.out.print("(a : 왼쪽 / d: 오른쪽 / w : 위 / x : 아래 / i : 아이템창 열기 / k : 땅 파기 / quit : 종료) >>> ");
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
			GameMenu.checkLogin = false;
		}else if(input.equals("i")) { //아이템창 열기
			//아이템창 열기
			List<ItemVO> myItems = is.itemAllSelect();
			is.itemsPrint(myItems);
			System.out.print("종료하고 싶으면 아무 버튼이나 누르세요 >>> ");
			scn.next();
		}else if(input.equals("k")) {
			int n =fs.fieldAddHere();
			if(n==1) {
				System.out.println("성공적으로 땅을 팠습니다!");
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

}
