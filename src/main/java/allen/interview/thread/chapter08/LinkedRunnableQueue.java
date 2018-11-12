package allen.interview.thread.chapter08;

import java.util.LinkedList;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月12日 13:53
 */
public class LinkedRunnableQueue implements RunnableQueue {
	//队列上限
	private final int limit;
	//达到上限处理策略
	private final DenyPolicy denyPolicy;
	//存放任务的队列
	private final LinkedList<Runnable> runnableList = new LinkedList<>();
	private final ThreadPool threadPool;

	public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
		this.limit = limit;
		this.denyPolicy = denyPolicy;
		this.threadPool = threadPool;
	}

	//入队列
	@Override
	public void offer(Runnable runnable) {
		synchronized (runnableList) {
			if (runnableList.size() >= limit) {
				//无法容纳 执行拒绝策略
				denyPolicy.reject(runnable, threadPool);
			} else {
				runnableList.addLast(runnable);
				runnableList.notifyAll();
			}
		}
	}

	//从队列中取任务
	@Override
	public Runnable take() throws InterruptedException {
		synchronized (runnableList) {
			while (runnableList.isEmpty()) {
				try {
					//当队列中任务为空时,当前线程挂起, 当前线程进入 runnableList关联的monitor waitset 中等待(有新任务的时候)唤醒
					runnableList.wait();
				} catch (InterruptedException e) {
					//抛出异常 令调用者处理异常
					throw e;
				}
			}
			return runnableList.getFirst();
		}
	}

	//返回队列中的长度
	@Override
	public int size() {
		synchronized (runnableList) {
			return runnableList.size();
		}
	}
}
