package co.minseo.prj.test2;

public class MainApp {
	public static void main(String[] args) {
		Card card = new Card("5432-4567-9534-3657","20251203","253");
		card.showCardInfo();
		card = new TossCard("5432-4567-9534-3657","20251203","253","신빛용");
		card.showCardInfo();
		card = new DGBCard("5432-4567-9534-3657","20251203","253","신빛용");
		card.showCardInfo();
		
	}

}
