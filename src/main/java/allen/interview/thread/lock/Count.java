package allen.interview.thread.lock;

/**
 * 该类说明了 如何使用可重入锁和不可重入锁
 * 不可重入锁,当加锁的外层方法执行后,子方法内如果尝试获取锁,会阻塞
 */
public class Count {
    MyReentrantLock reentrantLock=new MyReentrantLock();

    private NotReentrantLock notReentrantLock=new NotReentrantLock();
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

