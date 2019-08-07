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

		new Thread(()->{
			for (; ;){
				eventQueue.offer(new EventQueue.Event());
			}
		},"Producer1").start();

		new Thread(()->{
			for (; ;){
				eventQueue.offer(new EventQueue.Event());
			}
		},"Producer2").start();

		//一个线程作为消费Event的线程，处理每个线程会耗时30 seconds时间（为了模拟真实情况）


        new Thread(()->{
			for (; ;){
				eventQueue.take();
				try {
					TimeUnit.SECONDS.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"Consumer1").start();

		new Thread(()->{
			for (; ;){
				eventQueue.take();
				try {
					TimeUnit.SECONDS.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"Consumer2").start();

		new Thread(()->{
			for (; ;){
				eventQueue.take();
				try {
					TimeUnit.SECONDS.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"Consumer3").start();
	}
}
