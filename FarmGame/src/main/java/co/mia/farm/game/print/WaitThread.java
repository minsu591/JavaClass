package co.mia.farm.game.print;

import java.util.Scanner;

import co.mia.farm.game.status.StatusService;
import lombok.Data;

@Data
public class WaitThread implements Runnable {
	private Scanner scn = new Scanner(System.in);
	private int forNum;

	@Override
	public void run() {
		while(true) {
			String s = scn.nextLine();
			if(!s.isEmpty()) {
				System.out.println("체력 충전을 종료합니다.");
				StatusService.threadFlag = false;
				break;
			}
		}
	}

}
