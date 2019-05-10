package allen.interview.thread;

/**
 * 创建新线程的第二种方法  实现Runnable接口
 * */
public class MyRunnable implements Runnable{
    private String name;
    MyRunnable(String name){
        this.name=name;
    }
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行  :  " + i);
            try {
                Thread.sleep((int) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main thread start running!");
        Thread thread1=new Thread(new MyRunnable("A"));
        thread1.start();
        thread1.join();
        System.out.println("main thread now is End My dear!");
        //        main thread start running!
        //        A运行  :  0
        //        A运行  :  1
        //        A运行  :  2
        //        A运行  :  3
        //        A运行  :  4
        //        main thread now is End My dear!
    }
}
