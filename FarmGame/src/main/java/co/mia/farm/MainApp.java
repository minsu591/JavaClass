package co.mia.farm;

import org.apache.commons.lang3.StringUtils;

public class MainApp 
{
    public static void main( String[] args )
    {
    	//로그인
        LoginMenu login = new LoginMenu();
        GameMenu gm = new GameMenu();
        login.run();
        //게임 시작
        gm.run();
        
        	
    }
}
