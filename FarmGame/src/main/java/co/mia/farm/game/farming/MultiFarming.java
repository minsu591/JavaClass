package co.mia.farm.game.farming;

import co.mia.farm.StaticMenu;
import co.mia.farm.game.item.ItemServiceImpl;
import co.mia.farm.game.item.ItemVO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MultiFarming implements Runnable {
	private int sec;
	private InFieldVO myField;

	@Override
	public void run() {
		for (int i = 0; i < sec; i++) {
			StaticMenu.waitTime(1000);
		}
		// 정보 바꾸기
		FieldServiceImpl fsi = new FieldServiceImpl();
		ItemServiceImpl is = new ItemServiceImpl();
		ItemVO item = is.itemOneSelect(myField.getItemId());
		fsi.fieldUpdate(myField, item.getItemID()-1);
	}

}
