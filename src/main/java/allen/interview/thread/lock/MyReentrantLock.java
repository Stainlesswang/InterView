package allen.interview.thread.lock;

public class MyReentrantLock {
    private boolean isLocked=false;
    private Thread lockBy=null;
    private int lockCount=0;
    public synchronized void lock() throws InterruptedException{
        Thread threadNow=Thread.currentThread();
        while (isLocked&&threadNow!=lockBy){
            wait();
        }
        isLocked=true;
        lockBy=threadNow;
        lockCount++;
        System.out.println(lockCount);
    }
    public synchronized void unlock(){
        if(Thread.currentThread() == this.lockBy){
            lockCount--;
            if(lockCount == 0){
                isLocked = false;
                notify();
            }
            System.out.println(lockCount);
        }
    }

}
