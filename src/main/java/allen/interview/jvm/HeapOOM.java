package allen.interview.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * OOM测试类
 *
 * 首先配置VM Option 参数如下:
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:+HeapDumpOnOutOfMemoryError 该设置可以将发生OOM转存为内存文件,可以借助工具分析是内存泄漏还是内存溢出,
 * 也就是判断下导致没有回收的对象到底是不是必须存活的对象?
 *
 * 然后运行会看到抛出OOM的错误
 *
 * 当我们线上遇到这样的问题的时候,我们首先根据 -XX:+HeapDumpOnOutOfMemoryError 参数dump下来的文件进行分析
 *
 *
 * @author AllenWong
 * @date 2019/10/20 10:50 AM
 */
public class HeapOOM {
    private static class OOMObject{}
    public static void main(String[] args) {
        List<OOMObject> list=new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }

    }
}
