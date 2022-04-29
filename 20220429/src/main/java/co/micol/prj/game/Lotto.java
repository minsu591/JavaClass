package co.micol.prj.game;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

//로또
//1~45 중
//한 행에 6개
//무작위 수이나 중복을 허용하지 않음
//-> 나온거 정렬
public class Lotto {
	private int[] lottoArr = new int[6];
	private int[] myLotto = new int[6];
	private Scanner scn = new Scanner(System.in);
	
	
	public void run() {
		System.out.println("[로또 게임]");
		lotto();
		
	}

	private void lotto() {
		// 로또
		int[] lottoArr = randomArr();
		int money = 0;
		int no = 0;
		System.out.print("돈을 입력해주세요 (한 판 : 1000원) >>> ");
		try {
			money = scn.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다! 다시 입력해주세요.");
			scn.nextLine();
		}		
		no = money/1000;
		for(int i = 0; i< no;i++) {
			System.out.println((i+1)+"번째 로또를 출력합니다.");
			int[] myLotto = randomArr();
			printArr(lottoArr,"당첨 번호");
			printArr(myLotto,"내 번호");
			int cnt = compLotto(lottoArr,myLotto);
			//당첨구문 출력
			System.out.println("번호" +cnt+"개가 맞았습니다!");
			System.out.println();
			
			//3초 대기
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//거스름돈 출력
		int change = money%1000;
		if(change > 0) {
			System.out.println("거스름돈 "+change+"원이 나왔습니다.");
		}

	}
	
	private int compLotto(int[] lottoArr, int[] myLotto) {
		int cnt = 0;
		for(int i = 0 ;i< lottoArr.length;i++) {
			for(int j = 0; j< myLotto.length;j++) {
				if(lottoArr[i]==myLotto[j]) {
					cnt++;
					break;
				}
			}
		}
		return cnt;
	}
	
	private void printArr(int[] arr, String name) {
		
		System.out.printf("%6s : ",name);
		System.out.print("[ ");
		for(int i =0 ; i<arr.length;i++) {
			System.out.printf("%2d ",arr[i]);
		}
		System.out.println("]");
		
	}
	
	private int[] randomArr() {
		int[] arr = new int[6];
		for (int i = 0; i < arr.length; i++) {
			int rnd = (int) (Math.random() * 45 + 1);
			boolean flag = true;
			for (int j = 0; j < i; j++) {
				if (arr[j] == rnd) {
					i--;
					flag = false;
					break;
				}
			}
			if (flag) {
				arr[i] = rnd;
			}
		}
		Arrays.sort(arr);
		return arr;
	}

}
