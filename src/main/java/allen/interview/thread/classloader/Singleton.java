package allen.interview.thread.classloader;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月20日 15:31
 */
public class Singleton {
    private static Singleton instance = new Singleton();

	private static int x = 0;
	private static int y ;

	private Singleton() {
		x++;
		y++;
	}
	private static Singleton getInstance() {
		return instance;
	}

	public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(x);
		System.out.println(y);
	}
}
