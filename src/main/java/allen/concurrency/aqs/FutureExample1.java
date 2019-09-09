package allen.concurrency.aqs;


import java.util.concurrent.*;

/**
 * future 接口可以用来接收其他线程返回的结果
 * 需要用到其他线程执行结果的时候就调用get方法,未完成的时候会阻塞等待
 */
public class FutureExample1 {
    /**
     * 自定义一个实现callable接口的类
     */
    static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("callable is running!");
            Thread.sleep(5000);
            return "Done";
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //自定义一个线程池
        ExecutorService executorService=Executors.newCachedThreadPool();
        System.out.println("main thread is start.......");
        //提交一个实现callable接口的类,执行任务
        Future<String> future=executorService.submit(new MyCallable());
        System.out.println("some result information is:::"+future.get());
    }
}
