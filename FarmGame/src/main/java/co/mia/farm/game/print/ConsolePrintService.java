package co.mia.farm.game.print;

import java.util.Scanner;

public class ConsolePrintService {
	private Scanner scn = new Scanner(System.in);
	private int wSize = 25;
	private int hSize = 15;
	private int[][] printGame = new int[hSize][wSize];
	private int x = 0;
	private int y = 0;
	private String person = "(º-º)";
	private boolean playing = true;

	public void consolePrintRun() {
		while (playing) {
			clearScreen();
			arrPrint();
			inputKeyboard();
		}
	}

	private void arrPrint() {
		for (int i = 0; i < wSize - 3; i++) {
			System.out.printf("%2s", "ㅡ");
		}
		System.out.println();

		for (int i = 0; i < hSize; i++) {
			for (int j = 0; j < wSize; j++) {
				if (j == 0) {
					System.out.print("|");
				}
				if(x==j && y==i) {
					System.out.printf("%2s",person);
				}else {
					System.out.printf("%2d", printGame[i][j]);					
				}
				if (j == wSize - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
		}
		for (int i = 0; i < wSize - 3; i++) {
			System.out.printf("%2s", "ㅡ");
		}
		System.out.println();
	}

	private void inputKeyboard() {
		System.out.print("(a : 왼쪽 / d: 오른쪽 / w : 위 / x : 아래 /quit : 종료) >>> ");
		String input = scn.next();
		if (input.equals("a")) {
			System.out.println("왼쪽");
			x--;
		} else if (input.equals("d")) {
			System.out.println("오른쪽");
			x++;
		} else if (input.equals("w")) {
			System.out.println("위");
			y--;
		} else if (input.equals("x")) {
			System.out.println("아래");
			y++;
		} else if (input.equals("quit")) {
			playing = false;
		}
		
		else {
			System.out.println("다시 입력");
		}

		// x,y가 범위를 벗어났을 때
		if (x < 0) { // 왼쪽 오버
			x++;
		} else if (x > wSize - 1) { // 오른쪽 오버
			x--;
		} else if (y < 0) { // 위쪽 오버
			y++;
		} else if (y > hSize - 1) {
			y--;
		}
//		System.out.println("x : " + x + " y :" + y); //확인용
	}
	
	private void clearScreen() {
		for(int i = 0; i<40;i++) {
			System.out.println("");
		}
	}

}
