package allen.interview.thread.deadlock;

import static java.lang.Thread.currentThread;

/**
 * @author WangJianQiang
 * @Description:
 * 交叉获取锁资源导致的死锁
 *
 * 程序错误排查方式:
 * 使用jstack pid 命令来查找deadlocak出现的地方
 *
 *
 * 代码解决方式:
 * 当获取不到资源的时候可以先释放自己占有的资源
 * 或者按照顺序进行锁的获取
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

	public static void main(String[] args) throws InterruptedException {
		DeadLock deadLock = new DeadLock();
		Thread thread=Thread.currentThread();
        System.out.println(thread.getId());
        System.out.println(thread.getThreadGroup().toString());
        System.out.println(thread.toString());

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
