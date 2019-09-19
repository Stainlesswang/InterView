package allen.interview.JavaAlgo.datastructure.queue;

/**
 * 首先定义队列操作的接口,不需要太复杂,简单的能够说明
 * 问题就可以了
 *
 * @author AllenWong
 * @date 2019/9/18 8:28 PM
 */
public interface Queue<E>{

    /**
     * 入队操作
     * 只能从队尾入队
     */
    public void add(E e);

    /**
     * 出队操作
     * @return 队头的元素,无数据返回null
     */
    public E pop();

    /**
     * 队列是否为空
     * @return
     */
    public boolean isEmpty();

    /**
     * 返回头不元素,但是不删除元素
     * @return
     */
    public E front();

    public int size();

}
