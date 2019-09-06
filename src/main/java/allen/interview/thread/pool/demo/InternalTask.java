package allen.interview.thread.pool.demo;

/**
 * @author WangJianQiang
 * @Description:
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
