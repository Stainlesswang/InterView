package allen.interview.jvm;

/**
 * @author AllenWong
 *
 * 重写和重载在JVM层面的动态分配相关的概念类
 *
 * @date 2020/5/27 4:17 PM
 */
public class StaticDispatch {

    static abstract class Human{
//        void sayHello(){
//            System.out.println("I just a Human");
//        }
    }

    static class Man extends Human{

//        @Override
//        void sayHello() {
//            System.out.println("Hey I am a Man");
//        }
    }

    static class Woman extends Human{
//        @Override
//        void sayHello() {
//            System.out.println("Hello,Woman");
//        }
    }

    public void sayHello(Human human){
        System.out.println("niHao,guy");
    }

    public void sayHello(Man man){
        System.out.println("hello,gentleman");
    }
    public void sayHello(Woman woman){
        System.out.println("hi,lady");
    }

    public static void main(String[] args) {
        StaticDispatch dispatch=new StaticDispatch();
        Human man=new Man();
        Human woman=new Woman();

        dispatch.sayHello(man);

        dispatch.sayHello(woman);

    }

}
