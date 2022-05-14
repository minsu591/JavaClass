package co.mia.farm.game.item.event;

import co.mia.farm.GameMenu;
import co.mia.farm.StaticMenu;
import co.mia.farm.game.farming.FieldService;
import co.mia.farm.game.farming.FieldServiceImpl;
import co.mia.farm.game.farming.InFieldVO;
import co.mia.farm.game.print.ConsolePrintService;

public class MonsterThread implements Runnable{
	private FieldService fsi = new FieldServiceImpl();
	private InFieldVO monsterField = new InFieldVO();
	private boolean cropBoolean;
	
	
	public MonsterThread(InFieldVO monsterField, boolean cropBoolean) {
		this.cropBoolean = cropBoolean;
		this.monsterField = monsterField;
	}


	@Override
	public void run() {
		int s = 10;
		
		System.out.println("※※※※※※※※※※ 위 험 ※※※※※※※※※※");
		while(s>0) {
			if(GameMenu.monsterFlag) {
				break;
			}
			
			if(cropBoolean) {
				System.out.printf("\n멧돼지가 농작물을 먹고있어요! 퇴치까지 남은 시간 : %d초\n",s);	
			}else {
				System.out.printf("\n멧돼지가 땅을 망치고 있어요! 퇴치까지 남은 시간 : %d초\n",s);
			}
			StaticMenu.waitTime(1000);
			s--;
			
			if(ConsolePrintService.userX == ConsolePrintService.monsterX && ConsolePrintService.userY == ConsolePrintService.monsterY) {
				int rnd =(int)(Math.random()*3); //랜덤으로
				for(int i = 0;i<2;i++) {
					System.out.println("\n멧돼지 퇴치 시도 중입니다...");
					StaticMenu.waitTime(1000);
				}
				if(rnd==1) {
					System.out.println("\n멧돼지 퇴치에 실패했습니다...");
					StaticMenu.waitTime(1000);
					s=0;
				}else {
					System.out.println("\n멧돼지를 퇴치했습니다!");
					System.out.println("멧돼지가 떠났어요!");
					StaticMenu.waitTime(1000);
					break;
				}
			}
		}
		if(s==0) {
			System.out.println();
			if(cropBoolean) {
				fsi.fieldUpdateZero(monsterField);
				System.out.println("멧돼지 퇴치에 실패해서 멧돼지가 농작물을 먹어버렸습니다...");		
				System.out.println("심어놓은 농작물이 사라졌어요...");		
			}else {
				fsi.fieldDrop(monsterField);
				System.out.println("멧돼지 퇴치에 실패해서 멧돼지가 땅을 망쳐버렸습니다...");
				System.out.println("갈아놓은 땅이 쓸 수 없게 되었어요...");
			}
			
			StaticMenu.waitTime(1000);
		}
		GameMenu.monsterFlag = true;
		ConsolePrintService.printGame[ConsolePrintService.monsterY][ConsolePrintService.monsterX] = " ";
		ConsolePrintService.monsterY = -1;
		ConsolePrintService.monsterX = -1;
		System.out.print("계속 진행하려면 아무키나 입력해주세요 >>> ");
	}

}
