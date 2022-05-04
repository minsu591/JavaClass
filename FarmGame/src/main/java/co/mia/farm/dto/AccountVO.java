package co.mia.farm.dto;

import lombok.Data;

@Data
public class AccountVO {
	private String id;
	private String pw;
	private String phoneNum;
	private String nickname;
	private String farmName;
	
	public AccountVO(String id, String pw, String phoneNum) { //가입용 생성자
		this.id = id;
		this.pw = pw;
		this.phoneNum = phoneNum;
		this.nickname = null;
		this.farmName = null;
	}

}
