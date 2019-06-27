package allen.interview.spring.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

public class MyInvocationHandlerTest {

    @Test
    public void getProxy() {
        UserService userService=new UserServiceImpl();
        UserService proxy= (UserService) new MyInvocationHandler(userService).getProxy();
        proxy.add();
    }
}