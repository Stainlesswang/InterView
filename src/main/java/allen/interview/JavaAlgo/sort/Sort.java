package allen.interview.JavaAlgo.sort;

/**
 * 我们规定Sort接口来定义排序规范，并且待排序元素需要实现Comparable接口
 * 该接口有 compareTo()方法，可以用来比较两个元素的大小
 * <p>
 * 接口中两个辅助函数less()、swap() 是比较 交换的两个基本操作
 * 用来增加代码可读性，并且一个代码的复杂度就是这两种基本操作的
 * 次数，所以也利于构建基本性能模型
 *
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月01日 14:43
 */
public abstract class Sort<T extends Comparable<T>> {

	public abstract void sort(T[] nums);

	/**
	 * @param t1 元素1 需要机场Comparable接口
	 * @param t2 元素2 需要机场Comparable接口
	 * @return boolean t1<t2 返回true 否则返回false
	 * @author WangJianQiang
	 * @date 2018年12月01日 下午03:47:25
	 */
	protected boolean less(T t1, T t2) {
		return t1.compareTo(t2) < 0;
	}

	/**
	 * @param a 待排序数组
	 * @param i 位置1
	 * @param j 位置2
	 * @author WangJianQiang
	 * @date 2018年12月01日 下午03:52:04
	 */
		protected void swap(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
