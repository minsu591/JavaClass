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
	
	private ItemService is = new ItemServiceImpl();
	
	@Override
	public String toString() {
		System.out.printf("%s | 구매가격 : %d별\n",itemName,aCost);
		return null;
	}
	
	public void toStringForSeed() {
		AllProductVO cropInfo =  is.itemGetproduct(itemId-1); //농작물일 때 가격
		System.out.printf("%s | 구매가격 : %d별 | 수확 후 가격 : %d별\n",itemName,aCost,cropInfo.getACost());
	}
	
	public String toStringDetail() {
		System.out.printf("이름 : %s || 가격 : %d || 수확가능 레벨 : %d || 상세 설명 : %s\n",itemName,aCost,aLevel,aExplain);
		return null;
	}
}

