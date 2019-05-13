package allen.interview.designPatterns.singleton;

/**
 * Initialization on Demand Holder（IODH）
 * 利用java类加载的锁机制,保证实例的唯一性,
 * 只有当getInstance()第一次被调用的时候,这时候要访问内部类LazyHolder的静态资源时,
 * 静态内部类LazyHolder才被JVM虚拟机加载,并且保证在第一次之后调用的所有getInstance()
 * 都是唯一的这一个
 *
 */
public class SomeThing {
    static {
        System.out.println("load SomeThing class");
    }

    //构造方法必须为private,隐藏外部对构造器的使用
    private SomeThing(){ System.out.println("SomeThing Structure Running!"); }

    //静态内部类,作为实例的包裹类. 主要是为了利用虚拟机加载该类时候的锁机制
    //在加载了外部类的时候,内部类是不被加载的,只有在使用内部类的东西的时候才进行加载该类
    private static class LazyHolder{
        static {
            System.out.println("load LazyHolder CLass");
        }
        private LazyHolder(){
            System.out.println("Lazy Holder is running!");
        }
        //实例的创建,只有在所属类被虚拟机加载的时候才会被创建,并且仅此一次一份
        static SomeThing INSTANCE=new SomeThing();
    }
    //外部访问单例对象入口
    public static SomeThing getInstance(){
        return LazyHolder.INSTANCE;
    }
}
