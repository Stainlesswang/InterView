package allen.interview.thread;

public class JavaThread {
	public static void main(String[] args){
		//lambda表达式
		Runnable runnable=()-> System.out.println("just do something");
		Thread thread=new Thread(runnable);
		thread.start();

	}
}
