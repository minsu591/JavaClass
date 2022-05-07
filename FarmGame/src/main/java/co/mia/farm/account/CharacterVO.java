package co.mia.farm.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CharacterVO {
	private String accId;
	private String farmName;
	private String userNickname;
	private int userHp;
	private int userLevel;
	private int userExp;
	private int userMoney;
	
	public CharacterVO() {
		
	}

}
