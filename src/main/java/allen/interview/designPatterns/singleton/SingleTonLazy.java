package allen.interview.designPatterns.singleton;

/**
 * @author WangJianQiang
 * @Description: 一个双重检验锁的优雅单例模式
 * @date 2019年03月26日 15:52
 */
public class SingleTonLazy {
	//使用volatile将该对象编程多线程间可见的
	private static volatile SingleTonLazy singleTonLazy = null;

    private SingleTonLazy() {
	}

	/**
	 * 线程安全的获取
	 *
	 * @param
	 * @return allen.interview.designPatterns.singleton.SingleTonLazy
	 * @author WangJianQiang
	 * @date 2019年03月26日 下午03:55:39
	 */
	public static SingleTonLazy getInsTance() {
		/*该方法安全是安全，但是效率太低。每次取的时候单例实例已经实例化好了 但是还要排队**/
//		synchronized (SingleTonLazy.class) {
//			if (null == singleTonLazy) {
//				singleTonLazy = new SingleTonLazy();
//			}
//			return singleTonLazy;
//		}
		/*修改成如下方式 双重检查锁**/
		if (null==singleTonLazy){
			synchronized (SingleTonLazy.class){
				if (null==singleTonLazy){
					singleTonLazy=new SingleTonLazy();
				}
			}
		}
		return singleTonLazy;
	}
}
