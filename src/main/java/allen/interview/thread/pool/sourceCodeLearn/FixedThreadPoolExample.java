package allen.interview.thread.pool.sourceCodeLearn;

import java.text.MessageFormat;
import java.util.concurrent.*;

/**
 * java.util.concurrent.Executors 包下的 newFixedThreadPool使用例子
 * 该例子场景描述: 简单 做1000次加一操作
 *
 */
public class FixedThreadPoolExample {
    /**
     * 首先贴出使用ThreadPoolExecutor构造函数创建的四个参数我的天啊
     *  return new ThreadPoolExecutor(nThreads, nThreads,
     *                                       0L, TimeUnit.MILLISECONDS,
     *                                       new LinkedBlockingQueue<Runnable>());
     * 核心线程数: 必须设定的参数
     * 最大线程数量:和核心线程数相同
     * 线程最大空闲时间:0
     * 空闲时间单位:milliSeconds
     * 阻塞队列: LinkedBlockingQueue 一个不限制大小的队列(int 最大值的容量)
     */
    private static ExecutorService fixedThreadPool =Executors.newFixedThreadPool(5);

    private static int count =0;

    public static void main(String[] args) throws InterruptedException {
        //信号量控制最大并发量200,把信号量理解成车库门卫就可以了 总共两百个车位可以使用
        Semaphore semaphore = new Semaphore(20);
        //倒数阀门,类似于3 ,2, 1 芝麻开门的功能, 只有这1000个线程每个都报数了(也就是执行countDown方法)
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            //!!!!重点来了,向我们的线程池提交一个Runnable的run方法,首先看线程池中有没有空闲的已创建的线程
            //如果有使用现成的线程, 如果没有则创建新的线程来执行
            fixedThreadPool.submit(() -> {
                try {
                    semaphore.acquire();//获取到信号量才可以进行加1操作
                    Thread.sleep(80);//模拟一个耗时操作,世界上最难的事是装作自己很忙的样子
//                    try{
                        int a=2/0;
//                    }catch (Exception e){
//                        System.out.println(e.getMessage());
//                    }
                    add();

                    semaphore.release();//执行完释放信号量
                    countDownLatch.countDown();//不要忘了报数
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            int threadCount = ((ThreadPoolExecutor) fixedThreadPool).getActiveCount();
            long taskCount = ((ThreadPoolExecutor) fixedThreadPool).getTaskCount();
            System.out.println(MessageFormat.format("当前活跃线程数 :{0}", String.valueOf(threadCount)));
            System.out.println(MessageFormat.format("已完成任务 :{0}", String.valueOf(taskCount)));
        }
        countDownLatch.await();//等到倒数数量执行为0,才会执行下边的代码  自己可以注释掉试下
        System.out.println(MessageFormat.format("count :{0}", String.valueOf(count)));
        fixedThreadPool.shutdown();//测试完成关闭资源,不然main方法会一直运行
    }

    /**
     * 加上synchronized关键字保证
     */
    public static synchronized void add() {
        count++;
    }

}
