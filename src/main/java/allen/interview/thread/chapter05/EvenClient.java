package allen.interview.thread.chapter05;

import java.util.concurrent.TimeUnit;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月06日 9:20
 */
public class EvenClient {
	public static void main(String[] args) {
		EventQueue eventQueue=new EventQueue();
		//一个线程为生产Event的线程，并且不断向队列加入数据
		new Thread(()->{
			for (; ;){
				eventQueue.offer(new EventQueue.Event());
			}
		},"Producer").start();

		//一个线程作为消费Event的线程，处理每个线程会耗时一分钟时间（为了模拟真实情况）
		new Thread(()->{
			for (; ;){
				eventQueue.take();
				try {
					TimeUnit.MINUTES.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"Consumer").start();
	}
}
