package co.mia.farm.game.farming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.mia.farm.StaticMenu;
import co.mia.farm.account.AccountService;
import co.mia.farm.account.AccountServiceImpl;
import co.mia.farm.game.item.AllProductVO;
import co.mia.farm.game.item.ItemService;
import co.mia.farm.game.item.ItemServiceImpl;
import co.mia.farm.game.item.ItemVO;
import co.mia.farm.game.print.ConsolePrintService;
import co.mia.farm.game.status.StatusService;

public class FarmingMenu { // 농작 메뉴
	private Scanner scn = new Scanner(System.in);
	private String ans;
	private List<ItemVO> userItems = new ArrayList<ItemVO>();
	private AllProductVO sysItem = new AllProductVO();
	private ItemService is = new ItemServiceImpl();
	private FieldService fsi = new FieldServiceImpl();
	private StatusService ss = new StatusService();
	private AccountService asi = new AccountServiceImpl();
	
	private int randomMonster() {
		int getCnt = 0;
		int rnd = (int)(Math.random()*5+1);
		System.out.println();
		if(rnd == 1) {//농작물 망침
			int rndIncrease = (int)(Math.random()*2+1);
			if(rndIncrease == 1) {
				System.out.println("〜(꒪꒳꒪)〜 .o0[ 운이 좋았네요! 농작물 2개가 자랐습니다. ]");
				getCnt = 2;
			}else if(rndIncrease == 2){
				System.out.println("₍₍ ◝(・ω・)◟ ⁾⁾ .o0[ 운이 엄청 좋았네요! 농작물 3개가 자랐습니다!! ]");
				getCnt = 3;
			}
		}else {
			getCnt = 1;
		}
		return getCnt;
	}
	
	public void harvesting(InFieldVO myField) {
		AllProductVO apv = is.itemGetproduct(myField.getItemId()); //현재 필드에 심겨진 정보 가져와서 변환
		System.out.printf("%s(이)가 다 자랐습니다!\n", apv.getItemName()); //현재 필드에 심겨진 작물 이름
		System.out.print("농작물을 수확하시겠어요? (y/n) >>> ");
		try {
			ans = scn.next();
		} catch (Exception e) {
			System.out.println("입력이 잘못됐습니다. 다시 입력바랍니다...");
			scn.next();
		}
		if (ans.equalsIgnoreCase("y")) {
			int getCnt = randomMonster();
			if(getCnt != 0) {
				ItemVO myItem = is.itemOneSelect(apv.getItemId()); //현재 필드에 심겨진 작물이 내 아이템 창에 있는지 확인
				if (myItem.getItemID() == -1 && myItem.getItemCnt() == -1) { // 가방에 없다
					is.itemInsert(myField.getItemId(), getCnt);
				} else { // 가방에 있다
					is.itemUpdateCnt(myItem.getItemID(), myItem.getItemCnt() + getCnt);
				}
				System.out.printf("%s %d개를 수확했습니다!\n", apv.getItemName(), getCnt);
				StaticMenu.waitTime(1000);
			}
			fsi.fieldUpdateZero(myField);
			//hp 감소
			ss.descHp(apv.getCHp());
			//경험치 증가
			ss.incExp(apv.getCExp());
			//캐릭터 정보 db에 저장
			asi.characterModify();
			
			
		}else {
			System.out.println("수확을 취소했습니다.");
			StaticMenu.waitTime(1000);
		}

	}
	
	public InFieldVO checkMonsterField() {
		InFieldVO monsterField = new InFieldVO(-1,-1,-1);
		int rnd = (int)(Math.random()*30);
		if(rnd==1) {
			List<InFieldVO> monsterFields = fsi.fieldSelect();
			if(monsterFields.size() != 0) {
				rnd = (int)(Math.random()*(monsterFields.size()-1));
				monsterField = monsterFields.get(rnd);
			}
		}
		return monsterField;
	}
	
	public InFieldVO checkMyField() { // 내 위치가 농장필드에 있는지 확인 => 있으면 농장필드 정보 반환
		InFieldVO myField = new InFieldVO(-1, -1, -1);
		FieldServiceImpl fsi = new FieldServiceImpl();
		List<InFieldVO> myfields = fsi.fieldSelect();
		for (int i = 0; i < myfields.size(); i++) {
			if (ConsolePrintService.userX == myfields.get(i).getFieldX()
					&& ConsolePrintService.userY == myfields.get(i).getFieldY()) {
				myField = myfields.get(i);
				break;
			}
		}
		return myField;
	}

	public void farmingThread(int sec) {
		InFieldVO myField = checkMyField(); // myField 업로드
		Runnable mf = new MultiFarmingThread(sec, myField);
		Thread th = new Thread(mf);
		th.start();
	}
	
	public void farming(InFieldVO myField) {
		System.out.print("농작물을 심으시겠어요? (y/n) >>> ");
		try {
			ans = scn.next();
		} catch (Exception e) {
			System.out.println("입력이 잘못됐습니다. 다시 입력바랍니다...");
			scn.next();
		}
		if (ans.equalsIgnoreCase("y")) {
			
			userItems = is.itemKindSelect(1); // 씨앗 목록 가져오기
			is.itemsPrint(userItems); // 아이템창 출력
			if (userItems.size() != 0) {
				System.out.print("심을 농작물의 번호를 입력해주세요 >>>");
				int ans = scn.nextInt();
				if (ans > 0 && ans < userItems.size() + 1) {
					ans--;
					ItemVO myItem = userItems.get(ans);
					sysItem = is.itemGetproduct(myItem.getItemID());
					System.out.printf("%s(을)를 심을게요!\n\n", sysItem.getItemName());

					if (myItem.getItemCnt() <= 1) {
						is.itemDelete(myItem);
					} else {
						is.itemUpdateCnt(userItems.get(ans).getItemID(), myItem.getItemCnt() - 1);
					}
					System.out.println("심는중... _〆(ﾟ▽ﾟ*)");
					StaticMenu.waitTime(1000);
					System.out.println("물 뿌리는 중... ♪(´ε｀*)");
					StaticMenu.waitTime(1000);
					System.out.println("예뻐하는 중... (❁´▽`❁)*✲ﾟ*");
					StaticMenu.waitTime(1000);
					fsi.fieldUpdate(myField, myItem.getItemID()); // 씨앗 심은거 표시
					System.out.println("\n심기 완료!");
					ss.descHp(5);
					ss.incExp(5);
					asi.characterModify();
					StaticMenu.waitTime(1000);
					ForFarmingThread fft = new ForFarmingThread();
					fft.farmingThread(sysItem.getCTime());
					
				} else {
					System.out.println("\n선택한 농작물이 존재하지 않습니다. 다시 선택해주세요...");
					StaticMenu.waitTime(1000);
				}
			}
		} else {
			System.out.println("\n농작물 심기를 취소했습니다.");
			StaticMenu.waitTime(1000);
		}

	}

}
