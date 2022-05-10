package co.mia.farm.game.farming;

import java.util.List;

public interface FieldService {
	//내 필드에서 필드 vo영역 찾아오기
	List<InFieldVO> fieldSelect();
	
	//필드 업데이트
	int fieldUpdate(InFieldVO myField, int newId);
	
	int fieldUpdateZero(InFieldVO myField);
	
	//필드 추가
	int fieldAddHere();
	

}
