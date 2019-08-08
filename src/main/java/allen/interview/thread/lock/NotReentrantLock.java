package allen.interview.thread.lock;

/**
 * 不可重入锁的实现,  为什么是不可重入锁,  和可重入锁的主要区别在哪里
 */
public class NotReentrantLock {
    private boolean isLocked = false;
    synchronized void lock() throws InterruptedException{
        while(isLocked){
            wait();
        }
        isLocked = true;
    }
    synchronized void unlock(){
        isLocked = false;
        notify();
    }

}
