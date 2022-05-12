package co.mia.farm;

import co.mia.farm.account.AccountVO;
import co.mia.farm.account.CharacterVO;
import co.mia.farm.game.item.event.TutorialService;
import co.mia.farm.game.status.StatusService;

public class testMenu {
	
	public static void main(String[] args) {
		LoginMenu.loginAccount = new AccountVO("admin","admin","0102323334");
		TutorialService ts = new TutorialService();
		ts.run();
	}
	
}
