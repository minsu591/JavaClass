package co.micol.prj.app;

import java.util.Arrays;

import co.micol.prj.SelectionSort;

public class MainApp {
	public static void main(String[] args) {
		SelectionSort selectSort = new SelectionSort(); //인스턴스 생성
		int data[] = {5,1,4,3,2};
		selectSort.sort(data);
//		Arrays.sort(data); //오름차순 정렬
	}
}
