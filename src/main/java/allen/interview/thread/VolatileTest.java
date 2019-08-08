package allen.interview.thread;

/**
 * 该类为了测试 Volatile 关键字保证变量的可见性
 * number变量没有加volatile关键字的时候,程序不知道变量已经改变了
 * 当number变量加上volatile关键字的 时候, 程序在另外线程中修改后,主线程立马就知道改变后的值
 */
public class VolatileTest {


    static class MyData{
//         int number=0;//case1 对该变量
        volatile int number=0;//当加上volatile关键字的时候,另外线程修改该参数主线程立马就知道
         void changeNumber(){
             number=60;
         }
    }

    public static void main(String[] args) {
        MyData mydata=new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mydata.changeNumber();
            System.out.println(Thread.currentThread().getName()+"\t updated number value:"+mydata.number);
        },"AAAA").start();

        while (mydata.number==0){}
        System.out.println(Thread.currentThread().getName()+"\t over, get:"+mydata.number);

    }
}
