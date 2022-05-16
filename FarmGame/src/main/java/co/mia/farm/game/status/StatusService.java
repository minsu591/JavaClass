package co.mia.farm.game.status;

import java.util.Scanner;

import co.mia.farm.LoginMenu;
import co.mia.farm.StaticMenu;
import co.mia.farm.account.AccountService;
import co.mia.farm.account.AccountServiceImpl;
import co.mia.farm.account.CharacterVO;
import co.mia.farm.game.item.event.SecretMoneyService;
import co.mia.farm.game.print.ConsolePrintService;
import co.mia.farm.game.print.WaitThread;

public class StatusService {
	LevelServiceImpl ls = new LevelServiceImpl();
	CharacterVO myCh;
	LevelVO levelInfo;
	public static boolean threadFlag = true;
	private AccountService as = new AccountServiceImpl();
	private SecretMoneyService sms = new SecretMoneyService();
	private Scanner scn = new Scanner(System.in);
	
	//경험치 증가 (max경험치보다 작은지 확인)
	//max경험치가 되면 level업 + 팝업창 띄워주기
	public void incExp(int addExp) {
		myCh = LoginMenu.loginCharacter;
		
		levelInfo = ls.levelOneSelect(myCh.getAccId());
		
		if(myCh.getUserExp()+addExp < levelInfo.getMaxExp()) {
			myCh.setUserExp(myCh.getUserExp()+addExp);
			System.out.printf("경험치가 %d만큼 증가했습니다.\n",addExp);
			StaticMenu.waitTime(1000);
		}else {
			myCh.setUserExp(myCh.getUserExp()+addExp - levelInfo.getMaxExp());
			if(myCh.getUserLevel()+1 <=5) { //최대 레벨 설정
				myCh.setUserLevel(myCh.getUserLevel()+1);
				as.characterModify();
				levelInfo = ls.levelOneSelect(myCh.getAccId());
				myCh.setUserHp(levelInfo.getMaxHp());
				System.out.println("축하합니다! 레벨이 올랐습니다!");
				System.out.println("체력도 충전됐어요!");
			}else {
				System.out.println("최대 레벨입니다...");
				myCh.setUserHp(levelInfo.getMaxHp());
			}
			as.characterModify();
			

			//비상금
			sms.resetMoney();
			sms.settingMoney();
			StaticMenu.waitTime(1000);
		}
		
	}
	
	//hp 감소 (maxhp보다 작은지 확인)
	public void descHp(int minusHp) {
		myCh = LoginMenu.loginCharacter;
		levelInfo = ls.levelOneSelect(myCh.getAccId());
		
		if(myCh.getUserHp()-minusHp > 0) {
			myCh.setUserHp(myCh.getUserHp()-minusHp);
			System.out.printf("체력이 %d만큼 감소했습니다.\n",minusHp);
			StaticMenu.waitTime(1000);
		}else {
			ConsolePrintService.clearScreen();
			myCh.setUserHp(0);
			myCh.toString();
			System.out.println("캐릭터 체력이 0이되어 쓰러졌습니다...");
			for(int i = 0; i<3;i++) {
				System.out.println("충전중...");
				StaticMenu.waitTime(1500);
			}
			System.out.println("체력을 조금 회복했습니다. 체력소모에 유의하세요!");
			myCh.setUserHp((int)(levelInfo.getMaxHp()*0.1));
			System.out.print("빠져나가려면 아무키나 입력하세요 >>> ");
			scn.nextLine();
			StaticMenu.waitTime(1000);
			
		}
	}
	
	//hp 증가 (maxhp보다 작은지 확인)
	public void incHp(int addHp) {
		myCh = LoginMenu.loginCharacter;
		levelInfo = ls.levelOneSelect(myCh.getAccId());
		
		if(myCh.getUserHp()+addHp < levelInfo.getMaxHp()) {
			myCh.setUserHp(myCh.getUserHp()+addHp);
		}else if(myCh.getUserHp()+addHp >= levelInfo.getMaxHp()) {
			System.out.println("체력이 가득 찼습니다!");
			myCh.setUserHp(levelInfo.getMaxHp());
		}
	}
	
	public void enterHome() {
		myCh = LoginMenu.loginCharacter;
		levelInfo = ls.levelOneSelect(myCh.getAccId());
		System.out.printf("\n˘◡˘ [̂%s Home] ˘◡˘\n",myCh.getUserNickname());
		System.out.println(".oO[집에 들어왔습니다! 편하게 쉬세요!]\n");
		
		int origHp = myCh.getUserHp();
		int lastHp = levelInfo.getMaxHp() - origHp;
		if(origHp <= levelInfo.getMaxHp()) {
			System.out.println("체력 충전을 시작합니다. (밖으로 나오고 싶으면 아무키나 입력하세요)");
			int forNum = lastHp/10;
			Thread th = new Thread(new WaitThread());
			th.start();
			if(threadFlag == false) {
				threadFlag = true;
			}
			while(forNum > 0 && threadFlag) {
				myCh.setUserHp(myCh.getUserHp()+10);
				System.out.printf("체력 충전중... [%d/%d]\n",myCh.getUserHp(),levelInfo.getMaxHp());
				StaticMenu.waitTime(1500);
				forNum--;
			}
			if(forNum <= 0) {
				myCh.setUserHp(levelInfo.getMaxHp());
				System.out.println("체력 충전을 완료했습니다!");
			}
			System.out.printf(".oO[현재 체력 : (%d/%d)]\n",myCh.getUserHp(),levelInfo.getMaxHp());
			as.characterModify();
			StaticMenu.waitTime(1000);
			
		}else {
			System.out.println("체력이 가득 찼어요!");
			StaticMenu.waitTime(1000);
		}
	}
	

}

