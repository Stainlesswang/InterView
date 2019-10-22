package allen.interview.spring.proxy.cglib;

/**
 * 使用CGLIB的方式来实现该类的代理增强类
 *
 * @author AllenWong
 * @date 2019/10/14 10:22 PM
 */
public class CGLIBDemo {

    /**
     * 模拟一个需要被执行的方法
     */
    public void add(){
        System.out.println("the CGLIBDemo Class Old add() method");
    }
    public  void desc(){
        System.out.println("the CGLIBDemo Class Old desc method");
    }
}
