package allen.interview.JavaAlgo.leecode;

/**
 * @author WangJianQiang
 * @Description:查找重复数字 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * example：
 * Input:
 * {2, 3, 1, 0, 2, 5}
 * Output:2
 * 要求：时间复杂度O(n),空间复杂度为O(1)
 * @date 2019年03月11日 14:24
 */
public class DuplicateNum {
	public static boolean duplicate(int[] nums, int length, int[] duplication) {
		if (nums == null || length <= 0)
			return false;
		for (int i = 0; i < length; i++) {
			while (nums[i] != i) {
				if (nums[i] == nums[nums[i]]) {
					duplication[0] = nums[i];
					return true;
				}
				swap(nums, i, nums[i]);
			}
		}
		return false;
	}

	private static void swap(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
}
