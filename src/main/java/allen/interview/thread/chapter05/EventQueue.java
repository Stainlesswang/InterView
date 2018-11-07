package allen.interview.thread.chapter05;

import java.util.LinkedList;

/**
 * @author WangJianQiang
 * @Description:事件队列,有如下三种状态 1.队列满
 * 2.队列空
 * 3.有Event但是没有满
 * @date 2018年11月06日 8:50
 */
public class EventQueue {
	private final int max;

	static class Event {
	}

	private final LinkedList<Event> eventQueue = new LinkedList<>();
	private final static int DEFAULT_MAX_EVENT = 10;

	EventQueue() {
		this(DEFAULT_MAX_EVENT);
	}

	private EventQueue(int max) {
		this.max = max;
	}

	//加入一个Event到队列中, 如果队列已经满了,调用队列的wait方法
	public void offer(Event event) {
		synchronized (eventQueue) {
			while (eventQueue.size() >= max) {
				try {
					console("The Queue is Full!");
					eventQueue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			console("The Queue is Submitted");
			eventQueue.addLast(event);
			console("eventQueue:::"+eventQueue.size());
			eventQueue.notifyAll();
		}
	}
	//处理一个事件,当队列为空的时候,调用wait方法让线程进入阻塞状态,并释放锁资源,当前线程加入 wait set中
	public Event take() {
		synchronized (eventQueue) {
			while (eventQueue.isEmpty()) {
				try {
					console("The Queue is Empty");
					eventQueue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			Event event = eventQueue.removeFirst();
			this.eventQueue.notifyAll();
			console("the event " + event + " is handled");
			console("eventQueue:::"+eventQueue.size());
			return event;
		}
	}

	private void console(String message) {
		System.out.printf("%s:%s\n", Thread.currentThread().getName(), message);
	}
}
