package allen.interview;

import allen.interview.JavaAlgo.*;

import java.util.Arrays;
import java.util.Random;

public class Teat {


	public static void main(String[] args) {
		Integer[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
		/*选择排序测试*/
//		Selection<Integer> selection=new Selection<>();
//		selection.sort(a);
		/*冒泡排序测试*/
//		Bubble<Integer> bubble=new Bubble<>();
//		bubble.sort(a);
		/*插入排序测试*/
//		Insertion<Integer> insertion=new Insertion<>();
//		insertion.sort(a);
		/*希尔排序测试*/
//		Shell<Integer> shell=new Shell<>();
//		shell.sort(a);
		/*归并排序测试*/
//		Up2DownMergeSort<Integer> shell=new Up2DownMergeSort<>();
//		shell.sort(a);
//		int N=a.length;
//		for (int sz = 1; sz < N; sz += sz) {
//			System.out.println(sz);
//			for (int lo = 0; lo < N - sz; lo += sz + sz) {
//				System.out.print(lo+"  ");
//			}
//		}
		int N=100;
		for (int sz = 1; sz < N; sz ++) {
			Random random=new Random();
			System.out.println(random.nextInt(9) + 1 + "");
		}
//		System.out.println(Arrays.asList(a));
	}


}

