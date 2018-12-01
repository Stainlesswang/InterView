package allen.interview.JavaAlgo;

/**
 * 我们规定Sort接口来定义排序规范，并且待排序元素需要实现Comparable接口
 * 该接口有 compareTo()方法，可以用来比较两个元素的大小
 * <p>
 * 接口中两个辅助函数less()、swap() 是比较 交换的两个基本操作
 * 用来增加代码可读性，并且一个代码的复杂度就是这两种基本操作的
 * 次数，所以也利于构建基本性能模型
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月01日 14:43
 */
public abstract class Sort<T extends Comparable<T>> {

	public abstract void sort(T[] nums);

	/**
	 * 比较两个元素大小，t1<t2返回true
	 *
	 * @return boolean
	 * @author WangJianQiang
	 * @date
	 * @Param t1
	 * @Param t2
	 */
	protected boolean less(T t1, T t2) {
		return t1.compareTo(t2) < 0;
	}

	protected void swap(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
