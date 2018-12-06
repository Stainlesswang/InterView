package allen.interview;

import allen.interview.JavaAlgo.Selection;

import java.util.Arrays;

public class Teat {


	public static void main(String[] args) {
		Integer[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
		Selection<Integer> selection=new Selection<>();
		selection.sort(a);
		System.out.println(Arrays.asList(a));
	}


}

