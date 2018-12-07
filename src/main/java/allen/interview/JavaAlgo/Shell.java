package allen.interview.JavaAlgo;

/**
 * 希尔排序
 * 对于大规模的数组，插入排序很慢，因为它只能交换相邻的元素
 * 每次只能将逆序数量减1，希尔排序的出现就是优化这方面，它
 * 通过交换不相邻的数字，每次交换将逆序减少大于1
 *
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月07日 14:13
 */
public class Shell<T extends Comparable<T>> extends Sort<T> {
	@Override
	public void sort(T[] nums) {
		int N = nums.length;
		int h = 1;
		//首先确定间隔
		while (h < N / 3) {
			h = 3 * h + 1;//1,4,13,40......
		}
		//停止条件
		while (h >= 1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && less(nums[j], nums[j - h]); j -= h) {
					swap(nums, j, j - h);
				}
			}
			h = h / 3;
		}
	}
}
