package co.minseo.prj.test3;

public interface Payment {
	public static double ONLINE_PAYMENT_RATIO = 0.05;
	public static double OFFLINE_PAYMENT_RATIO = 0.03;
	
	public int online(int price);
	public int offline(int price);
	public void showInfo();

}
