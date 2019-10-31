package allen.interview.aboutJava;

/**
 * @author wangjianqiang
 */
public class FloatTest {
    public static void main(String[] args) {
        float a=1.0f-0.9f;
        float b=0.9f-0.8f;
        float diff=1e-6f;
        if (Math.abs(a-b)<diff) {
            System.out.println("误差为："+Math.abs(a-b));
        }
    }
}
