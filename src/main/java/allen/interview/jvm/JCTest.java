package allen.interview.jvm;

/**
 * 做一些简单的JC测试,
 * 新建的对象在创建的时候会在年轻代创建,当年轻代空间不足的时候
 * 会发生 内存分配担保机制:
 * 发现年轻代没有足够空间了,会暂时借用老年代的空间保存现在新生代的对象,然后新生代腾出空间分配新的对象
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
