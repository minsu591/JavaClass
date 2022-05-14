package co.mia.farm;

import java.util.List;

import co.mia.farm.account.AccountService;
import co.mia.farm.account.AccountServiceImpl;
import co.mia.farm.game.farming.FarmingMenu;
import co.mia.farm.game.farming.FieldService;
import co.mia.farm.game.farming.FieldServiceImpl;
import co.mia.farm.game.farming.ForFarmingThread;
import co.mia.farm.game.farming.InFieldVO;
import co.mia.farm.game.item.AllProductVO;
import co.mia.farm.game.item.ItemService;
import co.mia.farm.game.item.ItemServiceImpl;
import co.mia.farm.game.item.event.MonsterThread;
import co.mia.farm.game.item.event.SecretMoneyService;
import co.mia.farm.game.print.ConsolePrintService;
import co.mia.farm.game.shop.ShopMenu;
import co.mia.farm.game.status.StatusService;

public class GameMenu {
	private ConsolePrintService cps = new ConsolePrintService();
	private FieldService fs = new FieldServiceImpl();
	private FarmingMenu fm = new FarmingMenu();
	private InFieldVO myField = new InFieldVO();
	private InFieldVO monsterField = new InFieldVO();
	private ShopMenu sm = new ShopMenu();
	private StatusService ss = new StatusService();
	public static boolean checkGame = true;
	private AccountService as = new AccountServiceImpl();
	private SecretMoneyService sms = new SecretMoneyService();
	private ItemService is = new ItemServiceImpl();
	//몬스터가 이미 작물 먹고 있을 때는 false로
	public static boolean monsterFlag = true;

	public void run() {
		setting();
		sms.settingMoney();
		while (LoginMenu.checkGame) {
			game();
		}

	}

	private void setting() { //기본 설정
		ConsolePrintService.setBasicArray(); // 필드 ' '으로 구성
		
		List<InFieldVO> myFields = fs.fieldSelect();
		for (InFieldVO i : myFields) {
			if (i.getItemId() != 0) {
				System.out.printf("[%d,%d] 좌표의 농작물이 썩었습니다......\n", i.getFieldX(), i.getFieldY());
				fs.fieldUpdateZero(i);
				StaticMenu.waitTime(1000);
			}
		}
	}

	
	
	private void game() {
		cps.consolePrintRun();
		// 이동
		myField = fm.checkMyField();
		if(monsterFlag) { //몬스터가 다른 작물 먹을 때는 시행하지 않기.
			monsterField = fm.checkMonsterField();			
		}
		
		//몬스터 필드에 값이 들어왔을 때 (1/10의 확률로)
		//최초로 시행해야되기때문에 monsterFlag가 true일 때
		if(monsterField.getFieldX() != -1 && monsterField.getFieldY() != -1 && monsterFlag) {
			ConsolePrintService.monsterX = monsterField.getFieldX();
			ConsolePrintService.monsterY = monsterField.getFieldY();
			Runnable r;
			if(monsterField.getItemId()==0) {
				r = new MonsterThread(monsterField, false);
			}else {
				r = new MonsterThread(monsterField,true);
			}
			Thread th = new Thread(r);
			th.start();
			monsterFlag = false;
		}
		
		
		if (ConsolePrintService.userY == 0 && ConsolePrintService.userX == 1) { // 내 위치가 상점
			sm.run();
			ConsolePrintService.exitIfCancel();
		} else if (ConsolePrintService.userY == 0 && ConsolePrintService.userX == ConsolePrintService.wSize / 2) { // 내 위치가 집이면
			ss.enterHome();
			ConsolePrintService.exitIfCancel();
		} else if (ConsolePrintService.userY == SecretMoneyService.smY && ConsolePrintService.userX == SecretMoneyService.smX) { //비상금
			//비상금
			System.out.println("앗! 숨겨둔 비상금을 발견했습니다!");
			StaticMenu.waitTime(1000);
			System.out.println("얼마일까요?");
			StaticMenu.waitTime(1000);
			for(int i = 0; i<3;i++) {
				System.out.println("돈 세는 중...");
				StaticMenu.waitTime(1000);
			}
			System.out.printf("%d별 입니다! 주머니에 넣을게요!",SecretMoneyService.smMoney);
			LoginMenu.loginCharacter.setUserMoney(LoginMenu.loginCharacter.getUserMoney()+SecretMoneyService.smMoney);
			as.characterModify();
			sms.resetMoney();
			StaticMenu.waitTime(1000);
		} else if (myField.getFieldX() != -1 && myField.getFieldY() != -1 && myField.getItemId() != -1) { // 내 위치가 농장필드면
			if(ConsolePrintService.userX == ConsolePrintService.monsterX && ConsolePrintService.userY == ConsolePrintService.monsterY) {
				// 내 위치가 농장필드인데 멧돼지랑 위치가 같다?
				// 멧돼지 퇴치하는거 실행
			} else if (myField.getItemId() == 0) { // 농장이 비어있음
				fm.farming(myField);
			} else { // 농장이 차있음
				if (myField.getItemId() % 2 == 0 ) { // 씨앗
					fm.harvesting(myField);
				} else { //작물
					ForFarmingThread fft = new ForFarmingThread();
					int leftSec = fft.remainTime();
					AllProductVO cropInfo = is.itemGetproduct(myField.getItemId());
					System.out.printf("%s(이)가 아직 덜 자랐습니다... %d초만 기다려주세요...\n",cropInfo.getItemName(),leftSec);				
					StaticMenu.waitTime(1000);
				}
			}
		}
		
		
		
		
	}

}
