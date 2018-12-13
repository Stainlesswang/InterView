package allen.interview.JavaAlgo;

/**
 * 归并排序 合并排序 merge
 * 思想：将数组分成两部分，分别对其进行排序 然后合并
 * 这个思想很重要，现在很重要的分布式计算思想不就是
 * 异曲同工么，将一个繁重的任务分开，分别交给不同的机器，然后汇总合并各自的结果
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月07日 15:12
 */
public abstract class MergeSort<T extends Comparable<T>> extends Sort<T> {
	protected T[] aux;


	protected void merge(T[] nums, int l, int m, int h) {

		int i = l, j = m + 1;

		for (int k = l; k <= h; k++) {
			aux[k] = nums[k]; // 将数据复制到辅助数组
		}

		for (int k = l; k <= h; k++) {
			if (i > m) {
				nums[k] = aux[j++];//直接把右边数组全部拷贝

			} else if (j > h) {
				nums[k] = aux[i++];//直接把左边数组全部拷贝

			} else if (aux[i].compareTo(nums[j]) <= 0) {
				nums[k] = aux[i++]; // 左边较小,赋值

			} else {
				nums[k] = aux[j++]; // 右边较小,赋值
			}
		}
	}
}
