package allen.interview.spring.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

public class MyInvocationHandlerTest {

    @Test
    public void getProxy() {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        //首先声明需要的代理类
        UserService userService=new UserServiceImpl();
        //动态代理技术实现的增强类, add方法执行之前和之后会打印出来相应的信息
        UserService proxy= (UserService) new MyInvocationHandler(userService).getProxy();
        proxy.add();
        proxy.desc();
    }
}