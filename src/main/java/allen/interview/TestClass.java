package allen.interview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jianqiang8
 * @description
 * @date 2021/10/19 4:18 下午
 */
public class TestClass {
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits 我们的爱 过了就不在回来直到现在我还默默的等待
    // 我们的爱 过了就不再回来, 我们的爱 过了就不再回来 知道现在我还默默的等待
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    private static int ctlOf(int rs, int wc) { return rs | wc; }

    public static void main(String[] args) {
        System.out.println(COUNT_BITS);
        System.out.println(CAPACITY);
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);
        System.out.println(ctlOf(RUNNING, 0));
    }

}
