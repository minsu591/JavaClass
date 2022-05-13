package co.mia.farm.account;

import java.util.List;

public interface AccountService {
	int accountInsert(AccountVO vo); //회원가입
	AccountVO accountLogin(AccountVO vo); //로그인
	
	
	List<CharacterVO> characterAllSelect();
	int characterInsert(CharacterVO chVO); //캐릭터 생성
	boolean characterCheck(String id); //id로 된 캐릭터 존재하는지 확인
	CharacterVO characterSelect(String id); //캐릭터 데이터 불러오기
	
	
	//한 턴마다 캐릭터 정보 수정하기
	int characterModify();
	
	//본인 계정에 들어간 후
	int accountUpdate(AccountVO vo); //계정 수정
	int characterUpdate(CharacterVO vo, int num);
	int accountDelete(AccountVO vo); //계정 삭제
}
