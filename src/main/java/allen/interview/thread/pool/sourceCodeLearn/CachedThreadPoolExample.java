package allen.interview.thread.pool.sourceCodeLearn;

import java.text.MessageFormat;
import java.util.concurrent.*;

/**
 * java.util.concurrent.Executors 包下的 newCachedThreadPool使用例子
 * 该例子场景描述: 简单的使用到信号量 做1000次加一操作
 *
 */
public class CachedThreadPoolExample {
    /**
     * 首先贴出使用ThreadPoolExecutor构造函数创建的四个参数
     * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *                                       60L, TimeUnit.SECONDS,
     *                                       new SynchronousQueue<Runnable>());
     *
     * 核心线程数:0
     * 最大线程数量:int最大值,也就是说基本不限制
     * 线程最大空闲时间:60
     * 空闲时间单位:seconds
     * 阻塞队列: SychronousQueue 一个不保存Runnable的队列,只有当有另外一个线程做取操作的时候才可以入队里,并且默认阻塞的线程
     * 是非公平形式的阻塞队列
     */
    private static ExecutorService cachedThreadPool=Executors.newCachedThreadPool();

    private static int count =0;

    public static void main(String[] args) throws InterruptedException {
        //信号量控制最大并发量200,把信号量理解成车库门卫就可以了 总共两百个车位可以使用
        Semaphore semaphore = new Semaphore(200);
        //倒数阀门,类似于3 ,2, 1 芝麻开门的功能, 只有这1000个线程每个都报数了(也就是执行countDown方法)
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            //!!!!重点来了,向我们的线程池提交一个Runnable的run方法,首先看线程池中有没有空闲的已创建的线程
            //如果有使用现成的线程, 如果没有则创建新的线程来执行
            cachedThreadPool.submit(() -> {
                try {
                    semaphore.acquire();//获取到信号量才可以进行加1操作
                    Thread.sleep(1000);//模拟一个耗时操作,世界上最难的事是装作自己很忙的样子
                    add();
                    semaphore.release();//执行完释放信号量
                    countDownLatch.countDown();//不要忘了报数
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            int threadCount = ((ThreadPoolExecutor)cachedThreadPool).getActiveCount();
            long taskCount = ((ThreadPoolExecutor)cachedThreadPool).getTaskCount();
            System.out.println(MessageFormat.format("当前活跃线程数 :{0}", String.valueOf(threadCount)));
            System.out.println(MessageFormat.format("已完成任务 :{0}", String.valueOf(taskCount)));
        }
        countDownLatch.await();//等到倒数数量执行为0,才会执行下边的代码  自己可以注释掉试下
        System.out.println(MessageFormat.format("count :{0}", String.valueOf(count)));
        cachedThreadPool.shutdown();//测试完成关闭资源,不然main方法会一直运行
    }

    /**
     * 加上synchronized关键字保证
     */
    public static synchronized void add() {
        count++;
    }

}
