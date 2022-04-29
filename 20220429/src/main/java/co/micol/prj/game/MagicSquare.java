package co.micol.prj.game;

import java.util.InputMismatchException;
import java.util.Scanner;

//n^2의 수를 가로 세로 대각선 방향의 수를 더하면 모두 같은 수가 오도록 n x n 행렬에 배열한 것
//n이 홀수이면 마방진 알고리즘 가능**
//첫 행의 가운데에 1을 넣음
//다음 숫자를 대각선 방향으로 오른쪽 위 칸에 넣는다
// if(y < 0) 일 때 y = n
// if(x > n) 일 때 x = 0
//도착한 위치에서 오른쪽 위로 다음 숫자 삽입
//가려는 곳에 숫자가 있거나 x,y가 둘 다 행 밖으로 나가면 아래로

public class MagicSquare {
	private Scanner scn = new Scanner(System.in);
	private int x = 0;
	private int y = 0;
	private int no;
	int size = -1;
	private int[][] square;

	public void run() {
		System.out.println("마방진 게임을 시작합니다.");
		System.out.print("n을 입력해주세요 (홀수) >>> ");
		try {
			size = scn.nextInt();
		} catch (InputMismatchException e) {
			scn.nextLine();
		}

		if (size % 2 == 0) {
			System.out.println("다시 입력해주세요!");
		} else {
			System.out.println(size + "X" + size + "의 마방진을 출력합니다.");
			square = new int[size][size];
			// 초기값
			x = 1; // 1
			y = size / 2 + 1; // 2
			no = 1;
			for (int i = 0; i < size * size; i++) {
				magicSquare(x, y);
			}
			// 행렬 출력
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					System.out.print("[" + square[i][j] + "]");
				}
				System.out.println();
			}
		}
	}

	public void ms() {
		int[][] arr = new int[size][size];
		int no = 1; //첫 값
		for (int i = 0, j = size / 2 ; no <= size * size; no++) {
			arr[i][j] = no;
			if (no % size == 0) {
				i++;
			} else {
				i--;
				j++;
				if (i < 0) 
					i = size - 1;
				if (j > size - 1)
					j = 0;
			}
		}
		toPrint(arr);
	}

	private void toPrint(int[][] result) {
		for (int[] num : result) {
			for (int n : num) {
				System.out.printf("%3d",n);
			}
			System.out.println();
		}
	}

	public void magicSquare(int x, int y) {
		int nextX = x + 1;
		int nextY = y + 1;

		while (true) {
			if (nextX > size && nextY < 0 || square[nextX][nextY] != 0) {
				// 행열 둘 다 오버했을 때, 다음 가야할 곳이 비어있지 않을 때
				// 한 열 아래로 가기
				y++;
			} else if (nextX > size) {
				x = 0;
			} else if (nextY < 0) {
				y = size;
			}

			if (square[nextX][nextY] == 0) {
				square[nextX][nextY] = no;
				no++;
				x = nextX;
				y = nextY;
				break;
			}
		}
	}

}
