package allen.interview.thread.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * @author WangJianQiang
 *
 * 钩子线程是干啥的呢？  首先当jvm中没有非守护线程运行的时候，或者收到了中断信号的时候， jvm要退出时，Hook线程就会启动
 * 比如在程序结束关闭一些资源，删除一些数据，检查一些状态等等
 * @Description:
 * @date 2018年11月09日 15:09
 */
public class ThreadHook {
	public static void main(String[] args) {
		//hook 线程示例   可以设置多个hook线程
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			System.out.println("the hook1 is running!");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"hook1"));
	}
}
