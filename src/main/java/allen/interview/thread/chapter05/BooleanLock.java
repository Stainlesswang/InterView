package allen.interview.thread.chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;

/**
 * 构造一个显示lock,令其不仅包含synchronized关键字所有功能
 * 而且还具备可中断 可设置超时时间的功能
 *
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月07日 15:58
 */
public class BooleanLock implements Lock {
	//获取了锁资源的线程
	private Thread currentThread;
	//false表示当前该锁处于空闲转态,true表示当前被 currentThread获取了锁
	private boolean locked = false;
	//保存那些锁资源已被占用,希望获取锁资源导致阻塞的线程
	private final List<Thread> blockList = new ArrayList<>();

	/**
	 * 判断锁资源是否被其他线程占用。
	 * 1.被占用，加入阻塞列表 当前线程 进入wait set
	 * 2.未被占用，移出阻塞列表，标识为false
	 * @author WangJianQiang
	 * @date
	 * @Param
	 * @return void
	 */
	@Override
	public void lock()  {
		synchronized (this) {
			while (locked) {
				//锁已被其他线程获取，当前线程阻塞加入阻塞列表
				blockList.add(currentThread());
				//当前线程阻塞加入 wait set中
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//将当前线程移除阻塞列表
			blockList.remove(currentThread());
			//锁资源被获取
			this.locked = true;
			//赋值到当前线程
			currentThread = currentThread();
		}
	}
	/**
	 * 在指定时间内未获取,抛出超时异常退出
	 * @author WangJianQiang
	 * @date
	 * @Param mills
	 * @return void
	 */
	@Override
	public void lock(long mills) throws InterruptedException, TimeoutException {
		synchronized (this) {
			if (mills <= 0) {
				this.lock();
			} else {
				long remainingMills = mills;
				long endMills = System.currentTimeMillis() + mills;
				while (locked) {
					if (remainingMills <= 0)
						throw new TimeoutException("can not get lock during " + mills);
					if (!blockList.contains(currentThread))
						blockList.add(currentThread);
					this.wait(remainingMills);
					remainingMills = endMills - System.currentTimeMillis();
				}
				//将当前线程从阻塞列表移除
				blockList.remove(currentThread());
				//锁资源 被获取
				this.locked = true;
				//赋值到当前线程
				currentThread = currentThread();
			}
		}
	}

	/**
	 * 判断获取锁资源的线程是否是当前线程. 如果是才可以重置为false.
	 * 然后通知其他所有在wait set中的线程可以来抢夺锁资源啦
	 * @author WangJianQiang
	 * @date
	 * @Param
	 * @return void
	 */
	@Override
	public void unlock() {
		synchronized (this) {
			if (currentThread == currentThread()) {
				this.locked = false;
				Optional.of(currentThread.getName()+" release the lock")
						.ifPresent(System.out::println);
				this.notifyAll();
			}
		}
	}

	/**
	 * 返回一个不可变的阻塞线程列表
	 * @author WangJianQiang
	 * @date
	 * @Param
	 * @return java.util.List<java.lang.Thread>
	 */
	@Override
	public List<Thread> getBlockedThreads() {
		//返回一个不可修改的list
		return Collections.unmodifiableList(blockList);
	}
}
