package allen.interview.JavaAlgo;

/**
 * 选择排序
 * 选择出最小的移动到首位，依次去选择最小的
 *
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月01日 16:43
 */
public class Selection<T extends Comparable<T>> extends Sort<T> {
	@Override
	public void sort(T[] nums) {
		int Len = nums.length;
		for (int i = 0; i < Len - 1; i++) {
			int min = i;
			for (int j = i + 1; j < Len; j++) {
				if (less(nums[j], nums[min])) {
					min = j;
				}
			}
			swap(nums, i, min);
		}
	}
}
