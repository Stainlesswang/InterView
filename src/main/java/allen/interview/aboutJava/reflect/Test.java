package allen.interview.aboutJava.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 测试把类加载到虚拟机的两种方式
 */
public class Test {
    public static void main(String[] args) {
        try {
            //loadClass只是把类的二进制流加载到JVM中，不进行初始化
            ClassLoader loader=ClassLoader.getSystemClassLoader();
        	Class n=loader.loadClass("allen.interview.aboutJava.reflect.Son");
        	//forName方法会初始化static变量和static块代码
        	Class n2=Class.forName("allen.interview.jvm.InitialOrderTest");
            //1.获取并输出类的名称
            Class mClass = Son.class;
            System.out.println("类的名称：" + mClass.getName());

            //2.1 获取所有 public 访问权限的变量
            // 包括本类声明的和从父类继承的
            Field[] fields = n.getDeclaredFields();

            //2.2 获取所有本类声明的变量（不问访问权限）
//            Field[] fields = mClass.getDeclaredFields();

            //3. 遍历变量并输出变量信息
//            for (Field field : fields) {
//                //获取访问权限并输出
//                int modifiers = field.getModifiers();
//                System.out.println("访问权限:"+Modifier.toString(modifiers));
//                //输出变量的类型及变量名
//                System.out.println("变量类型:"+field.getType().getName()
//                        + "--变量名称 " + field.getName());
//            }

            //根据反射修改一个类中的属性
            Son sonBean=new Son();
            Field field=n.getDeclaredField("sonName");
            field.setAccessible(true);
            field.set(sonBean,"AllenSon");
            Field field2=n.getDeclaredField("sonAge");
            field2.setAccessible(true);
            field2.set(sonBean,18);
            sonBean.printSonMsg();
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

