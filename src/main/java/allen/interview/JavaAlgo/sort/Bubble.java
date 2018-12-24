package allen.interview.JavaAlgo.sort;

/**
 * 冒泡排序
 * 一轮循环后，未排序中最大的转移到最右端。 没有移动则说明是有序的
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月07日 10:05
 */
public class Bubble<T extends Comparable<T>> extends Sort<T> {
	@Override
	public void sort(T[] nums) {
		int N = nums.length;
		//用来记录对一次未排序遍历后是否有移动
		boolean hasSorted = false;
		for (int i = N - 1; i > 0 && !hasSorted; i--) {
			hasSorted = true;
			for (int j = 0; j < i; j++) {
				if (less(nums[j + 1], nums[j])) {
					hasSorted = false;
					swap(nums, j, j + 1);
				}
			}
		}
	}
}
