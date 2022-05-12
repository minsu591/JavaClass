package co.mia.farm.game.item.event;

import java.util.List;
import java.util.Scanner;

import co.mia.farm.LoginMenu;
import co.mia.farm.StaticMenu;
import co.mia.farm.game.farming.FieldService;
import co.mia.farm.game.farming.FieldServiceImpl;
import co.mia.farm.game.farming.ForFarmingThread;
import co.mia.farm.game.item.AllProductVO;
import co.mia.farm.game.item.ItemService;
import co.mia.farm.game.item.ItemServiceImpl;
import co.mia.farm.game.item.ItemVO;
import co.mia.farm.game.print.ConsolePrintService;

public class TutorialService {
	private Scanner scn = new Scanner(System.in);
	private int x;
	private int y;
	private String person = "&*";
	private String[][] printField = new String[3][5];
	private ItemService is = new ItemServiceImpl();
	private FieldService fs = new FieldServiceImpl();
	private int fX = -1;
	private int fY = -1;
	private int fNum = 0;

	public void run() {
		System.out.println("여러분들의 농장 생활을 도와드리기 위해서 튜토리얼을 진행할게요!");
		System.out.print("원하신다면 아무키나, 원하지 않으신다면 skip을 입력해주세요! >>> ");
		String ans = scn.next();
		if (ans.equalsIgnoreCase("skip")) {
			System.out.println("튜토리얼 진행을 원하지 않으시는군요! 바로 농장으로 보내드릴게요!");
		} else {
			tutorial();
		}
	}

	private void tutorial() {
		setting();
		arrPrintInTutorial();

		System.out.println("지금 보이는 화면의 >> &* << 이 여러분입니다.");
		StaticMenu.waitTime(1000);
		System.out.println("각 키를 입력하면 상하좌우로 움직일 수 있어요.");
		StaticMenu.waitTime(1000);
		System.out.println("한 번 여기저기 움직여보세요!");
		int c = 0;
		while (c < 7) {
			System.out.print("(a : 왼쪽 || d : 오른쪽 || w : 위 || s : 아래) >>> ");
			inputKeyBoard();
			setting();
			arrPrintInTutorial();
			c++;
		}
		System.out.println("충분히 연습하셨나요?");
		StaticMenu.waitTime(1000);
		is.itemInsert(101, 1); //감자 하나 넣어주기
		while (true) {
			setting();
			arrPrintInTutorial();
			System.out.println("아이템 창에 감자 씨앗을 하나 넣어드렸어요! 확인해보세요!");
			scn.next();
			StaticMenu.waitTime(1000);
			System.out.println("i를 누르면 아이템 창을 열 수 있습니다.");
			System.out.print("(a : 왼쪽 || d : 오른쪽 || w : 위 || s : 아래 || i : 아이템창 열기) >>> ");
			String input = inputKeyBoard();
			if (input.equals("i")) {
				break;
			}
		}
		System.out.println("이제 밭을 갈아볼게요! k를 눌러보세요!");
		StaticMenu.waitTime(2000);
		while (true) {
			setting();
			arrPrintInTutorial();
			System.out.println("이제 밭을 갈아볼게요! k를 눌러보세요!");
			System.out.print("(a : 왼쪽 || d : 오른쪽 || w : 위 || s : 아래 || i : 아이템창 열기 || k : 밭 갈기.메우기) >>> ");
			String input = inputKeyBoard();
			if (input.equals("k")) {
				break;
			}
		}
		if ((x + 1) <= 4) {
			x++;
		} else if ((x - 1) >= 0) {
			x--;
		} else if ((y + 1) <= 2) {
			y++;
		} else if ((y - 1) >= 0) {
			y--;
		}

		setting();
		arrPrintInTutorial();
		System.out.println("해당 위치로 가서 농작물을 심어보세요.");
		StaticMenu.waitTime(1000);
		AllProductVO sysItem = is.itemGetproduct(101);
		while (true) {
			setting();
			arrPrintInTutorial();
			System.out.println("해당 위치로 가서 농작물을 심어보세요.");
			System.out.print("(a : 왼쪽 || d : 오른쪽 || w : 위 || s : 아래 || i : 아이템창 열기 || k : 밭 갈기.메우기) >>> ");
			inputKeyBoard();
			if (x == fX && y == fY) {
				System.out.println("농작물을 심겠습니까? (y/n) >>> ");
				String ans = scn.next();
				if (ans.equalsIgnoreCase("y")) {
					List<ItemVO> userItems = is.itemKindSelect(1); // 씨앗 목록 가져오기
					is.itemsPrint(userItems); // 아이템창 출력
					if (userItems.size() != 0) {
						System.out.print("심을 농작물의 번호를 입력해주세요 >>>");
						int a = scn.nextInt();
						if (a > 0 && a < userItems.size() + 1) {
							a--;
							ItemVO myItem = userItems.get(a);
							System.out.printf("%s을(를) 심을게요!\n", sysItem.getItemName());
							is.itemDelete(myItem);
							System.out.println("심는중... _〆(ﾟ▽ﾟ*)");
							StaticMenu.waitTime(1000);
							System.out.println("물 뿌리는 중... ♪(´ε｀*)");
							StaticMenu.waitTime(1000);
							System.out.println("예뻐하는 중... (❁´▽`❁)*✲ﾟ*");
							StaticMenu.waitTime(1000);

							fNum = 101; // 씨앗 심은거 표시
							System.out.println("심기 완료!");
							StaticMenu.waitTime(1000);
							ForFarmingThread fft = new ForFarmingThread();
							fft.farmingThread(sysItem.getCTime());
							break;
						} else {
							System.out.println("선택한 농작물이 존재하지 않습니다. 다시 선택해주세요...");
							StaticMenu.waitTime(1000);
						}
					}
				} else {
					System.out.println("농작물 심기를 취소했습니다.");
					StaticMenu.waitTime(1000);
				}
			}
		}
		if ((x + 1) <= 4) {
			x++;
		} else if ((x - 1) >= 0) {
			x--;
		} else if ((y + 1) <= 2) {
			y++;
		} else if ((y - 1) >= 0) {
			y--;
		}

		setting();
		arrPrintInTutorial();
		StaticMenu.waitTime(1000);
		fNum = 100;
		while (true) {
			setting();
			arrPrintInTutorial();
			System.out.println("동일한 자리로 가서 씨앗을 수확해보세요");
			StaticMenu.waitTime(1000);
			System.out.print("(a : 왼쪽 || d : 오른쪽 || w : 위 || s : 아래 || i : 아이템창 열기 || k : 밭 갈기.메우기) >>> ");
			inputKeyBoard();
			if (fNum % 2 == 0 && fNum > 0 && x == fX && y == fY) {
				System.out.printf("%s(이)가 다 자랐습니다!\n", sysItem.getItemName());
				System.out.print("농작물을 수확하시겠어요? (y/n) >>> ");
				String b = " ";
				try {
					b = scn.next();
				} catch (Exception e) {
					System.out.println("입력이 잘못됐습니다. 다시 입력바랍니다...");
					scn.next();
				}
				if (b.equalsIgnoreCase("y")) {
					System.out.println("수확을 완료했습니다!");
					is.itemInsert(100, 1);
					StaticMenu.waitTime(1000);
					break;
				} else {
					System.out.println("수확을 취소했습니다.");
					StaticMenu.waitTime(1000);
					ConsolePrintService.exitIfCancel();
				}
			}
		}
		clearConsole();
		System.out.println("튜토리얼이 끝났습니다!");
		StaticMenu.waitTime(2000);
		System.out.println("방금 수확한 감자는 아이템창에 넣어놨어요!");
		StaticMenu.waitTime(2000);
		System.out.println("궁금하신 사항이 있다면 q를 눌러 게임 설명을 참고하세요.");
		StaticMenu.waitTime(2000);
		System.out.print("아무키나 눌러 게임을 진행하세요 >>> ");
		scn.next();

	}

