package allen.interview.thread.chapter05;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月07日 15:53
 */
public interface Lock {
	void lock() throws InterruptedException;
	void lock(long mills) throws InterruptedException,TimeoutException;
	void unlock();
	List<Thread> getBlockedThreads();
}
