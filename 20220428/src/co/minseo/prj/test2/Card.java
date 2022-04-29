package co.minseo.prj.test2;

public class Card {
	private String cardNo;
	private String validDate;
	private String cvc;
	
	public Card(String cardNo, String validDate, String cvc) {
		super();
		this.cardNo = cardNo;
		this.validDate = validDate;
		this.cvc = cvc;
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public String getValidDate() {
		return validDate;
	}


	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}


	public String getCvc() {
		return cvc;
	}


	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
	
	public void showCardInfo() {
		System.out.printf("카드정보 ( Card NO : %s, 유효기간 : %s, CVC : %s )\n",cardNo,validDate,cvc);
	}
	

}
