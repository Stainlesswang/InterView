package allen.interview.spring.proxy.cglib;

import org.junit.Test;

/**
 * @author AllenWong
 * @date 2019/10/14 10:33 PM
 */
public class DemoFactoryTest {

    @Test
    public void getProxy() {
        CGLBProxy proxy = new CGLBProxy();
        CGLIBDemo demo=DemoFactory.getInstance(proxy);
        demo.add();
        demo.desc();
    }

}