package co.mia.farm;

import java.util.List;
import java.util.Scanner;

import co.mia.farm.game.item.ItemServiceImpl;
import co.mia.farm.game.item.ItemVO;
import co.mia.farm.game.print.ConsolePrintService;

public class GameMenu {
	private ConsolePrintService cps = new ConsolePrintService();
	public static boolean checkLogin = false;
	private Scanner scn = new Scanner(System.in);
	private String ans;
	private ItemServiceImpl is = new ItemServiceImpl();
	private List<ItemVO> userItems;

	private List<ItemVO> itemsPrint() { // 프린트할 때 1번부터 시작하게
		userItems = is.itemSelect();
		System.out.printf("======= %s의 아이템 List =======\n", LoginMenu.loginCharacter.getUserNickname());
		for (int i = 0; i < userItems.size(); i++) {
			System.out.printf("%s번, [%s : %d개]\n", i + 1, userItems.get(i).getItemName(),
					userItems.get(i).getItemCnt());
		}
		System.out.println("=================================");
		return userItems;
	}

	public void run() {
//		if(!StringUtils.isEmpty(LoginMenu.loginAccount.getAccId())){
//			checkLogin = true;
//        }
		while (true) {
			cps.consolePrintRun();

			// 농작물 심을 수 있는 위치로 오면
			if (ConsolePrintService.userX == 3 && ConsolePrintService.userY == 0) {
				System.out.print("농작물을 심으시겠어요? (y/n) >>> ");
				try {
					ans = scn.next();
				} catch (Exception e) {
					System.out.println("입력이 잘못됐습니다. 다시 입력바랍니다...");
					scn.next();
				}
				if (ans.equalsIgnoreCase("y")) {
					itemsPrint(); // 아이템창에 농작물 프린트
					System.out.print("심을 농작물의 번호를 입력해주세요 >>>");
					int ans = scn.nextInt();
					if (ans > 0 && ans < userItems.size() + 1) {
						System.out.printf("%s을(를) 심을게요!", userItems.get(ans).getItemName());
						is.itemUpdate(userItems.get(ans).getItemName(), userItems.get(ans).getItemCnt()-1);
						

						// 해당 위치에 농작물 심기 + multiThread진행
						// 아이템 창에서 농작물 하나 빼기
						System.out.println("심기 완료!");
					}else {
						System.out.println("선택한 농작물이 존재하지 않습니다. 다시 선택해주세요...");
					}

					
					break;
				} else {
					System.out.println("농작물 심기를 취소했습니다.");
					scn.next();
				}

			}

		}

	}

}
