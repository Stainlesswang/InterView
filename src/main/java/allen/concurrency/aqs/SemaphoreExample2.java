package allen.concurrency.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量的使用例子一
 */
public class SemaphoreExample2 {
    private final static int threadCount=200;

    public static void main(String[] args){
        //unlimited size thread pool,but will reuse previously constructed threads when they are available
        ExecutorService executorService=Executors.newCachedThreadPool();
        final Semaphore semaphore=new Semaphore(21);//最多允许20个线程获取资源进行处理

        for (int i = 0; i <threadCount ; i++) {
            final int theedNumb=i;
            executorService.execute(()->{
                try {
                    if (semaphore.tryAcquire()){//尝试获取一个许可,获取到了可以执行
                        //其中的tryAcquire方法可以设置一次获取几个, 并且可以设置在多长时间里边获取相应的许可,可以等待其他许可释放许可
                        test(theedNumb);
                        semaphore.release();
                    }

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
