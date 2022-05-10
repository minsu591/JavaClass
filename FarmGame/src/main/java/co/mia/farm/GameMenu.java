package co.mia.farm;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.mia.farm.game.farming.FarmingMenu;
import co.mia.farm.game.farming.FieldService;
import co.mia.farm.game.farming.FieldServiceImpl;
import co.mia.farm.game.farming.InFieldVO;
import co.mia.farm.game.print.ConsolePrintService;
import co.mia.farm.game.shop.ShopMenu;

public class GameMenu {
	private ConsolePrintService cps = new ConsolePrintService();
	private FieldService fs = new FieldServiceImpl();
	public static boolean checkLogin = false;
	private FarmingMenu fm = new FarmingMenu();
	private InFieldVO myField = new InFieldVO();
	private ShopMenu sm = new ShopMenu();

	public void run() {
		// 기본 설정
		ConsolePrintService.setBasicArray(); // 필드 ' '으로 구성
		if (!StringUtils.isEmpty(LoginMenu.loginAccount.getAccId())) {
			checkLogin = true;
		}

		List<InFieldVO> myFields = fs.fieldSelect();
		for (InFieldVO i : myFields) {
			if (i.getItemId() != 0) {
				System.out.printf("[%d,%d] 좌표의 농작물이 썩었습니다......\n", i.getFieldX(), i.getFieldY());
				fs.fieldUpdateZero(i);
				StaticMenu.waitTime(1000);
			}
		}
		while (checkLogin) { // checkLogin으로 변경하기
			cps.consolePrintRun();

			// 이동
			myField = fm.checkMyField();
			if(ConsolePrintService.userY == 0 && ConsolePrintService.userX == 1) { //내 위치가 상점
				sm.run();
			}
			else if (myField.getFieldX() != -1 && myField.getFieldY() != -1 && myField.getItemId() != -1) { // 내 위치가 농장필드가 아니면
				if (myField.getItemId() == 0) { // 농장이 비어있음
					fm.farming(myField);
				} else { // 농장이 차있음
					if (myField.getItemId() % 2 == 0) { // 씨앗
						fm.harvesting(myField);
					} else {
						System.out.println("아직 덜 자랐습니다...");
						StaticMenu.waitTime(1000);
					}
				}

			}
			//턴 한 번 끝날 때마다
			//character 정보 저장
			
			

		}

	}

}
