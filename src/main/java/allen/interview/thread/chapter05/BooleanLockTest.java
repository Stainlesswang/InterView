package allen.interview.thread.chapter05;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * @author WangJianQiang
 * @Description: BooleanLock显示锁的测试类
 * @date 2018年11月08日 9:00
 */
public class BooleanLockTest {
	private final Lock lock = new BooleanLock();

	private void synMethod() {
		lock.lock();
		try {
			int randomInt = ThreadLocalRandom.current().nextInt(10);
			System.out.println(currentThread() + " get the lock");
			TimeUnit.SECONDS.sleep(randomInt);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		BooleanLockTest booleanLockTest = new BooleanLockTest();
		IntStream.range(0, 9)
				.mapToObj(i -> new Thread(booleanLockTest::synMethod, "T" + String.valueOf(i)))
				.forEach(Thread::start);
	}
}
