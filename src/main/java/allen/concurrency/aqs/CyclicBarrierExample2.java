package allen.concurrency.aqs;

import java.util.concurrent.*;

/**
 * 循环使用平常,使用场景: 主要是多个线程全部都就绪的时候才可以继续执行
 */
public class CyclicBarrierExample2 {
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
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            });

        }
        exec.shutdown();
    }

    private static void race(int threadNum) throws InterruptedException, BrokenBarrierException, TimeoutException {
        Thread.sleep(1000);
        System.out.println("is ready:" + threadNum);
        try{
            cyclicBarrier.await(2000,TimeUnit.MILLISECONDS);
        }catch (Exception e){
            System.out.println("do nothing");
        }
        System.out.println("continue:" + threadNum);
    }
}
