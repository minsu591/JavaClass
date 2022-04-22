package co.micol.prj;

public class SelectionSort {
	// 멤버변수로 정의한건 초기값 필요 X
	// 내부적으로 사용할건 getter,setter 필요X
	private int i;
	private int j;
	private int min;// 작은값의 인덱스를 담음

	public void sort(int[] arr) {
		//초기 데이터 출력
		arrPrint(arr);
		//SelectSort 실행
		for (i = 0; i < arr.length-1; i++) {
			min = i;
			for (j = i + 1; j < arr.length; j++) { // 요소 비교, 본인과는 비교할 필요없음
				if (arr[min] > arr[j]) {
					min = j;
				}
			}
			if (min != i) { // swap -> 한 번만 수행하면 됨
				int temp = arr[i];
				arr[i] = arr[min];
				arr[min] = temp;
			}
		}
		//결과 데이터 출력
		resultPrint(arr);
	}
	

	private void resultPrint(int[] arr) {
		System.out.println("=== 결과 데이터 ===");
		for(int i : arr) {
			System.out.print(i+" ");
		}
		System.out.println();
	}


	private void arrPrint(int[] arr) { //내부에서 사용하는거니까 private
		System.out.println("=== 초기 데이터 ===");
		for(int i : arr) {
			System.out.print(i+" ");
		}
		System.out.println();
		System.out.println("==================");
	}


	@Override
	public String toString() {
		return "SelectionSort [i=" + i + ", j=" + j + ", min=" + min + "]";
	}
}
