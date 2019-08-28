package allen.concurrency;

import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Logger;

import static com.mysql.cj.conf.PropertyKey.logger;

/**
 * 模拟线程并发的类
 */
public class ConcurrencyTest {
    //请求总数
    private static int clientTotal = 5000;
    //总线程数
    private static int threadTotal = 200;

    /**LongAdder 是在高并发计数的情况下如何提高性能在java8以后做的优化
     * 其主要思想是利用了concurrentHashMap分段锁的思想, 每个线程名称经过hash修改制定的cell下标的数据
     * 然后计算sum的时候对cell数据循环累加得到结果
     * 首先理解到这里. 以后详细使用再来翻翻
     * */
    private static LongAdder count =new LongAdder();//

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
//    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch latch = new CountDownLatch(5);
//        Service service = new Service(latch);
//        Runnable task = () -> service.exec();
//
//        for (int i = 0; i < 5; i++) {
//            Thread thread = new Thread(task);
//            thread.start();
//        }
//
//        System.out.println("main thread await. ");
//        latch.await();
//        System.out.println("main thread finishes await. ");
//    }

    public static void add() {
        count.decrement();
    }


    /**
     * 该类是测试 CountDownLatch 使用方法的
     * 主线程会等待 CountDownLatch实例的await()方法,等待五个线程执行完并减一到0 才执行
     * 有点像'3,2,1 芝麻开门'的功能
     */
    public static class Service {
        private CountDownLatch latch;

        public Service(CountDownLatch latch) {
            this.latch = latch;
        }

        public void exec() {
            try {
                System.out.println(Thread.currentThread().getName() + " execute task. ");
                sleep(2);
                System.out.println(Thread.currentThread().getName() + " finished task. ");
            } finally {
                latch.countDown();
            }
        }

        private void sleep(int seconds) {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
