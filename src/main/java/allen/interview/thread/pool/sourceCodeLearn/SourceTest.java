package allen.interview.thread.pool.sourceCodeLearn;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author AllenWong
 * 用来测试源码中的一些零碎点
 * @date 2020/4/20 10:29 PM
 */
public class SourceTest {
    private void outMethod(){
        System.out.println("outMethod running");
    }
    public final class InnerClass{

        private   void test(){
            outMethod();
            System.out.println("Test Method running");
        }
         InnerClass(){
            System.out.println("非静态内部类构造函数");
        }
    }
    public static void main(String[] args) {
        SourceTest  sourceTest= new SourceTest();
        InnerClass innerClass=sourceTest.new InnerClass();

    }
}
