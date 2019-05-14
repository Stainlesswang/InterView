package allen.interview.JavaAlgo.sort.插入;

/**
 * 插入类排序------shell排序
 * 按照一定间隔 一般取 n/2 为开始的间隔，然后对间隔中的一组数据执行直接插入排序
 */
public class InsertShellSort {

	public static void main(String[] args) {
		int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
		System.out.println("排序之前：");
		for (int anA : a) {
			System.out.print(anA + " ");
		}
		// 希尔排序
		int d = a.length;
        do {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < a.length; i = i + d) {
                    int temp = a[i];
                    int j;
                    for (j = i - d; j >= 0 && a[j] > temp; j = j - d) {
                        a[j + d] = a[j];
                    }
                    a[j + d] = temp;
                }
            }
        } while (d != 1);
		System.out.println();
		System.out.println("排序之后：");
		for (int anA : a) {
			System.out.print(anA + " ");
		}

	}

}
