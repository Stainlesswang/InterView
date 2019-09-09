package allen.interview.thread.pool.sourceCodeLearn;

import java.text.MessageFormat;
import java.util.concurrent.*;

/**
 * java.util.concurrent.Executors 包下的 newSingleThreadExecutor 创建线程池使用的例子
 * 该例子场景描述: 简单 做1000次加一操作
 *
 */
public class SingleThreadPoolExample {
    /**
     * 首先贴出使用ThreadPoolExecutor构造函数创建的四个参数我的天啊
     *  return new ThreadPoolExecutor(1, 1,
     *                                       0L, TimeUnit.MILLISECONDS,
     *                                       new LinkedBlockingQueue<Runnable>());
     * 核心线程数: 1
     * 最大线程数量:1
     * 线程最大空闲时间:0
     * 空闲时间单位:milliSeconds
     * 阻塞队列: LinkedBlockingQueue 一个不限制大小的队列(int 最大值的容量)
     */
    private static ExecutorService singleThreadPool=Executors.newSingleThreadExecutor();

    private static int count =0;

    public static void main(String[] args) throws InterruptedException {
        //信号量控制最大并发量200,把信号量理解成车库门卫就可以了 总共两百个车位可以使用
        Semaphore semaphore = new Semaphore(1);
        //倒数阀门,类似于3 ,2, 1 芝麻开门的功能, 只有这100个线程每个都报数了(也就是执行countDown方法)
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            //提交线程,只有一个线程在执行,所以,其他提交的任务都在队列中等待,线程去处理
            singleThreadPool.submit(() -> {
                try {
                    semaphore.acquire();//获取到信号量才可以进行加1操作
                    Thread.sleep(8);//模拟一个耗时操作,世界上最难的事是装作自己很忙的样子
                    add();
                    semaphore.release();//执行完释放信号量
                    countDownLatch.countDown();//不要忘了报数
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();//等到倒数数量执行为0,才会执行下边的代码  自己可以注释掉试下
        System.out.println(MessageFormat.format("count :{0}", String.valueOf(count)));
        singleThreadPool.shutdown();//测试完成关闭资源,不然main方法会一直运行
    }

    /**
     * 加上synchronized关键字保证
     */
    public static synchronized void add() {
        count++;
    }

}
