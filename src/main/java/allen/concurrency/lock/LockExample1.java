package allen.concurrency.lock;

import java.text.MessageFormat;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟线程并发的类
 */
public class LockExample1 {
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

    public static void add() {
        reentrantLock.lock();
        try{
            count++;
        }finally {
            reentrantLock.unlock();
        }
    }
}
