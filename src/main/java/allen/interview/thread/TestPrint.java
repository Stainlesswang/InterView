package allen.interview.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author AllenWong
 * @date 2020/4/27 8:50 PM
 */
public class TestPrint {

    public static void main(String[] args) {
        ReentrantLock lock =new ReentrantLock();
        Condition A= lock.newCondition();
        Condition B= lock.newCondition();
        Condition C= lock.newCondition();

        Thread AThread=new Thread(() -> {
            while (true){
                try {
                    lock.lock();

                    Thread.sleep(1000);
                    System.out.println("A");
                    B.signal();
                    A.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }

        },"A");
        Thread BThread=new Thread(() -> {

            while (true){
                try {
                    lock.lock();

                    Thread.sleep(1000);
                    System.out.println("B");
                    C.signal();
                    B.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        },"B");
        Thread CThread=new Thread(() -> {

            while (true){

                try {
                    lock.lock();

                    Thread.sleep(1000);
                    System.out.println("C");
                    A.signal();
                    C.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        },"C");
        AThread.start();
        BThread.start();
        CThread.start();
    }

}
