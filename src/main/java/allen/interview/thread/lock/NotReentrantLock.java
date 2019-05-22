package allen.interview.thread.lock;

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
