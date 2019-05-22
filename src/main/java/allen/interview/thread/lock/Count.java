package allen.interview.thread.lock;


public class Count {
    MyReentrantLock reentrantLock=new MyReentrantLock();

    private NotReentrantLock lock=new NotReentrantLock();
    private void print() throws InterruptedException {
        reentrantLock.lock();
        doAdd();
        reentrantLock.unlock();
    }

    private void doAdd() throws InterruptedException {
        reentrantLock.lock();
        //do something
        reentrantLock.unlock();
    }

    public static void main(String[] args) {
        //不可重入锁,当一个拥有该锁的线程再次进入同步代码块的时候,会阻塞!
        Count count=new Count();
        try {
            count.print();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

