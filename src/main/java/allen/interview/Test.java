package allen.interview;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {


        try {
        	ClassLoader loader=ClassLoader.getSystemClassLoader();
        	Class n=loader.loadClass("allen.auto.code.Col");
	        Field[] fields= n.getDeclaredFields();
	        for (Field field : fields) {
		        System.out.print(field.getName()+";");
	        }
	        Method[] methods=n.getMethods();
	        for (Method method : methods) {
		        System.out.println(method.getName());
	        }

//            Class aClass = Class.forName("allen.auto.code.FreeMarkerTest");
//            Object object=aClass.newInstance();
//            Method method=aClass.getMethod("hahah");
//            method.invoke(object ,null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}

