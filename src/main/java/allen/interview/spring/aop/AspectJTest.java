package allen.interview.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 定义一个切面,使用注解实现
 */
@Aspect
public class AspectJTest {
    /**
     * 定义切点
     */
    @Pointcut("execution(public void allen.interview.spring.aop.TestBean.test(int,*))")
    public void test(){
    }

    /**
     * 在切点之前执行
     */
    @Before("test()")
    public void beforeTest(){
        System.out.println("before Test running!");
    }

    /**
     * 在切点之前执行
     */
    @After("test()")
    public void afterTest(){
        System.out.println("after Test running!");
    }

    /**
     * 环绕型通知,在切点的前后都执行
     * @param joinPoint
     * @return
     */
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
