package allen.interview.thread.chapter08;

/**
 * @author WangJianQiang
 * @Description:当任务队列中达到了limit上限，采用什么策略通知提交者
 * @date 2018年11月12日 10:35
 */
@FunctionalInterface
public interface DenyPolicy {
	void reject(Runnable runnable, ThreadPool threadPool);

	//该拒绝策略直接将任务丢弃
	class DiscardDenyPolicy implements DenyPolicy {

		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {

		}
	}

	//该拒绝策略会向任务提交者抛出异常
	class AbortDenyPolicy implements DenyPolicy {
		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			throw new RunnableDenyException("The runnable" + runnable + " will be abort. ");
		}
	}

	class RunnerDenyPolicy implements DenyPolicy {
		@Override
		public void reject(Runnable runnable, ThreadPool threadPool) {
			if (!threadPool.isShutdown()) {
				runnable.run();
			}
		}
	}
}
