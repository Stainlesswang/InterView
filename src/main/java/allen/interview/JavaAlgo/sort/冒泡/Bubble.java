package allen.interview.JavaAlgo.sort.冒泡;

import allen.interview.JavaAlgo.sort.Sort;

/**
 * 冒泡排序
 * 一轮循环后，未排序中最大的转移到最右端。 没有移动则说明是有序的
 *
 * 时间复杂度:
 * 外层循环执行N-1次.  内存循环最多N次,最少1次  avg=(N+1)/2
 * 所以总共执行次数 (N^2-1)/2.   去掉常数去最高阶 则为O(N^2)
 *
 * 最坏O(n^2)  最好的情况:O(n) 平均时间复杂度 O(n^2)
 *
 *
 * @date 2018年12月07日 10:05
 */
public class Bubble<T extends Comparable<T>> extends Sort<T> {
	@Override
	public void sort(T[] numbs) {
		int N = numbs.length;
		//用来记录对一次未排序遍历后是否有移动
		boolean hasSorted = false;
		for (int i = N - 1; i > 0 && !hasSorted; i--) {
			hasSorted = true;
			for (int j = 0; j < i; j++) {
				if (less(numbs[j + 1], numbs[j])) {
					hasSorted = false;
					swap(numbs, j, j + 1);
				}
			}
		}
	}
}
