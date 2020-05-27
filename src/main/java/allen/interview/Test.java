package allen.interview;


import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        ConcurrentHashMap<String ,Object> map=new ConcurrentHashMap<>(23);
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

