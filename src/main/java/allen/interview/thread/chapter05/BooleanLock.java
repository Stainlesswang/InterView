package allen.interview.thread.chapter05;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

	@Override
	public void lock() throws InterruptedException {
		synchronized (this) {
			while (locked) {
				//锁已被其他线程获取，当前线程阻塞加入阻塞列表
				blockList.add(currentThread());
				//当前线程阻塞加入 wait set中
				this.wait();
			}
			//将当前线程移除阻塞列表
			blockList.remove(currentThread());
			//锁资源被获取
			this.locked = true;
			//赋值到当前线程
			currentThread = currentThread();
		}
	}

	@Override
	public void lock(long mills) throws InterruptedException, TimeoutException {
		if (mills<=0){
			this.lock();
		}else{
			long remainingMills=mills;
			long endMills= System.currentTimeMillis()+mills;
		}

	}

	@Override
	public void unlock() {

	}

	@Override
	public List<Thread> getBlockedThreads() {
		return null;
	}
}
