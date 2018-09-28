package allen.interview.thread;

import java.util.Map;

/**
 * this is a class show how to created a thread
 * 1.继承Thread类,创建线程
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
                    sleep((int) (Math.random() * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }
        /**
         *
         * @Title: testGetString
         * @author wangjianqiang
         * @Description:
         * @Param a
         * @return java.lang.String
         * @date 2018年09月19日 上午10:20:13
         */
        public String testGetString(int a){
        	return null;
        }
    public static void main(String[] args){
        //new 出来的线程为新建状态，等待即将调用的start（）方法
        NewThread1 mTh1=new NewThread1("A");
        NewThread1 mTh2=new NewThread1("B");
        //调用start（）方法，当前线程进入可执行状态Runable状态
        mTh1.start();
        mTh2.start();
    }
}
