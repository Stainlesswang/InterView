package allen.interview.thread;


import java.util.concurrent.TimeUnit;

public class JavaThread {
	public static void main(String[] args){
		//lambda表达式
		Runnable runnable=()->{
			try {
				TimeUnit.MINUTES.sleep(1L);
			} catch (InterruptedException e) {
				System.out.println("current thread name" +Thread.currentThread().getName());
			}
		} ;
		Thread thread=new Thread(runnable);
		thread.start();
		thread.interrupt();


	}
}
