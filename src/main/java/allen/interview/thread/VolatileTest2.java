package allen.interview.thread;

/**
 * 该类为了测试 Volatile 关键字 不能保证原子性. ++操作会分成三步走,取数据,自增 然后set数据
 * 所以在多线程共同操作一个共享变量的时候会出现结果不一致的问题.
 *
 * 如何解决该问题?
 * 1.使用synchronized关键字对共享变量加锁
 * 2.使用AtomicInteger变量
 *
 */
public class VolatileTest2 {

    static class MyData{
        volatile int number=0;//解决方法2:该变量使用 AtomicInteger 原子类整型 CAS(Compare And Set)算法
         void changeNumber(){
             number=60;
         }

         void addOne(){
             number++;
         }
    }

    public static void case2(){
        MyData myData=new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j <1000; j++) {
                    myData.addOne();
                    //解决方法2,在自增操作调用的时候,对共享加锁,只有一个线程获取到锁以后执行完毕后释放锁,可以保证结果的正确性
//                    synchronized (myData){
//                        myData.addOne();
//                    }
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t number value:"+myData.number);

    }
    public static void main(String[] args) {
        case2();
    }
}
