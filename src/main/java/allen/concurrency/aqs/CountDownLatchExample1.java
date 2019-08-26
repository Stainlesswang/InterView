package allen.concurrency.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒数计数器开关 例子一
 * CountDownLatch
 */
public class CountDownLatchExample1 {
    private final static int threadCount=200;

    public static void main(String[] args) throws InterruptedException {
        //unlimited size thread pool,but will reuse previously constructed threads when they are available
        ExecutorService executorService=Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch=new CountDownLatch(threadCount);

        for (int i = 0; i <threadCount ; i++) {
            final int thereadNum=i;
            executorService.submit(()->{
                try {
                    test(thereadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("finish");
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        System.out.println(threadNum);
        Thread.sleep(100);
    }
}
