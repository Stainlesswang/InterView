package allen.concurrency.lock;

import java.text.MessageFormat;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 模拟线程并发的类
 */
public class LockExample2 {
    private final Map<String,String> map=new TreeMap<>();

    private final ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    private final Lock readLock=lock.readLock();
    private final Lock wirteLock=lock.writeLock();
}
