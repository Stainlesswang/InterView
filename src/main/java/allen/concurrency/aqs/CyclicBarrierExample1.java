package allen.concurrency.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 循环屏障,使用场景: 主要是多个线程全部都就绪的时候才可以继续执行
 */
public class CyclicBarrierExample1 {
    private static  final CyclicBarrier cyclicBarrier =new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec=Executors.newCachedThreadPool();
        for (int i = 0; i <10 ; i++) {
            final int threadNum=i;
            Thread.sleep(1000);
            exec.execute(()->{
                try {
                    race(threadNum);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
    }

    private static void race(int threadNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        System.out.println("is ready:" + threadNum);
        cyclicBarrier.await();
        System.out.println("continue:" + threadNum);
    }
}
