package allen.interview.thread.pool.demo;

import java.util.LinkedList;

/**
 * @author WangJianQiang
 * @Description:
 * LinkedList实现的Runnable接口列表作为存放容器, 提供入队/出队操作
 * 入队时要判断是否到达了最大限制,到达限制后执行构造时注入进来的拒绝策略
 * 出队时队列若无可执行runnable则当前线程阻塞wait();否则 取出相应的runnable
 *
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

	LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
		this.limit = limit;
		this.denyPolicy = denyPolicy;
		this.threadPool = threadPool;
	}

	//入队列
	@Override
	public void offer(Runnable runnable) {
		//runnable 相关的monitor 加上锁
		synchronized (runnableList) {
			//如果队列满
			if (runnableList.size() >= limit) {
				//无法容纳 执行拒绝策略
				denyPolicy.reject(runnable, threadPool);
			} else {
				//将任务加入队列
				runnableList.addLast(runnable);
				//将因阻塞放入wait set中的线程全部唤醒,可以争取资源
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
					//当队列中任务为空时,当前线程挂起, 当前线程进入 runnableList关联的monitor wait set 中等待(有新任务的时候)唤醒
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
