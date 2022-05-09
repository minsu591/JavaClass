package co.mia.farm;

import co.mia.farm.account.AccountVO;

public class testMenu {
	
	public static void main(String[] args) {
		GameMenu gm = new GameMenu();
		LoginMenu.loginAccount = new AccountVO("admin","admin","010-2222-3333"); //테스트용
		gm.run();
	}
	
}
