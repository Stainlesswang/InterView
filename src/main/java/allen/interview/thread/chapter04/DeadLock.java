package allen.interview.thread.chapter04;

import static java.lang.Thread.currentThread;

/**
 * @author WangJianQiang
 * @Description:线程死锁举例
 * @date 2018年11月05日 9:02
 */
public class DeadLock {
	private final Object MUTEX_READ = new Object();
	private final Object MUTEX_WRITE = new Object();

	private void read() {
		synchronized (MUTEX_READ) {
			System.out.println(currentThread().getName() + " get Read Lock!");
			synchronized (MUTEX_WRITE) {
				System.out.println(currentThread().getName() + " get Write Lock!");
			}
			System.out.println(currentThread().getName() + " release Write Lock!");
		}
		System.out.println(currentThread().getName() + " release Read Lock!");
	}

	private void write() {
		synchronized (MUTEX_WRITE) {
			System.out.println(currentThread().getName() + " get Write Lock#");
			synchronized (MUTEX_READ) {
				System.out.println(currentThread().getName() + " get Read Lock#");
			}
			System.out.println(currentThread().getName() + " release Read Lock#");
		}
		System.out.println(currentThread().getName() + " release Write Lock#");
	}

	public static void main(String[] args) {
		DeadLock deadLock = new DeadLock();
		//线程会因为交叉获取锁资源导致死锁 无法继续执行
		new Thread(() ->
		{
			while (true) {
				deadLock.read();
			}
		}, "Read-Thread").start();

		new Thread(() ->
		{
			while (true) {
				deadLock.write();
			}
		}, "Write-Thread").start();
	}
}
