package allen.interview.thread.pool.demo;

/**
 * @author WangJianQiang
 * @Description:
 * 将任务队列包装进来,run方法是从任务队里中取出来的Runnable的run方法在执行
 * 一个InternalTask就是一个Runnable的实现
 * @date 2018年11月13日 16:12
 */
public class InternalTask implements Runnable {
	//
	private final RunnableQueue runnableQueue;
	private volatile boolean running = true;

	InternalTask(RunnableQueue runnableQueue) {
		this.runnableQueue = runnableQueue;
	}

	@Override
	public void run() {
		while (running && !Thread.currentThread().isInterrupted()) {
			try {
				Runnable task = runnableQueue.take();
				task.run();
				} catch (InterruptedException e) {
				running = false;
				break;
			}
		}
	}

	public void stop() {
		this.running = false;
	}
}
