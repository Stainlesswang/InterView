package allen.interview;

import allen.interview.JavaAlgo.Bubble;
import allen.interview.JavaAlgo.Insertion;
import allen.interview.JavaAlgo.Selection;
import allen.interview.JavaAlgo.Shell;

import java.util.Arrays;

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
		Shell<Integer> shell=new Shell<>();
		shell.sort(a);

		System.out.println(Arrays.asList(a));
	}


}

