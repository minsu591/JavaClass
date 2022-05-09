package co.mia.farm.game.item;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemVO {
	//accId
	private int itemID;
	private int itemCnt;
	
	public ItemVO() {
		
	}
}
