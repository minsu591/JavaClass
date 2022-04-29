package co.minseo.prj.test2;

public class TossCard extends Card{
	private final String company = "Toss";
	private String cardStaff;
	
	public TossCard(String cardNo, String validDate, String cvc, String cardStaff) {
		super(cardNo, validDate, cvc);
		this.cardStaff =cardStaff;
	}
	
	@Override
	public void showCardInfo() {
		System.out.println("카드정보 - Card NO, "+ this.getCardNo());
		System.out.println("담당직원 - "+cardStaff+", "+company);
	}
}
