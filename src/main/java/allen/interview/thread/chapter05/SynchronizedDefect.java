package allen.interview.thread.chapter05;

import java.util.concurrent.TimeUnit;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月07日 10:22
 */
public class SynchronizedDefect {
	private synchronized void synMethod() {
		try {
			TimeUnit.HOURS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws InterruptedException {
		SynchronizedDefect defect = new SynchronizedDefect();
		Thread t1 = new Thread(defect::synMethod, "T1");
		t1.start();
		TimeUnit.MILLISECONDS.sleep(2);
		Thread t2 = new Thread(defect::synMethod, "T2");
		t2.start();
		TimeUnit.MILLISECONDS.sleep(2);
		t2.interrupt();
		System.out.println(t2.isInterrupted());
		System.out.println(t2.getState());
		System.out.println(t1.isInterrupted());
		System.out.println(t1.getState());
	}
}
