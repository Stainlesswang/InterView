package allen.interview.spring.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @author AllenWong
 * @date 2019/10/14 10:28 PM
 */
public class DemoFactory {

    public static CGLIBDemo getInstance(CGLBProxy proxy){
        //一个增强器实例化
        Enhancer enhancer=new Enhancer();
        //指定需要继承的父类
        enhancer.setSuperclass(CGLIBDemo.class);
        //通过继承动态生成一个子类,然后使用 MethodInterceptor 中重写的intercept()方法执行所有非final,非static,非私有方法
        enhancer.setCallback(proxy);
        return (CGLIBDemo) enhancer.create();
    }
}
