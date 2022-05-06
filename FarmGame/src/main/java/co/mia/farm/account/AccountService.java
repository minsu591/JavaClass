package co.mia.farm.account;

public interface AccountService {
	int accountInsert(AccountVO vo); //회원가입
	AccountVO accountLogin(AccountVO vo); //로그인
	int characterInsert(CharacterVO chVO); //캐릭터 넣기
	boolean characterCheck(AccountVO vo); //캐릭터 처음 생성하는지 확인
	
	//본인 계정에 들어간 후
//	int accountUpdate(AccountVO vo); //계정 수정
//	int accountDelete(AccountVO vo); //계정 삭제
}
