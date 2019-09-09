package allen.concurrency.aqs;


import java.util.concurrent.*;

/**
 * FutureTask 将Callable和Future融合到一起的方法,可以让我们更加方便的使用
 * 需要用到其他线程执行结果的时候就调用get方法,未完成的时候会阻塞等待
 */
public class FutureTaskExample2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //直接新建一个FutureTask类
        FutureTask futureTask=new FutureTask(() -> {
            System.out.println("callable is running!");
            Thread.sleep(5000);
            return "Done";
        });
        //自定义一个线程池
        ExecutorService executorService=Executors.newCachedThreadPool();
        System.out.println("main thread is start.......");
        //提交一个实现callable接口的类,执行任务
        executorService.submit(futureTask);
        System.out.println("some result information is:::"+futureTask.get());
    }
}
