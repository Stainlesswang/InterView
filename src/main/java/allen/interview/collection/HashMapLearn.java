package allen.interview.collection;

import java.util.*;

/**
 * @author WangJianQiang
 * @Description: HashMap特性学习相关记录today is my favorite habit
 * @date 2019年02月20日 10:18
 */
public class HashMapLearn {

    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();

        hashMap.put("sss", null);
        Map<String, String> hashtable = new Hashtable<>();
        Map<String, String> concurrentHashMap = new LinkedHashMap<>();
        HashSet<String> set=new HashSet<>();
        set.add("fuck");

        for (Map.Entry<String, String> o : hashMap.entrySet()) {
            System.out.println(o.getKey());
        }
    }
}
