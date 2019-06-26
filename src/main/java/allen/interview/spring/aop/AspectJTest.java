package allen.interview.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectJTest {
    @Pointcut("execution(public void allen.interview.spring.aop.TestBean.test(int,*))")
    public void test(){
    }
    @Before("test()")
    public void beforeTest(){
        System.out.println("before Test running!");
    }
    @After("test()")
    public void afterTest(){
        System.out.println("after Test running!");
    }
    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint joinPoint){
        System.out.println("before1");
        Object o=null;
        try {
            o=joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after1");
        return o;
    }
}
