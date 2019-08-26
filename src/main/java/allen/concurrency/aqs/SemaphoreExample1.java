package allen.concurrency.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量的使用例子一
 */
public class SemaphoreExample1 {
    private final static int threadCount=200;

    public static void main(String[] args) throws InterruptedException {
        //unlimited size thread pool,but will reuse previously constructed threads when they are available
        ExecutorService executorService=Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(21);//最多允许20个线程获取资源进行处理

        for (int i = 0; i <threadCount ; i++) {
            final int theedNumb=i;
            executorService.execute(()->{
                try {
                    semaphore.acquire(3);//每次获取多个信号量
                    test(theedNumb);
                    semaphore.release(3);//释放多个信号量
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        System.out.println(threadNum);
    }
}
