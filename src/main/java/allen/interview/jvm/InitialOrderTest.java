package allen.interview.jvm;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2019年03月05日 13:47
 */
public class InitialOrderTest {
    public boolean top;
    /* 静态变量 */
    private static String staticField = "静态变量";
    /* 变量 */
    private String field = "变量";

    /* 静态初始化块 */
    static {
        System.out.println(staticField);
        System.out.println("静态初始化块");
    }

    /* 初始化块 */ {
        System.out.println(field);
        System.out.println("初始化块");
    }

    /* 构造器 */
    public InitialOrderTest() {
        System.out.println("构造器");
    }

    //非静态内部类代码测试
    public class InnerNotStatic {
        private static final int i = 1;

        {
            System.out.println("非静态类初始化代码块");
        }

        public InnerNotStatic() {
            System.out.println("非静态类构造函数");
        }

    }

    public static class InnerStatic {
        private String file = "静态内部类变量";
        static String innerFile = "静态内部类的静态变量";

        static {
            System.out.println(innerFile);
            System.out.println("静态内部类静态代码块");
        }

//        private static InitialOrderTest initialOrderTest = new InitialOrderTest();

        public InnerStatic() {
            System.out.println("静态内部类构造函数");
        }

        public static void innerStaticMethod() {
            System.out.println("静态内部类静态方法");
        }
    }


    public static void main(String[] args) {
        InitialOrderTest.InnerStatic.innerStaticMethod();
//	    InitialOrderTest initialOrderTest=new InitialOrderTest();
//        System.out.println(InnerNotStatic.i);
//        System.out.println(InnerStatic.innerFile);
    }
}
