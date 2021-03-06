package co.mia.farm.game.farming;

import co.mia.farm.StaticMenu;
import co.mia.farm.game.item.ItemServiceImpl;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MultiFarmingThread implements Runnable {
	private int sec;
	private InFieldVO myField;

	@Override
	public void run() {
		while(sec > 0) {
			StaticMenu.waitTime(1000);
			sec--;
		}
		// 정보 바꾸기
		FieldServiceImpl fsi = new FieldServiceImpl();
		int itemId = fsi.fieldInfoSelect(myField);
		if(itemId != 0) {
			fsi.fieldUpdate(myField, myField.getItemId()-1);			
		}

	}

	
	

}
