package allen.concurrency.lock;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 什么事读写锁? 就是
 */
public class LockExample2 {
    private final Map<String,Data> map=new TreeMap<>();

    private final ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    private final Lock readLock=lock.readLock();
    private final Lock writeLock =lock.writeLock();
    public Data get(String key){
        readLock.lock();
        try {
           return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public void setData(String key,Data data){
        writeLock.lock();
        try {
            map.put(key,data);
        }finally {
            writeLock.unlock();
        }

    }
    class Data{}
}
