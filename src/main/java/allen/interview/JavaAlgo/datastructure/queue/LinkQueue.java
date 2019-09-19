package allen.interview.JavaAlgo.datastructure.queue;


/**
 * 使用链表实现一个队列
 * @author AllenWong
 * @date 2019/9/19 10:25 AM
 */
public class LinkQueue<T> implements Queue<T>{

    private Node head;
    private Node rear;
    private int size;

    /**
     * 初始化一个链表,初始状态 头节点 尾节点 都为null
     */
    public LinkQueue(){
        head=null;
        rear=null;
        size=0;
    }

    /**
     * 增加一个数据
     * @param t
     */
    @Override
    public void add(T t) {
        Node node=new Node(t);
        if (isEmpty()){
            head=node;//如果队列是空的,头节点就是新加入的这个节点
        }else{
            rear.next=node;//在当前尾节点的后边追加
        }
        rear=node;//尾节点更新为当前新建的这个
        size++;
    }

    @Override
    public T pop() {
        T temp;
        if (isEmpty()){
            temp=null;
        }else{
            temp=head.data;
            head=head.next;
            size--;
        }
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public T front() {
        return head.data;
    }

    @Override
    public int size() {
        return size;
    }

    class Node{
        T data;
        Node next;
        public Node(){}
        public Node(T t){
            this.data=t;
        }
    }
}
