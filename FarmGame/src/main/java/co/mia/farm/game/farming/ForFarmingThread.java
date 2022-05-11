package co.mia.farm.game.farming;

public class ForFarmingThread {
	private FarmingMenu fm = new FarmingMenu();
	long nowTime;
	long afterTime;
	
	public void farmingThread(int sec) {
		InFieldVO myField = fm.checkMyField(); // myField 업로드
		Runnable mf = new MultiFarmingThread(sec, myField);
		Thread th = new Thread(mf);
		nowTime = System.currentTimeMillis();
		th.start();
	}
	
	public int knowWhatTime() {
		afterTime = System.currentTimeMillis();
		int secTime = (int)((afterTime - nowTime)/1000);
		return secTime;
	}
	

}
