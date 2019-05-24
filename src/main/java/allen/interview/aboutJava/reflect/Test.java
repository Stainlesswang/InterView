package allen.interview.aboutJava.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Test {
    public static void main(String[] args) {


        try {
        	ClassLoader loader=ClassLoader.getSystemClassLoader();
        	//loadClass只是把类的二进制流加载到JVM中，不进行初始化
        	Class n=loader.loadClass("allen.interview.aboutJava.reflect.Son");
        	//forName方法会初始化static变量和static块代码
//        	Class n2=Class.forName("allen.interview.jvm.InitialOrderTest");
            //1.获取并输出类的名称
            Class mClass = Son.class;
            System.out.println("类的名称：" + mClass.getName());

            //2.1 获取所有 public 访问权限的变量
            // 包括本类声明的和从父类继承的
            Field[] fields = mClass.getFields();

            //2.2 获取所有本类声明的变量（不问访问权限）
            //Field[] fields = mClass.getDeclaredFields();

            //3. 遍历变量并输出变量信息
            for (Field field :
                    fields) {
                //获取访问权限并输出
                int modifiers = field.getModifiers();
                System.out.print(Modifier.toString(modifiers) + " ");
                //输出变量的类型及变量名
                System.out.println(field.getType().getName()
                        + " " + field.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}

