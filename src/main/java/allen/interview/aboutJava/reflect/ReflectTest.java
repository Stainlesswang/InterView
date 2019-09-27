package allen.interview.aboutJava.reflect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AllenWong
 * @date 2019/9/26 11:14 PM
 */
public class ReflectTest {
    public static void main(String[] args) {
        //创建三个对象.
        Father father=new Father();
        Father fatherSon=new Son();
        Son son=new Son();
        //对象容器,元素为Father
        List<Father> list=new ArrayList<>();

        list.add(father);
        list.add(fatherSon);
        list.add(son);
        //循环遍历其中的元素,并执行重写的方法
        for (Father bean : list) {
            if (bean instanceof Son){
                System.out.println("-------Son is Instance");
            }
            System.out.println(bean.getClass().toString());
            bean.out();
        }
    }
}
