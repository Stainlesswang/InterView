package allen.interview.aboutJava;

import com.alibaba.fastjson.JSON;

import java.util.TreeMap;

/**
 * @author wangjianqiang
 */
public class HashTest {
    public static void main(String[] args) {
        String a= "fuck";
        String b=new String("fuck");
        String c="fuck";
        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        TreeMap<Object, Object> map=new TreeMap<>();
        map.put("3",1);
        map.put("4",1);
        map.put("2",2);
        map.put("1",1);

        TreeMap<Integer, Integer> map2=new TreeMap<>();
        map2.put(3,1);
        map2.put(4,1);
        map2.put(2,2);
        map2.put(1,1);


        Object[] arg={"111",map};
        Object[] arg2={"111",map2};

        System.out.println(JSON.toJSONString(arg));
        System.out.println(JSON.toJSONString(arg2));

    }
}
