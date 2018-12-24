package allen.interview.JavaAlgo.sort;

/**
 * 直接插入排序
 * 每次都将当前元素插入到左侧已经有序的数组中，使得插入后左侧依然有序
 *
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月07日 11:11
 */
public class Insertion<T extends Comparable<T>> extends Sort<T> {
	@Override
	public void sort(T[] nums) {
		int N = nums.length;
		for (int i = 1; i < N; i++) {
			for (int j = i; j > 0; j--) {
				if (less(nums[j], nums[j - 1])) {
					swap(nums, j, j - 1);
				}
			}
		}
	}
}
