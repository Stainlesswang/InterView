package allen.concurrency.lock;

import java.text.MessageFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 第二点例子使用, 如何使用Condition类
 * 2.可以使用Condition类,可以分组唤醒需要唤醒的锁
 */
public class LockExample3 {
    //请求总数
    private static int clientTotal = 5000;
    //总线程数
    private static int threadTotal = 200;

    private static int count =0;

    private final static ReentrantLock reentrantLock=new ReentrantLock();
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
        reentrantLock.lock();
        try{
            count++;
        }finally {
            reentrantLock.unlock();
        }
    }
}
