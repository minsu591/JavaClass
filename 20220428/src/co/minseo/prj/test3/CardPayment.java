package co.minseo.prj.test3;

public class CardPayment implements Payment {
	private double cardRatio;

	public CardPayment(double cardRatio) {
		super();
		this.cardRatio = cardRatio;
	}

	@Override
	public int online(int price) {
		price = (int) (price * (1 - cardRatio - ONLINE_PAYMENT_RATIO));
		return price;
	}

	@Override
	public int offline(int price) {
		price = (int) (price * (1 - cardRatio - OFFLINE_PAYMENT_RATIO));
		return price;
	}

	@Override
	public void showInfo() {
		System.out.println("*** 카드로 결제 시 할인정보");
		System.out.println("온라인 결제시 총 할인율 : " + (cardRatio + ONLINE_PAYMENT_RATIO));
		System.out.println("오프라인 결제시 총 할인율 : " + (cardRatio + OFFLINE_PAYMENT_RATIO));
	}

}