	private String inputKeyBoard() {
		String input = scn.next();
		if (input.equals("a")) {
			x--;
		} else if (input.equals("d")) {
			x++;
		} else if (input.equals("w")) {
			y--;
		} else if (input.equals("s")) {
			y++;
		} else if (input.equals("i")) { // 아이템창 열기
			// 아이템창 열기
			List<ItemVO> myItems = is.itemAllSelect();
			is.itemsPrint(myItems);
			System.out.print("종료하려면 아무키나 누르세요 >>> ");
			scn.next();

		} else if (input.equals("k")) {
			fX = x;
			fY = y;
			System.out.println("밭 갈기에 성공했습니다.");
			StaticMenu.waitTime(1000);
		}

		if (x < 0) { // 왼쪽 오버
			x++;
		} else if (x > 4) { // 오른쪽 오버
			x--;
		} else if (y < 0) { // 위쪽 오버
			y++;
		} else if (y > 1) {
			y--;
		}
		return input;
	}

	private void setting() {
		for (int i = 0; i < printField.length; i++) {
			for (int j = 0; j < printField[i].length; j++) {
				printField[i][j] = " ";
			}
		}
		if (fX != -1 && fY != -1) {
			printField[fY][fX] = "#";
		}
		printField[y][x] = person;
	}

	private void clearConsole() {
		for (int i = 0; i < 40; i++) {
			System.out.println(" ");
		}
	}

	private void arrPrintInTutorial() {
		clearConsole();
		System.out.println("==============");
		System.out.println("★  Tutorial  ★");
		System.out.println("==============");
		System.out.println();

		for (int i = 0; i < 5; i++) {
			System.out.printf("%2s", "ㅡ");
		}
		System.out.println();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				if (j == 0) {
					System.out.print("|");
				}
				System.out.printf("%2s", printField[i][j]);
				if (j == 5 - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
		}
		for (int i = 0; i < 5; i++) {
			System.out.printf("%2s", "ㅡ");
		}
		System.out.println();
		printField[y][x] = " "; // 프린트하고 유저가 원래 있던 자리 돌려놓기
	}

}
