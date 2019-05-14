package allen.interview.JavaAlgo.sort.归并;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月11日 14:10
 */
public class Up2DownMergeSort<T extends Comparable<T>> extends MergeSort<T> {
	@Override
	public void sort(T[] nums) {
		aux = (T[]) new Comparable[nums.length];
		sort(nums, 0, nums.length - 1);
	}

	private void sort(T[] nums, int l, int h) {
		if (h <= l) {
			return;
		}
		int mid = l + (h - l) / 2;
		sort(nums, l, mid);
		sort(nums, mid + 1, h);
		merge(nums, l, mid, h);
	}
}
