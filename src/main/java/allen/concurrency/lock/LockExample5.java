package allen.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 第二点例子使用, 如何使用Condition类
 * 2.可以使用Condition类,可以分组唤醒需要唤醒的锁
 */
public class LockExample5 {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock reentrantLock=new ReentrantLock();
        Condition condition=reentrantLock.newCondition();

        //创建第一个线程
        new Thread(()->{
            try{
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "::获取锁资源");

                condition.await();
                System.out.println(Thread.currentThread().getName() + "::Condition.await之后继续执行!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }


        },"First").start();

        //创建第二个线程
        new Thread(()->{
            try{
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "::获取锁资源");
                Thread.sleep(2000);
                condition.signalAll();
                System.out.println(Thread.currentThread().getName() + "::Condition.await之后继续执行!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        },"Second").start();
    }

}
