package allen.interview.spring.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @author AllenWong
 * @date 2019/10/14 10:28 PM
 */
public class DemoFactory {

    public static CGLIBDemo getInstence(CGLBProxy proxy){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(CGLIBDemo.class);
        enhancer.setCallback(proxy);
        return (CGLIBDemo) enhancer.create();
    }
}
