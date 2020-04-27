package allen.interview.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author WangJianQiang
 * @Description: HashMap特性学习相关记录today is my favorite habit
 * @date 2019年02月20日 10:18
 */
public class HashMapLearn {

    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        ConcurrentHashMap concurrentHashMap;

        hashMap.put("sss", null);
        Map<String, String> hashtable = new Hashtable<>();

        for (Map.Entry<String, String> o : hashMap.entrySet()) {
            System.out.println(o.getKey());
        }

        int n = 20 - 1;
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);
        System.out.println((n < 0) ? 1 : (n >= 1024) ? 1024 : n + 1);
    }
}
