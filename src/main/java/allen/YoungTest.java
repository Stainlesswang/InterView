package allen;

import allen.interview.JavaAlgo.leecode.DuplicateNum;

import java.util.Arrays;

public class YoungTest {

	public static void main(String[] args) {
		int nums[] = {0, 1, 2, 3, 4, 5, 4};
		int duplication[] = new int[1];
		//判断是否含有重复数字
		boolean ishaveDuplicateNumber=DuplicateNum.duplicate(nums, nums.length, duplication);
		System.out.println(Arrays.toString(duplication));
	}
}