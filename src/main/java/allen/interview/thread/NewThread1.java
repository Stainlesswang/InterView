package allen.interview.thread;
/**
 * this is a class show how to created a thread
 * 1.继承Thread类
 * 2.实现Runnable接口
 * */
public class NewThread1 extends Thread {


        private String name;
        public NewThread1(String name) {
            this.name=name;
        }
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(name + "运行  :  " + i);
                try {
                    sleep((int) Math.random() * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    public static void main(String[] args){
        NewThread1 mTh1=new NewThread1("A");
        NewThread1 mTh2=new NewThread1("B");
        mTh1.start();
        mTh2.start();
    }
}
