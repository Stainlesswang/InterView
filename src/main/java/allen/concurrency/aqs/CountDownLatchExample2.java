package allen.concurrency.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 倒数计数器开关 例子2
 * CountDownLatch  指定时间完成任务,未完成丢弃服务
 * @author wangjianqiang
 */
public class CountDownLatchExample2 {
    private final static int threadCount=200;

    public static void main(String[] args) throws InterruptedException {
        //unlimited size thread pool,but will reuse previously constructed threads when they are available
        ExecutorService executorService=Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch=new CountDownLatch(threadCount);

        for (int i = 0; i <threadCount ; i++) {
            final int theedNumb=i;
            executorService.execute(()->{
                try {
                    test(theedNumb);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        //可以指定等待时间
        countDownLatch.await(10,TimeUnit.MILLISECONDS);
        System.out.println("finish");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);

        System.out.println(threadNum);
    }
}
