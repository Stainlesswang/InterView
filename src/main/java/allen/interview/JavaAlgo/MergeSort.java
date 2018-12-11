package allen.interview.JavaAlgo;

/**
 * 归并排序 合并排序 merge
 * 思想：将数组分成两部分，分别对其进行排序 然后合并
 * 这个思想很重要，现在很重要的分布式计算思想不就是
 * 异曲同工么，将一个繁重的任务分开，分别交给不同的机器，然后汇总合并各自的结果
 *
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月07日 15:12
 */
public abstract class MergeSort<T extends Comparable<T>> extends Sort<T> {
	T[] aux;

	protected void merge(T[] nums, int start, int mid, int end) {
		int i = start, j = mid + 1;

		for (int k = start; k <= end; k++) {
			aux[k] = nums[k]; // 将数据复制到辅助数组
		}

		for (int k = start; k <= end; k++) {
			if (i > mid) {
				nums[k] = aux[j++];

			} else if (j > end) {
				nums[k] = aux[i ++];

			} else if (aux[i].compareTo(nums[j]) <= 0) {
				nums[k] = aux[i++]; // 先进行这一步，保证稳定性

			} else {
				nums[k] = aux[j++];
			}
		}
	}
}
