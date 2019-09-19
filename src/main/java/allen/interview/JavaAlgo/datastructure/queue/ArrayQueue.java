package allen.interview.JavaAlgo.datastructure.queue;

/**
 * 使用循环数组来实现一个队列,使用两个标志位代表头和尾部
 * 计算下标的时候使用取模的方式来保证位置坐标不超过数组的长度
 * @author AllenWong
 * @date 2019/9/19 8:32 PM
 */
public class ArrayQueue<T> implements Queue<T> {;

    private  T [] queue;

    private  int head;

    private  int rear;

    private  int capacity;

    private static int DEFAULT_SIZE=16;

    public ArrayQueue(){
        this(DEFAULT_SIZE);
    }
    public ArrayQueue(int initSize){
        if (initSize<=0) throw new RuntimeException("参数不合法!");
        capacity=initSize;
        queue= (T[]) new Object[initSize];
        head=rear=0;
    }

    @Override
    public void add(T t) {
        if (isFull()) throw new RuntimeException("The Queue is Full!");
        queue[rear]=t;
        int old=rear;
        rear=(++rear)%capacity;
        System.out.println("旧的尾部为"+old+"-下一个位置为"+rear);
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new RuntimeException("The Queue is Empty!");
        T t =queue[head];
        head=(++head)%capacity;
        return t;
    }

    @Override
    public boolean isEmpty() {
        return rear==head;
    }

    @Override
    public T front() {
        if (isEmpty()) throw new RuntimeException("The Queue is Empty!");

        return queue[head];
    }

    @Override
    public int size() {
        return (capacity+rear-head)%capacity;
    }

    private boolean isFull(){
        //本来应该在rear所在地判断 rear=head的时候队列满了, 但是很遗憾的是队列空的时候也是这么判断
        //所以提前判断队列满的状态,使用rear+1的位置对size取模桶head比较
        return (rear+1)%capacity==head;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue=new ArrayQueue<Integer>(5);
        arrayQueue.add(1);
        arrayQueue.add(2);
        arrayQueue.add(3);
        arrayQueue.add(4);
        System.out.println(arrayQueue.size());
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue.pop());
        arrayQueue.add(4);
        System.out.println(arrayQueue.pop());
        arrayQueue.add(4);
        arrayQueue.add(4);
        arrayQueue.add(4);
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue.pop());

    }

}
