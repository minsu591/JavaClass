package co.mia.farm.game.item;

import lombok.Data;

@Data
public class AllProductVO {
	private int itemId;
	private String itemName;
	private int aCost;
	private int aLevel;
	private String aExplain;
	private int cTime;
	private int cExp;
	
	@Override
	public String toString() {
		String s = itemName + " | 가격 : "+aCost+"별 ";
		return s;
	}
	
	public String toStringDetail() {
		String s = "이름 : "+ itemName + " | 가격 : "+aCost+"별 | 상세설명 : "+aExplain;
		return s;
	}
}

