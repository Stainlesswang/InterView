package allen.interview.JavaAlgo.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AllenWong
 * @date 2020/4/21 11:12 PM
 */
public class LC13 {
    public static int romanToInt(String s) {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("I", 1);
//        map.put("IV", 4);
//        map.put("V", 5);
//        map.put("IX", 9);
//        map.put("X", 10);
//        map.put("XL", 40);
//        map.put("L", 50);
//        map.put("XC", 90);
//        map.put("C", 100);
//        map.put("CD", 400);
//        map.put("D", 500);
//        map.put("CM", 900);
//        map.put("M", 1000);
//
//        int ans = 0;
//        for(int i = 0;i < s.length();) {
//            if(i + 1 < s.length() && map.containsKey(s.substring(i, i+2))) {
//                ans += map.get(s.substring(i, i+2));
//                i += 2;
//            } else {
//                ans += map.get(s.substring(i, i+1));
//                i ++;
//            }
//        }
//        return ans;
        HashMap<Character,Integer> map =new HashMap<Character,Integer>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        int sum=0;
        int i=0;
        while(i<s.length()-1){
            int first=map.get(s.charAt(i));
            int next=map.get(s.charAt(i+1));
            if (first>=next){
                sum+=first;
                i++;
            }else{
                sum+=next-first;
                i=i+2;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        romanToInt("MCMXCIV");
    }

}
