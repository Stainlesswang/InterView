package allen.interview.JavaAlgo;

/**
 * 插入类排序------直接插入排序
 * 从前到后一个个遍历，
 */
public class InsertDirectSort {
	public static void main(String[] args) {
		int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
		System.out.println("排序之前：");
		for (int anA : a) {
			System.out.print(anA + " ");
		}

		for (int i = 1; i < a.length; i++) {
			int temp = a[i];
			int j;
			for (j = i - 1; j >= 0; j--) {
				if (a[j] > temp) {
					a[j + 1] = a[j];
				} else {
					break;
				}
			}
			a[j + 1] = temp;
		}


		System.out.println("排序之后：");
		for (int anA : a) {
			System.out.print(anA + " ");
		}

	}
}
