package allen.interview.aboutJava.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {


        try {
        	ClassLoader loader=ClassLoader.getSystemClassLoader();
        	//loadClass只是把类的二进制流加载到JVM中，不进行初始化
        	Class n=loader.loadClass("allen.interview.aboutJava.reflect.Son");
        	//forName方法会初始化static变量和static块代码
//        	Class n2=Class.forName("allen.interview.jvm.InitialOrderTest");
            Field[] fieldsFather=n.getSuperclass().getDeclaredFields();
            for (Field field:fieldsFather){
                System.out.println("field = " + field.getName());
            }
	        Field[] fields= n.getDeclaredFields();
	        for (Field field : fields) {
		        System.out.print(field.getName()+";");
	        }
//	        Method[] methods=n.getMethods();
//	        for (Method method : methods) {
//		        System.out.println(method.getName());
//	        }

//            Class aClass = Class.forName("allen.auto.code.FreeMarkerTest");
//            Object object=aClass.newInstance();
//            Method method=aClass.getMethod("hahah");
//            method.invoke(object ,null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}

