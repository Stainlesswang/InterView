package allen.interview;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.System.out;

public class Teat {
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

