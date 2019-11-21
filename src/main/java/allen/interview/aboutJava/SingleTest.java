package allen.interview.aboutJava;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangjianqiang
 */
public class SingleTest {
    private static String value=null;
    private SingleTest(){
        if (value==null){
            value="fuck";
        }
    }
    public String getValue(){
        return value.toUpperCase();
    }
    public static SingleTest getInstance(){
        return new SingleTest();
    }

    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(2);
        for (int i = 0; i <10; i++) {
            service.submit(()->{
                SingleTest singleTest= SingleTest.getInstance();
                System.out.println(singleTest);
                System.out.println(singleTest.value);
            });
        }
    }
}
