package allen.interview.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeanAOPTest {
    @Test
    public void testAOP() throws Throwable {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-test.xml");
        TestBean testBean= (TestBean) context.getBean("testAOP");
        testBean.test(1,"b");
    }

}