package co.mia.farm.game.item;

import java.util.List;

public interface ItemService {
	//1. 아이템 insert
	int itemInsert(int itemId, int itemCnt);
	
	//2. 아이템 전체 조회
	List<ItemVO> itemAllSelect();
	
	//3. 한 개 조회 (ALL_PRODUCTS -> ITEMS로)
	ItemVO itemOneSelect(int itemId);

	//4. items통해서 all_products 정보 불러오기 (ITEMS -> ALL_PRODUCTS)
	AllProductVO itemGetproduct(int itemId);
	
	//5.판매나 구매에서 소지 갯수가 변했을 때 item 수정
	int itemUpdateCnt(int itemId, int newCnt);
	
	//6.씨앗으로 변했을 때 item 수정
	
	//7. 아이템 0개 이하일 때 삭제
	int itemDelete(ItemVO item);
	
	//8. 씨앗만 조회
	List<ItemVO> itemSeedSelect();
	
	//9. 아이템창 조회
	void itemsPrint(List<ItemVO> userItems);
	
	//10. allproduct 전체 리스트 가져오기
	List<AllProductVO> productSelect();
	
	//11. allProduct 전체 조회
	boolean productPrint(List<AllProductVO> shopItems);
	

	
	
}
