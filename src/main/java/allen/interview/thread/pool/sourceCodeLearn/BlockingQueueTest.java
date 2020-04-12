package allen.interview.thread.pool.sourceCodeLearn;

import java.util.concurrent.SynchronousQueue;

/**
 * @author AllenWong
 *
 * 各种阻塞队列的测试
 *
 * @date 2020/4/12 3:58 PM
 */
public class BlockingQueueTest {
    public static void main(String[] args) {

        //同步移交队列,只有在有线程可以消费数据的时候才可以塞进去
        SynchronousQueue<String> synchronousQueue=new SynchronousQueue<>();
        /*
         * Inserts the specified element into this queue, if another thread is
         * waiting to receive it.
         */
        synchronousQueue.offer("a");
        synchronousQueue.offer("b");
        System.out.println(synchronousQueue.isEmpty());
        System.out.println(synchronousQueue.size());
        System.out.println(synchronousQueue.poll());



    }
}
