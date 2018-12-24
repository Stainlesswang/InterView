package allen.interview.JavaAlgo.sort;

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
		//从前向后循环数组
		for (int i = 1; i < a.length; i++) {
			int temp = a[i];
			int j;
			//从当前位置向前遍历
			for (j = i - 1; j >= 0; j--) {
				//遇到大的向后移动
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
