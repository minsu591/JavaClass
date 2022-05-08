package co.mia.farm;

import org.apache.commons.lang3.StringUtils;

import co.mia.farm.account.AccountVO;
import co.mia.farm.game.print.ConsolePrintService;

public class MainApp 
{
    public static void main( String[] args )
    {
    	//로그인
        LoginMenu login = new LoginMenu();
        login.run();
        
        //게임 시작
        
        	
    }
}
