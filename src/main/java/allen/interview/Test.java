package allen.interview;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {


        try {
        	ClassLoader loader=ClassLoader.getSystemClassLoader();
        	//loadClass只是把类的二进制流加载到JVM中，不进行初始化
        	Class n=loader.loadClass("allen.interview.jvm.InitialOrderTest");
        	//forName方法会初始化static变量和static块代码
        	Class n2=Class.forName("allen.interview.jvm.InitialOrderTest");
            ArrayList<String> list=new ArrayList<>();
            list.add("fuck");
            System.out.println(list);
//	        Field[] fields= n.getDeclaredFields();
//	        for (Field field : fields) {
//		        System.out.print(field.getName()+";");
//	        }
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

