package allen.concurrency.aqs;

import java.util.concurrent.*;

/**
 * 循环使用平常,使用场景: 主要是多个线程全部都就绪的时候才可以继续执行
 *
 * 创建的时候可以定义当准备好的时候需要首先做的事情,定义成一个runnable作为构造函数的参数传进去
 */
public class CyclicBarrierExample3 {
    private static  final CyclicBarrier cyclicBarrier =new CyclicBarrier(5,()-> System.out.println("callback is running!"));

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
            cyclicBarrier.await();
        }catch (Exception e){
            System.out.println("do nothing");
        }
        System.out.println("continue:" + threadNum);
    }
}
