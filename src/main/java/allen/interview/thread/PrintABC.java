package allen.interview.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author AllenWong
 * @date 2020/4/27 7:51 PM
 */
public class PrintABC {
    static volatile int i=1;
    public static void main(String[] args) {
        ReentrantLock lock =new ReentrantLock();
        Condition A= lock.newCondition();
        Condition B= lock.newCondition();
        Condition C= lock.newCondition();

        Thread AThread=new Thread(() -> {
            while (true){
                try {
                    lock.lock();
                    while (PrintABC.i!=1){
                        A.await();
                    }
                    Thread.sleep(1000);
                    System.out.println("A");
                    PrintABC.i=2;
                    B.signal();
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
                    while (PrintABC.i!=2){
                        B.await();

                    }
                    Thread.sleep(1000);
                    System.out.println("B");
                    PrintABC.i=3;
                    C.signal();

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
                    while (PrintABC.i!=3){
                        C.await();
                    }
                    Thread.sleep(1000);
                    System.out.println("C");
                    PrintABC.i=1;
                    A.signal();

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
