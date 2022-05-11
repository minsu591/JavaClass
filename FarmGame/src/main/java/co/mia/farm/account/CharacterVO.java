package co.mia.farm.account;

import co.mia.farm.game.status.LevelServiceImpl;
import co.mia.farm.game.status.LevelVO;
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
		LevelServiceImpl ls = new LevelServiceImpl();
		LevelVO levelInfo = ls.levelOneSelect(accId);
		System.out.println("٩(●'▿'●)۶");
		System.out.printf("%s 농장, %s님\n",farmName,userNickname);
		System.out.printf("레벨 : %2d (%s) || 현재 경험치 : %3d /%3d\n",userLevel,levelInfo.getLevelName(),userExp,levelInfo.getMaxExp());
		System.out.printf("현재 자금 : %d별\n",userMoney);
		//userHP가 full의 30프로 떨어지면 쌩쌩함
		//userHP가 full의 50프로 떨어지면 조금 지침
		//userHP가 full의 70프로 떨어지면 힘들어요...
		//userHP가 10프로 남으면 충전이 필요해요...
		System.out.printf("HP : %4d /%4d \n",userHp,levelInfo.getMaxHp());
		System.out.println();
		
		return null;
	}



}
