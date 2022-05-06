package co.mia.farm.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CharacterVO {
	private String acc_id;
	private String farm_name;
	private String user_nickname;
	private int user_hp;
	private int user_level;
	private int user_exp;
	private int user_money;
	
	public CharacterVO() {
		
	}

}
