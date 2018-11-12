package allen.interview.thread.chapter08;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月12日 15:24
 */
public class BasicThreadPool extends Thread implements ThreadPool {

	@Override
	public void execute(Runnable runnable) {

	}

	@Override
	public void shutdown() {

	}

	@Override
	public int getInitSize() {
		return 0;
	}

	@Override
	public int getMaxSize() {
		return 0;
	}

	@Override
	public int getCoreSize() {
		return 0;
	}

	@Override
	public int getQueueSize() {
		return 0;
	}

	@Override
	public int getActivityCount() {
		return 0;
	}

	@Override
	public boolean isShutdown() {
		return false;
	}
}
