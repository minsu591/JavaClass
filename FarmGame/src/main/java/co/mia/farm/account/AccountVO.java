package co.mia.farm.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountVO {
	// accSeq
	private String accId;
	private String accPw;
	private String accPhone;

	public AccountVO() {
		this.accId = null;
		this.accPw = null;
		this.accPhone = null;

	}

}
