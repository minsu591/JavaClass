package co.mia.farm.game.shop;

import java.util.List;
import java.util.Scanner;

import co.mia.farm.LoginMenu;
import co.mia.farm.StaticMenu;
import co.mia.farm.account.AccountService;
import co.mia.farm.account.AccountServiceImpl;
import co.mia.farm.game.item.AllProductVO;
import co.mia.farm.game.item.ItemServiceImpl;
import co.mia.farm.game.item.ItemVO;
import co.mia.farm.game.print.ConsolePrintService;

public class ShopMenu {
	private ItemServiceImpl is = new ItemServiceImpl();
	private List<AllProductVO> shopItems;
	private Scanner scn = new Scanner(System.in);
	private AccountService asi = new AccountServiceImpl();

	public void run() {

		while (true) {
			ConsolePrintService.clearScreen();
			System.out.println("[상점]");
			System.out.println("물건을 구매하러 오셨나요? 편하게 둘러보세요!");
			System.out.println();
			System.out.printf("소지금 : %d별\n", LoginMenu.loginCharacter.getUserMoney());
			System.out.print("[1. 구매 | 2. 판매 | 3. 나가기] >>> ");
			int menu = -1;
			try {
				menu = scn.nextInt();
				scn.nextLine();
			} catch (Exception e) {
				System.out.println("잘못된 번호 선택입니다...");
				scn.nextLine();
				StaticMenu.waitTime(1000);
			}
			if (menu == 1) { // 구매
				shopItems = is.productKindSelect(1);
				boolean flag = is.productPrint(shopItems, "상점용 씨앗");
				if (flag) {
					asi.characterModify();
					System.out.print("구매할 물건의 번호를 입력해주세요 >>> ");
					int ans = -1;
					try {
						ans = scn.nextInt();
						scn.nextLine();
					} catch (Exception e) {
						System.out.println("숫자만 입력해주세요...");
						scn.nextLine();
						StaticMenu.waitTime(1000);
					}
					if (ans > 0 && ans < shopItems.size() + 1) {
						ans--;
						AllProductVO purItem = shopItems.get(ans);
						System.out.printf("%s를 몇 개 구매하시겠어요? (취소 : 0) >>> ", purItem.getItemName());
						int cnt = 0;
						try {
							cnt = scn.nextInt();
							scn.nextLine();
						} catch (Exception e) {
							System.out.println("잘못된 번호 선택입니다...");
							scn.nextLine();
							StaticMenu.waitTime(1000);
						}
						if (cnt < 0) {
							System.out.println("구매를 취소합니다.");
							StaticMenu.waitTime(1000);
						} else {
							int purMoney = cnt * purItem.getACost();
							System.out.printf("총 %d별 입니다.\n", purMoney);
							StaticMenu.waitTime(1000);
							if (LoginMenu.loginCharacter.getUserMoney() >= purMoney) {
								LoginMenu.loginCharacter
										.setUserMoney(LoginMenu.loginCharacter.getUserMoney() - purMoney); // 유저 돈 뺏기

								ItemVO myItem = is.itemOneSelect(purItem.getItemId());

								if (myItem.getItemID() == -1 && myItem.getItemCnt() == -1) { // 가방에 없다
									is.itemInsert(purItem.getItemId(), cnt);
								} else { // 가방에 있다
									is.itemUpdateCnt(myItem.getItemID(), myItem.getItemCnt() + cnt);
								}
								System.out.println("구매 완료했습니다! 즐거운 농사되세요!");
								StaticMenu.waitTime(1000);
							} else {
								System.out.println("잔액이 부족합니다...");
								StaticMenu.waitTime(1000);
							}
						}

					} else {
						System.out.println("존재하지 않는 물건입니다.");
						StaticMenu.waitTime(1000);
					}
				}
			} else if (menu == 3) {
				System.out.println("다음에 또 찾아주세요!");
				StaticMenu.waitTime(1000);
				break;
			} else if (menu == 2) {// 판매
				List<ItemVO> myItems = is.itemAllSelect();
				is.itemsPrint(myItems, false);
				System.out.print("무엇을 판매하시겠어요? >>> ");
				int ans = -1;
				try {
					ans = scn.nextInt();
					scn.nextLine();
				} catch (Exception e) {
					System.out.println("숫자만 입력해주세요...");
					scn.nextLine();
					StaticMenu.waitTime(1000);
				}
				if (ans > 0 && ans < myItems.size() + 1) {
					ans--;
					ItemVO item = myItems.get(ans);
					AllProductVO itemDetail = is.itemGetproduct(item.getItemID());
					System.out.printf("%s를 몇 개 판매하시겠어요? (취소 : 0) >>> ", itemDetail.getItemName());
					int cnt = -1;
					try {
						cnt = scn.nextInt();
						scn.nextLine();
					} catch (Exception e) {
						System.out.println("숫자만 입력해주세요!");
						scn.nextLine();
					}

					if (cnt > 0 && cnt <= item.getItemCnt()) {
						int addMoney = cnt * itemDetail.getACost();
						System.out.printf("총 %d별 입니다!\n", addMoney);

						if (item.getItemCnt() - cnt <= 0) { // 아이템 수 변경
							is.itemDelete(item);
						} else {
							is.itemUpdateCnt(item.getItemID(), item.getItemCnt() - cnt);

						}

						LoginMenu.loginCharacter.setUserMoney(LoginMenu.loginCharacter.getUserMoney() + addMoney); // 캐릭터
																													// 돈
																													// 더
																													// 주기
						System.out.println("판매 완료했습니다.");
						StaticMenu.waitTime(1000);
						// 삭제

					} else if (cnt == 0) {
						System.out.println("판매를 취소합니다.");
						StaticMenu.waitTime(1000);
					} else {
						System.out.println("물건 수량을 다시 확인해주세요.");
						StaticMenu.waitTime(1000);
					}
				} else {
					System.out.println("존재하지 않는 물건입니다.");
					StaticMenu.waitTime(1000);
				}
			}
		}
	}
}
