package allen.interview.spring.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGlib 实现代理对象必须实现 Method
 * @author AllenWong
 * @date 2019/10/14 10:25 PM
 */
public class CGLBProxy implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before-------------");
        methodProxy.invokeSuper(o,objects);
        System.out.println(method.getName());
        System.out.println("after-------------");
        return null;
    }
}
