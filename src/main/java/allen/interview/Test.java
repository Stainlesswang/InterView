package allen.interview;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {


        try {
            Class aClass = Class.forName("allen.auto.code.FreeMarkerTest");
            Object object=aClass.newInstance();
            Method method=aClass.getMethod("hahah");
            method.invoke(object ,null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }

}

