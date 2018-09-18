package allen.interview.thread;

public class TicketWindowRunnable implements Runnable{
	private int index=1;
	private final static int MAX=500;
	@Override
	public void run() {
		while (index<500){
			System.out.println(Thread.currentThread().getName()+"当前号码为："+(index++));
		}
	}


	public static void main(String[] args){
		TicketWindowRunnable task=new TicketWindowRunnable();
		Thread thread1=new Thread(task,"一");
		Thread thread2=new Thread(task,"二");
		Thread thread3=new Thread(task,"三");
		Thread thread4=new Thread(task,"四");
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
}
