package co.mia.farm;

import co.mia.farm.account.AccountVO;
import co.mia.farm.account.CharacterVO;
import co.mia.farm.game.status.StatusService;

public class testMenu {
	
	public static void main(String[] args) {
		GameMenu gm = new GameMenu();
		StatusService ss = new StatusService();
		LoginMenu.loginAccount = new AccountVO("admin","admin","010-2222-3333"); //테스트용
		LoginMenu.loginCharacter = new CharacterVO("admin","메","미아",80,1,80,80);
		ss.enterHome();
		gm.run();
	}
	
}
