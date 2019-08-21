package allen.interview.thread.threadLocal;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;

import java.text.MessageFormat;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * 模拟线程并发的类
 */
public class ThreadLocalTest {
    Logger logger= (Logger) LogFactory.getLog(this.getClass());
    //请求总数
    public static int clientTotal = 10;
    //总线程数
    public static int threadTotal = 2;

    private static LongAdder count =new LongAdder();//

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
//            int finalI = i;
            int finalI = i;
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    ThreadLocalDemo.addUser(String.valueOf(finalI));
                    System.out.println(ThreadLocalDemo.getUser());
                    count.increment();
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        System.out.println(MessageFormat.format("count :{0}", String.valueOf(count)));
        executorService.shutdown();
    }
//

    public static void add() {
        count.decrement();
    }

}
