package co.minseo.prj.test2;

public class DGBCard extends Card {
	private final String company = "대구은행";
	private String cardStaff;

	public DGBCard(String cardNo, String validDate, String cvc, String cardStaff) {
		super(cardNo, validDate, cvc);
		this.cardStaff = cardStaff;
	}

	@Override
	public void showCardInfo() {
		System.out.println("카드정보 (Card NO : " + this.getCardNo() + ", 유효기간 : " + this.getValidDate() + ", CVC : "
				+ this.getCvc() + " )");
		System.out.println("담당직원 - " + cardStaff + ", " + company);
	}

}
