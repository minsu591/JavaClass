package co.mia.farm.game.item;

import lombok.Data;

@Data
public class AllProductVO {
	private String itemId;
	private String itemName;
	private int aSell;
	private int aPur;
	private int aLevel;
	private String aExplain;
	private int cTime;
	private int cExp;
}
