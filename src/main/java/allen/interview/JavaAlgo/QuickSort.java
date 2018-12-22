package allen.interview.JavaAlgo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * QuickSort 快速排序，寻找一个基数进行分组，
 * 左边都是小于基数的，右边都是大于基数的
 * 然后分别对两个分组里的数据进行排序，排完后数组即有序
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月13日 9:35
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {
	@Override
	public void sort(T[] nums) {
		shuffle(nums);
		sort(nums, 0, nums.length - 1);
	}

	private void sort(T[] nums, int l, int h) {
		if (h <= l)
			return;
		int j = partition(nums, l, h);
		sort(nums, l, j - 1);
		sort(nums, j + 1, h);
	}

	private void shuffle(T[] nums) {
		List<Comparable> list = Arrays.asList(nums);
		Collections.shuffle(list);
		list.toArray(nums);
	}
	private int partition(T[] nums, int l, int h) {
		int i = l, j = h + 1;
		T v = nums[l];
		while (true) {
			while (less(nums[++i], v) && i != h) ;
			while (less(v, nums[--j]) && j != l) ;
			if (i >= j)
				break;
			swap(nums, i, j);
		}
		swap(nums, l, j);
		return j;
	}
}
