package co.micol.prj.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.micol.prj.game.Lotto;
import co.micol.prj.game.MagicSquare;
import co.micol.prj.game.Tetris;

public class Menu {
	private Scanner scn = new Scanner(System.in);
	private Lotto lotto = new Lotto();
	private MagicSquare ms = new MagicSquare();
	private Tetris tetris = new Tetris();
	
	private void mainTitle() {
		System.out.println("======================");
		System.out.println("=======게 임 모 음 ======");
		System.out.println("=====1. 로 또 게 임 =====");
		System.out.println("=====2.  마 방 진  =====");
		System.out.println("=====3. 테 트 리 스 =====");
		System.out.println("=====4. 게 임 종 료 =====");
		System.out.println("======================");
		System.out.println("=하고 싶은 게임을 선택하세요!=");
	}
	
	private void mainMenu() {
		boolean b = true;
		int key = -1;
		do {
			mainTitle();
			try {
				key = scn.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("잘못된 입력입니다! 다시 입력해주세요.");
				scn.nextLine();
			}
			
			switch (key) {
			case 1: //로또 게임
				lotto.run();
				break;
			case 2: //마방진
				ms.run();
				break;
			case 3: //테트리스
				tetris.run();
				break;
			case 4: //게임종료
				b =false;
				scn.close();
				break;
			}
			
		}while(b);
	}
	
	public void run() { //실행만을 위한 
		mainMenu();
	}

}
