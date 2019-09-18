package allen.interview.JavaAlgo.datastructure.queue;

/**
 * @author AllenWong
 * @date 2019/9/18 8:28 PM
 */
public class Queue<E>{
    private int front;//队头的位置,只允许删除

    private int tail;//队尾,只允许增加

    private int max_size=16;

    private Object [] data;

    public Queue(){
        this(10);
    }
    public Queue(int size){
        if (size<0) throw new IllegalArgumentException("参数不合法"+size);
        this.max_size=size;
        front=tail=0;
        data=new Object[size];
    }
    public boolean add(E e){
        if (data.length>=max_size) throw new RuntimeException("超出了最大容量");
        data[tail++]=e;
        return true;
    }

    public E pop(){
        if (isEmpty()) throw new RuntimeException("队列空了,没啥数据");
        E e= (E) data[front];
        //此处使用链表比较好一点
        data[front++]=null;
        return e;

    }
    //判断是否为空
    public boolean isEmpty(){
        return tail == front;
    }


}
