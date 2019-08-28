package allen.concurrency.lock;

import java.text.MessageFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock 是什么: stampedLock 不仅实现了读锁和写锁,还实现了乐观写锁
 *
 * 当调用写锁加锁时会返回一个标记, 解锁的时候必须带上该标记才能够解锁
 */
public class LockExample4 {
    //请求总数
    private static int clientTotal = 5000;
    //总线程数
    private static int threadTotal = 200;

    private static int count =0;

    private final static StampedLock stampedLock=new StampedLock();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        System.out.println(MessageFormat.format("count :{0}", String.valueOf(count)));
    }

    public static  void add() {
        long stamp=stampedLock.writeLock();
        try{
            count++;
        }finally {
            stampedLock.unlock(stamp);
        }
    }
}
