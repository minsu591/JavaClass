package co.minseo.prj.test3;

public class SimplePayment implements Payment {
	private double simplePaymentRatio;

	public SimplePayment(double simplePaymentRatio) {
		super();
		this.simplePaymentRatio = simplePaymentRatio;

	}

	@Override
	public int online(int price) {
		price = (int) (price * (1 - simplePaymentRatio - ONLINE_PAYMENT_RATIO));
		return price;
	}

	@Override
	public int offline(int price) {
		price = (int) (price * (1 - simplePaymentRatio - OFFLINE_PAYMENT_RATIO));
		return price;

	}

	@Override
	public void showInfo() {
		System.out.println("*** 카드로 결제 시 할인정보");
		System.out.println("온라인 결제시 총 할인율 : " + (simplePaymentRatio + ONLINE_PAYMENT_RATIO));
		System.out.println("오프라인 결제시 총 할인율 : " + (simplePaymentRatio + OFFLINE_PAYMENT_RATIO));

	}

}
