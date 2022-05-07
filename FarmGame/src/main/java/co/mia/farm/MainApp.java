package co.mia.farm;

import org.apache.commons.lang3.StringUtils;

import co.mia.farm.account.AccountVO;
import co.mia.farm.game.print.ConsolePrintService;

public class MainApp 
{
	private static AccountVO loginAccount = new AccountVO();
    public static void main( String[] args )
    {
    	//로그인
        LoginMenu login = new LoginMenu();
        loginAccount = login.run();
        
        //게임 시작
        if(!StringUtils.isEmpty(loginAccount.getAccId())) {
        	ConsolePrintService cps = new ConsolePrintService();
    		cps.consolePrintRun();
        }
        	
    }
}
