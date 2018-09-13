package allen.interview.thread;

/**
 * 创建新线程的第二种方法  实现Runnable接口
 * */
public class NewThread2 implements Runnable{
    private String name;
    NewThread2(String name){
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

    public static void main(String[] args){
        new Thread(new NewThread1("C")).start();
        new Thread(new NewThread2("D")).start();

    }
}
