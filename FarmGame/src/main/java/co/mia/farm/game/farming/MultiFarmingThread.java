package co.mia.farm.game.farming;

import co.mia.farm.StaticMenu;
import co.mia.farm.game.item.ItemServiceImpl;
import co.mia.farm.game.item.ItemVO;
import lombok.AllArgsConstructor;
import lombok.Data;

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
		ItemServiceImpl is = new ItemServiceImpl();
//		ItemVO item = is.itemOneSelect(myField.getItemId());
		fsi.fieldUpdate(myField, myField.getItemId()-1);
	}
	
	

}
