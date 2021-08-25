package allen.interview.jvm;

public class InnerClassTest {

    public InnerClassTest(){
        System.out.println("构造器");
    }
    static void testMethod(){
        System.out.println("静态方法");
    }
    static {
        System.out.println("静态代码块");
    }
    {
        System.out.println("构造代码块");
    }
    public static class StaticInnerClass{
        StaticInnerClass(){
            System.out.println("静态内部类的构造器");
        }
        static {
            System.out.println("静态内部类的静态代码块");
        }
        {
            System.out.println("静态内部类的构造代码块");
        }
        public static void testMethod(){
            System.out.println("静态内部类的静态方法");
        }
    }
    public  class InnerClass{
        InnerClass(){
            System.out.println("内部类的构造器");
        }
        {
            System.out.println("内部类的构造代码块");
        }
    }

    public static void main(String[] args) {
//        InnerClassTest.StaticInnerClass.testMethod();
        new InnerClassTest.StaticInnerClass();
    }
}