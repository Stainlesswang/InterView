package allen.interview.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理增强类,在组合类基础上增加了 before输出和after输出
 * JDK动态代理实现
 * 1.面向接口才可以使用JDK动态代理,所以这里定义一个UserService,以及一个实现类UserServiceImpl
 * 2.定义一个实现InvocationHandler的实现
 *
 *
 */
public class MyInvocationHandler implements InvocationHandler {
    //真正需要执行 add()方法的对象
    private Object target;

    /**
     * 构造器,注入需要增强的类
     * @param object
     */
    public MyInvocationHandler(Object object){
        super();
        this.target=object;
    }

    /**
     * 真正代理对象执行的方法,
     * method.invoke(target,args) 是利用反射来执行原来对象的方法的
     * 该行代码的前后就是执行AOP的切面操作的
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
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
        //生成代理对象的方法,需要三个参数,
        // 1.ClassLoader
        // 2.CLass对象的接口getInterfaces()
        // 3.一个InvocationHandler实例
        return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),target.getClass().getInterfaces(),this);
    }
}
