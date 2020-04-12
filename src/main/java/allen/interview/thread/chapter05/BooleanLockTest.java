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

        try {
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

	public static void main(String[] args) throws InterruptedException {
		BooleanLockTest booleanLockTest = new BooleanLockTest();
		//实现了synchronize的作用
//		IntStream.range(0, 9)
//				.mapToObj(d -> new Thread(booleanLockTest::synMethod))
//				.forEach(Thread::start);

		//可中断测试
		new Thread(booleanLockTest::synMethod,"T1").start();
		TimeUnit.SECONDS.sleep(2);
		Thread t2=new Thread(booleanLockTest::synMethod,"T2");
		t2.start();
		TimeUnit.SECONDS.sleep(10);
		t2.interrupt();
	}
}
