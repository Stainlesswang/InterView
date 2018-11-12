package allen.interview.thread.chapter08;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月12日 10:15
 */
public interface ThreadPool {
	//提交任务到线程池
	void execute(Runnable runnable);
	//关闭线程池；
	void shutdown();
	//获取线程池初始化大小
	int getInitSize();
	//获取最大线程数
	int getMaxSize();
	//获取核心线程的数量
	int getCoreSize();
	//用于缓存任务队列的大小
	int getQueueSize();
	//线程池中活跃的线程数量
	int getActivityCount();
	//线程池是否被关闭
	boolean isShutdown();
}
