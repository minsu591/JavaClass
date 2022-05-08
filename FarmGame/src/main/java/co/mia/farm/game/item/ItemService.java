package co.mia.farm.game.item;

import java.util.List;

public interface ItemService {
	//1. 아이템 처음 추가
	int itemInsert(String itemId, String itemName, int itemStatus);
	//2. 아이템창 select => 조회
	List<ItemVO> itemSelect();
	//3. user한테 아이템 존재하는지 검사
	ItemVO itemCheck(String itemName);
	//4. 아이템 정보 수정
	int itemUpdate(String itemId, int itemCnt);
}
