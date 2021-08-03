package allen.interview.spring.proxy.cglib;

import allen.interview.spring.proxy.MyInvocationHandler;
import allen.interview.spring.proxy.UserService;
import allen.interview.spring.proxy.UserServiceImpl;

/**
 * @author AllenWong
 * @date 2021/5/6 下午11:52
 */
public class Main {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        //首先声明需要的代理类
        UserService userService=new UserServiceImpl();
        //动态代理技术实现的增强类, add方法执行之前和之后会打印出来相应的信息
        UserService proxy= (UserService) new MyInvocationHandler(userService).getProxy();
        proxy.add();
        proxy.desc();

        CGLBProxy proxy2 = new CGLBProxy();
        CGLIBDemo demo=DemoFactory.getInstance(proxy2);
        demo.add();
        demo.desc();
    }
}
