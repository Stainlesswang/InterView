package allen.interview.thread.pool.demo;

/**
 * @author WangJianQiang
 * @Description:创建线程的接口
 * @date 2018年11月12日 10:29
 */
@FunctionalInterface
public interface ThreadFactory {
	Thread createThread(Runnable runnable);

	default void defaultMethod() {
		System.out.println("this is interface default method");
	}
}
