package allen.interview.thread.chapter05;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author WangJianQiang
 *
 * 自定义显示锁，不仅有synchronize关键字的功能，并且有 可中断，可设置阻塞时间的功能
 *
 * @Description:
 * @date 2018年11月07日 15:53
 */
public interface Lock {
	void lock() throws InterruptedException;

	void lock(long mills) throws InterruptedException, TimeoutException;

	void unlock();

	List<Thread> getBlockedThreads();
}
