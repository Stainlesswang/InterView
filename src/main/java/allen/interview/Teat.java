package allen.interview;

import allen.interview.JavaAlgo.Bubble;
import allen.interview.JavaAlgo.Selection;

import java.util.Arrays;

public class Teat {


	public static void main(String[] args) {
		Integer[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
		/*选择排序测试*/
//		Selection<Integer> selection=new Selection<>();
//		selection.sort(a);
		/*冒泡排序测试*/
		Bubble<Integer> bubble=new Bubble<>();
		bubble.sort(a);

		System.out.println(Arrays.asList(a));
	}


}

