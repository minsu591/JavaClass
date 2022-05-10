package co.mia.farm.game.farming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.mia.farm.LoginMenu;
import co.mia.farm.StaticMenu;
import co.mia.farm.game.item.AllProductVO;
import co.mia.farm.game.item.ItemServiceImpl;
import co.mia.farm.game.item.ItemVO;
import co.mia.farm.game.print.ConsolePrintService;

public class FarmingMenu { // 농작 메뉴
	private Scanner scn = new Scanner(System.in);
	private String ans;
	private List<ItemVO> userItems = new ArrayList<ItemVO>();
	private AllProductVO sysItem = new AllProductVO();
	private ItemServiceImpl is = new ItemServiceImpl();
	private FieldServiceImpl fsi = new FieldServiceImpl();

	public void harvesting(InFieldVO myField) {
		AllProductVO apv = is.itemGetproduct(myField.getItemId()); //현재 필드에 심겨진 정보 가져와서 변환
		System.out.printf("%s(이)가 다 자랐습니다!\n", apv.getItemName()); //현재 필드에 심겨진 작물 이름
		System.out.print("농작물을 수확하시겠어요? (y/n) >>> ");
		try {
			ans = scn.next();
		} catch (Exception e) {
			System.out.println("입력이 잘못됐습니다. 다시 입력바랍니다...");
			scn.next();
		}
		if (ans.equalsIgnoreCase("y")) {
			int n;
			ItemVO myItem = is.itemOneSelect(apv.getItemId()); //현재 필드에 심겨진 작물이 내 아이템 창에 있는지 확인
			if (myItem.getItemID() == -1 && myItem.getItemCnt() == -1) { // 가방에 없다
				n = is.itemInsert(myField.getItemId(), 1);
			} else { // 가방에 있다
				n = is.itemUpdateCnt(myItem.getItemID(), myItem.getItemCnt() + 1);
			}
			System.out.printf("%s %d개를 수확했습니다!", apv.getItemName(), 1);
			StaticMenu.waitTime(1000);
			fsi.fieldUpdateZero(myField);
			//농작물 수확하면 exp 증가
			LoginMenu.loginCharacter.setUserExp(LoginMenu.loginCharacter.getUserExp()+apv.getCExp());
		}

	}

	public InFieldVO checkMyField() { // 내 위치가 농장필드에 있는지 확인 => 있으면 농장필드 정보 반환
		InFieldVO myField = new InFieldVO(-1, -1, -1);
		FieldServiceImpl fsi = new FieldServiceImpl();
		List<InFieldVO> myfields = fsi.fieldSelect();
		for (int i = 0; i < myfields.size(); i++) {
			if (ConsolePrintService.userX == myfields.get(i).getFieldX()
					&& ConsolePrintService.userY == myfields.get(i).getFieldY()) {
				myField = myfields.get(i);
				break;
			}
		}
		return myField;
	}

	private void farmingThread(int sec) {
		InFieldVO myField = checkMyField(); // myField 업로드
		Runnable mf = new MultiFarmingThread(sec, myField);
		Thread th = new Thread(mf);
		th.start();
	}

	public void farming(InFieldVO myField) {
		System.out.print("농작물을 심으시겠어요? (y/n) >>> ");
		try {
			ans = scn.next();
		} catch (Exception e) {
			System.out.println("입력이 잘못됐습니다. 다시 입력바랍니다...");
			scn.next();
		}
		if (ans.equalsIgnoreCase("y")) {
			userItems = is.itemSeedSelect(); // 씨앗 목록 가져오기
			is.itemsPrint(userItems); // 아이템창 출력
			if (userItems.size() != 0) {
				System.out.print("심을 농작물의 번호를 입력해주세요 >>>");
				int ans = scn.nextInt();
				if (ans > 0 && ans < userItems.size() + 1) {
					ans--;
					ItemVO myItem = userItems.get(ans);
					sysItem = is.itemGetproduct(myItem.getItemID());
					System.out.printf("%s을(를) 심을게요!\n", sysItem.getItemName());

					if (myItem.getItemCnt() <= 1) {
						is.itemDelete(myItem);
					} else {
						is.itemUpdateCnt(userItems.get(ans).getItemID(), myItem.getItemCnt() - 1);
					}
					for (int i = 0; i < 3; i++) {
						System.out.println("심는중... _〆(ﾟ▽ﾟ*)");
						StaticMenu.waitTime(1000);
					}
					fsi.fieldUpdate(myField, myItem.getItemID()); // 씨앗 심은거 표시
					System.out.println("심기 완료!");
					StaticMenu.waitTime(1000);
					farmingThread(sysItem.getCTime());
					
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
