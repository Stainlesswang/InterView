package allen.interview.thread.chapter04;

import java.util.HashMap;

/**
 * @author WangJianQiang
 * @Description:HashMap是非线程安全的
 * @date 2018年11月06日 8:37
 */
public class HashMapDeadLock {
	private HashMap<String, String> map = new HashMap<>();

	private void add(String key, String value) {
		this.map.put(key, value);
	}
	private String get(String key) {
		return this.map.get(key);
	}

	public static void main(String[] args) {
		//慎重启动该方法，因为会让你的机器死机  兄弟
		HashMapDeadLock hashMapDeadLock = new HashMapDeadLock();
		for (int i = 0; i < 2; i++) {//创建两个线程，启动
			new Thread(() -> {
				for (int j = 0; j < Integer.MAX_VALUE; j++) {
					hashMapDeadLock.add(String.valueOf(j), String.valueOf(j));
				}
			}, "thread-" + i).start();
		}
	}
}
