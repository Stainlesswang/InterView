package allen.interview.jvm;

/**
 * 做一些简单的JC测试
 *
 * @author AllenWong
 * @date 2019/9/22 1:36 PM
 */
public class JCTest {

    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation3,allocation4,allocation5,allocation2;
        allocation1 = new byte[30900*1024];
        allocation1 = new byte[32000*1024];
        allocation2 = new byte[1000*1024];
        allocation3 = new byte[1000*1024];
        allocation4 = new byte[1000*1024];
        allocation5 = new byte[1000*1024];
    }
}
