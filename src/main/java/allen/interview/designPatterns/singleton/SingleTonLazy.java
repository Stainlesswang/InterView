package allen.interview.designPatterns.singleton;

/**
 * @author WangJianQiang
 * @Description: 一个双重检验锁的优雅单例模式
 * @date 2019年03月26日 15:52
 */
public class SingleTonLazy {
	//使用volatile保证了'可见性',即多个线程看到的该变量的状态都是'最新的'
    /**
     * 加上 volatile关键字以后可以防止指令重排序
     * 原因在于instance = new Singleton(); 可以分为三步：
     * 1. memory=allocate();//分配内存空间
     * 2. instance(memory);//初始化对象
     * 3. instance=memory;//设置instance指向分配的内存地址，分配成功后，instance!=null
     * **/
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
