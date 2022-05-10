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

	@Override
	public String toString() {
		System.out.println("٩(●'▿'●)۶");
		System.out.printf("%s 농장, %s님\n",farmName,userNickname);
		System.out.printf("레벨 : %2d (초보 농부) || 현재 경험치 : %3d\n",userLevel,userExp);
		System.out.printf("현재 자금 : %d별\n",userMoney);
		//userHP가 full의 30프로 떨어지면 쌩쌩함
		//userHP가 full의 50프로 떨어지면 조금 지침
		//userHP가 full의 70프로 떨어지면 힘들어요...
		//userHP가 10프로 남으면 충전이 필요해요...
		System.out.printf("HP : %4d /%4d \n",userHp,userHp);
		System.out.println();
		
		return "CharacterVO [accId=" + accId + ", farmName=" + farmName + ", userNickname=" + userNickname + ", userHp="
				+ userHp + ", userLevel=" + userLevel + ", userExp=" + userExp + ", userMoney=" + userMoney + "]";
	}



}
