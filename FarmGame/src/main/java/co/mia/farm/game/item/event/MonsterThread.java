package co.mia.farm.game.item.event;

import co.mia.farm.StaticMenu;
import co.mia.farm.game.farming.FieldService;
import co.mia.farm.game.farming.FieldServiceImpl;
import co.mia.farm.game.farming.InFieldVO;
import co.mia.farm.game.print.ConsolePrintService;

public class MonsterThread implements Runnable{
	public static boolean monsterflag = false;
	private FieldService fsi = new FieldServiceImpl();
	private InFieldVO monsterField = new InFieldVO();
	
	
	public MonsterThread(InFieldVO monsterField) {
		this.monsterField = monsterField;
	}


	@Override
	public void run() {
		int s = 10;
		
		while(s>0) {
			if(monsterflag) {
				break;
			}
			if(ConsolePrintService.userX == ConsolePrintService.monsterX && ConsolePrintService.userY == ConsolePrintService.monsterY) {
				System.out.println("멧돼지를 퇴치했습니다!");
				StaticMenu.waitTime(1000);
				ConsolePrintService.monsterX = (int) (Math.random() * (ConsolePrintService.wSize - 1));
				ConsolePrintService.monsterY = (int) (Math.random() * (ConsolePrintService.hSize - 1));
				
				break;
			}
			System.out.printf("멧돼지가 농작물을 먹고있어요! 퇴치까지 남은 시간 : %d초\n",s);
			StaticMenu.waitTime(1000);
			s--;
		}
		if(s==0) {
			fsi.fieldUpdateZero(monsterField);
			System.out.println("멧돼지 퇴치에 실패해서 멧돼지가 농작물을 먹어버렸습니다...");
			StaticMenu.waitTime(1000);
		}
		ConsolePrintService.monsterStayFlag = true;
		
	}

}
