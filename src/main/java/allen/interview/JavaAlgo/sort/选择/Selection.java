package allen.interview.JavaAlgo.sort.选择;

import allen.interview.JavaAlgo.sort.Sort;

/**
 * 选择排序
 * 选择出最小的移动到首位，依次去选择最小的
 *
 * 时间复杂度分析: 共两个循环
 * 外层循环: 循环N次
 * 内层循环:  max:N  min:1  avg:(N+1)/2
 * 所以 N(N+1)/2   等价于 O(N^2)   最好的情况,内循环第一一次,min未变 最小 O(N)
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
