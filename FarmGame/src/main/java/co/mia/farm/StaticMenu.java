package co.mia.farm;

public class StaticMenu {
	public static void waitTime(int sec) {
		long original = System.currentTimeMillis();
		while(true) {
			if (System.currentTimeMillis() - original >= sec) {
				break;
			}
		}
	}
}
