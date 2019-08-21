package allen.interview.thread.threadLocal;

/**
 * 该类在指定场景下说明什么是threadLocal
 *
 * 场景:
 *    在提供用户登录访问功能的web接口中, 不同用户的访问对项目来讲都是开启不同的线程.我们用ThreadLocal来
 *    记录当前线程也就是当前url访问者的信息. 以便在controller,service,dao等地方使用当前用户的信息
 * 实现思路:
 *    当前访问线程id作为key,用户对象信息作为value存储下来
 */
public class ThreadLocalDemo {
    private final  static ThreadLocal<String> threadLocal=new ThreadLocal<>();

    public static void addUser(String obj){
        threadLocal.set(obj);
    }
    public static String getUser(){
        return  threadLocal.get();
    }
    public static void remove(){
        threadLocal.remove();
    }
}
