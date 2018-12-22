package allen.interview.thread.chapter08;

import java.util.concurrent.TimeUnit;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月16日 16:26
 */
public class ThreadPoolTest {
	public static void main(String[] args) throws InterruptedException {
		final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);
		for (int i = 0; i < 20; i++) {//
			threadPool.execute(()->{
				try {
					TimeUnit.SECONDS.sleep(10);
					System.out.println(Thread.currentThread().getName() + " is running and done");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}

		for (;;){
			System.out.println("ActivityCount::" + threadPool.getActivityCount());
			System.out.println("QueueSize::" + threadPool.getQueueSize());
			System.out.println("CoreSize::" + threadPool.getCoreSize());
			System.out.println("MaxSize::" + threadPool.getMaxSize());
			System.out.println("=================================");
			TimeUnit.SECONDS.sleep(5);
		}
	}
}
