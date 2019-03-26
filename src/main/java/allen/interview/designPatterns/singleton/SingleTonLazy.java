package allen.interview.designPatterns.singleton;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2019年03月26日 15:52
 */
public class SingleTonLazy {
	private static volatile SingleTonLazy singleTonLazy = null;

	private SingleTonLazy() {
	}

	/**
	 * 线程安全的获取
	 * @author WangJianQiang
	 * @date 2019年03月26日 下午03:55:39 
	 * @param 
	 * @return allen.interview.designPatterns.singleton.SingleTonLazy
	 */
	public static SingleTonLazy getInsTance() {
		synchronized (SingleTonLazy.class) {
			if (null == singleTonLazy) {
				singleTonLazy = new SingleTonLazy();
			}
			return singleTonLazy;
		}
	}
}
