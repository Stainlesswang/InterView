package allen.interview.JavaAlgo;

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
	private void sort(T[] nums, int start, int end) {
		if (end <= start) {
			return;
		}
		int mid = start + (end - start) / 2;
		sort(nums, start, mid);
		sort(nums, mid + 1, end);
		merge(nums, start, mid, end);
	}
}
