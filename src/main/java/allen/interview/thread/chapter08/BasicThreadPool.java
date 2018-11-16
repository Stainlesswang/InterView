package allen.interview.thread.chapter08;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月12日 15:24
 */
public class BasicThreadPool extends Thread implements ThreadPool {
	//初始化大小
	private final int initSize;
	//线程池最大容量
	private final int maxSize;
	//核心线程数量
	private final int coreSize;
	//当前活跃的线程数
	private int activityCount;
	//Thread工厂
	private final ThreadFactory threadFactory;
	//任务队列
	private final RunnableQueue runnableQueue;
	//线程池是否关闭
	private volatile boolean isShutdown = false;
	//存活时长
	private final long keepAliveTime;
	private final TimeUnit timeUnit;
	private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

	private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();
	private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultFactory();

	public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
		this(initSize, maxSize, coreSize, queueSize, DEFAULT_THREAD_FACTORY, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
	}

	private BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize, ThreadFactory threadFactory, DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
		this.initSize = initSize;
		this.maxSize = maxSize;
		this.coreSize = coreSize;
		this.threadFactory = threadFactory;
		this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
		this.keepAliveTime = keepAliveTime;
		this.timeUnit = timeUnit;
		init();
	}

	private void init() {
		start();
		for (int i = 0; i < initSize; i++) {
			newThread();
		}
	}

	private void newThread() {
		InternalTask internalTask = new InternalTask(runnableQueue);
		Thread thread = this.threadFactory.createThread(internalTask);
		ThreadTask threadTask = new ThreadTask(thread, internalTask);
		threadQueue.offer(threadTask);
		this.activityCount++;
		thread.start();
	}

	private void removeThread() {
		ThreadTask threadTask = threadQueue.remove();
		threadTask.internalTask.stop();
		this.activityCount--;
	}

	@Override
	public void execute(Runnable runnable) {
		if (this.isShutdown)
			throw new IllegalStateException("The ThreadPool is destroy！");
		this.runnableQueue.offer(runnable);

	}

	@Override
	public void run() {
		while (!isShutdown && !isInterrupted()) {
			try {
				timeUnit.sleep(keepAliveTime);
			} catch (InterruptedException e) {
				isShutdown = true;
				break;
			}
			synchronized (this) {
				if (isShutdown)
					break;
				//有任务，活动线程小于核心线程   扩大线程数量
				if (runnableQueue.size() > 0 && activityCount < coreSize) {
					for (int i = initSize; i < coreSize; i++) {
						newThread();
					}
					continue;
				}
				//有任务,活动线程小于最大线程 扩大线程数量到max
				if (runnableQueue.size() > 0 && activityCount < maxSize) {
					for (int i = coreSize; i < maxSize; i++) {
						newThread();
					}
				}
				//无任务,则需要缩减活动线程
				if (runnableQueue.size() == 0 && activityCount > coreSize) {
					for (int i = coreSize; i < activityCount; i++) {
						removeThread();
					}
				}
			}
		}
	}

	@Override
	public void shutdown() {
		synchronized (this) {
			if (isShutdown) return;
			isShutdown = true;
			threadQueue.forEach(threadTask -> {
				threadTask.internalTask.stop();
				threadTask.thread.interrupt();
			});
			this.interrupt();
		}
	}

	@Override
	public int getInitSize() {
		if (isShutdown)
			throw new IllegalStateException("The pool is destroy");
		return this.initSize;
	}

	@Override
	public int getMaxSize() {
		if (isShutdown)
			throw new IllegalStateException("The pool is destroy");
		return this.maxSize;
	}

	@Override
	public int getCoreSize() {
		if (isShutdown)
			throw new IllegalStateException("The pool is destroy");
		return this.coreSize;
	}

	@Override
	public int getQueueSize() {
		if (isShutdown)
			throw new IllegalStateException("The pool is destroy");
		return this.runnableQueue.size();
	}

	@Override
	public int getActivityCount() {
		if (isShutdown)
			throw new IllegalStateException("The pool is destroy");
		return this.activityCount;
	}

	@Override
	public boolean isShutdown() {
		return this.isShutdown;
	}

	private static class ThreadTask {
		private Thread thread;
		private InternalTask internalTask;

		ThreadTask(Thread thread, InternalTask internalTask) {
			this.internalTask = internalTask;
			this.thread = thread;
		}
	}

	private static class DefaultFactory implements ThreadFactory {
		private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
		private static final ThreadGroup group = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndDecrement());
		private static final AtomicInteger COUNTER = new AtomicInteger(0);

		@Override
		public Thread createThread(Runnable runnable) {
			return new Thread(group, runnable, "Thread-pool-" + COUNTER.getAndDecrement());
		}
	}
}
