import allen.interview.spring.cyclicDependence.fucl;
import org.junit.Test;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    @Test
    public void testCircleByConstructor() throws Throwable {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-test.xml");
        fucl fucl= (allen.interview.spring.cyclicDependence.fucl) context.getBean("fu");
fucl.out();
    }
}
