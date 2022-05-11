package co.mia.farm;

public class MainApp 
{
    public static void main( String[] args )
    {
    	//로그인
        LoginMenu login = new LoginMenu();
        GameMenu gm = new GameMenu();
        
        while(LoginMenu.checkLogin) {
        	login.run();
            //게임 시작
            gm.run();
        }
       
        
        	
    }
}
