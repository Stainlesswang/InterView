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
        Condition condition2=reentrantLock.newCondition();

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

        //创建第二个在阻塞的线程
        new Thread(()->{
            try{
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "::获取锁资源");

                condition2.await();
                System.out.println(Thread.currentThread().getName() + "::Condition.await之后继续执行!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }


        },"First-2").start();

        //创建第唤醒condition1个线程
        new Thread(()->{
            try{
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "::获取锁资源");
                Thread.sleep(2000);
                condition.signalAll();
                Thread.sleep(2000);
                condition2.signalAll();
                System.out.println(Thread.currentThread().getName() + "::Condition.await之后继续执行!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "::释放ReenTrantLocak!");
                reentrantLock.unlock();
            }
        },"Second").start();
    }

}
