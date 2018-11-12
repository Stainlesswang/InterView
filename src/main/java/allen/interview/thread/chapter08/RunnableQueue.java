package allen.interview.thread.chapter08;

/**
 * @author WangJianQiang
 * @Description:任务队列 主要用于缓存提交到线程池的任务
 * @date 2018年11月12日 10:24
 */
public interface RunnableQueue {
	//当有新的任务提交，首先会offer到队列中
	void offer(Runnable runnable);

	//线程到队列中取任务
	Runnable take() throws InterruptedException;

	//获取任务队列中的任务数量
	int size();
}
