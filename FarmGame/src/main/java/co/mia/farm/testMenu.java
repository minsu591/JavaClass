package co.mia.farm;

import co.mia.farm.account.AccountVO;
import co.mia.farm.account.CharacterVO;

public class testMenu {
	
	public static void main(String[] args) {
		GameMenu gm = new GameMenu();
		LoginMenu.loginAccount = new AccountVO("admin","admin","010-2222-3333"); //테스트용
		LoginMenu.loginCharacter = new CharacterVO("admin","메","미아",300,1,10,300);
		gm.run();
	}
	
}
