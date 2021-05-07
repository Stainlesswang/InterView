package allen.interview;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        ConcurrentHashMap<String ,Object> map=new ConcurrentHashMap<>(23);
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item:list) {
            if ("1".equals(item)){
                list.remove(item);
            }
        }
        System.out.println(list);
    }


    private  ConcurrentHashMap<String ,Object> map=new ConcurrentHashMap<>(100);

    private  ConcurrentHashMap<String ,Object> timeMap=new ConcurrentHashMap<>(100);

    public void setCache(String key,Object o){
        while (map.size()>=100){
            //clear();
        }
        map.put(key,0);
        timeMap.put(key,System.currentTimeMillis());
    }

    public Boolean getCache(String key){
        return null ==map.get(key);
    }

//    public void clear(){
//        for ()
//    }

}

