package allen.interview.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理增强类,在组合类基础上增加了 before输出和after输出
 *
 */
public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    /**
     * 构造器,注入需要增强的类
     * @param object
     */
    public MyInvocationHandler(Object object){
        super();
        this.target=object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before-------------");

        Object result= method.invoke(target,args);


        System.out.println("after-------------");

        return result;
    }

    /**
     * 获取代理
     * @return
     */
    public Object getProxy(){
        return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),target.getClass().getInterfaces(),this);
    }
}
