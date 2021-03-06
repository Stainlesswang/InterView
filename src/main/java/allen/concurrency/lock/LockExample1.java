package allen.concurrency.lock;

import java.text.MessageFormat;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 如何使用的例子
 * 首先他是jdk层面实现的锁,必须用代码加锁和解锁,
 * 并且和synchronized 相比较 特殊之处在于以下三点
 * 1.能够指定 公平锁 或者 非公平锁
 * 2.可以使用Condition类,可以分组唤醒需要唤醒的锁
 * 3.提供了一种中断等在锁的机制
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

    public static  void add() {
        reentrantLock.lock();
        try{
            count++;
        }finally {
            reentrantLock.unlock();
        }
    }
}
