package co.mia.farm.game.item.event;

import co.mia.farm.LoginMenu;
import co.mia.farm.account.AccountService;
import co.mia.farm.account.AccountServiceImpl;
import co.mia.farm.game.print.ConsolePrintService;
import lombok.Data;


@Data
public class SecretMoneyService {
	public static int smX;
	public static int smY;
	public static int smMoney;
	private AccountService as = new AccountServiceImpl();
	
	public SecretMoneyService() {
		smX = (int)(Math.random()*(ConsolePrintService.wSize-1));
		smY = (int)(Math.random()*(ConsolePrintService.hSize-1));
		smMoney = (int)(Math.random()*100)+30;
	}
	
	public static void resetMoney() {
		smMoney = -1;
	}
}

