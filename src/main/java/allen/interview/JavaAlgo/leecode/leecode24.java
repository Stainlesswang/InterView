package allen.interview.JavaAlgo.leecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AllenWong
 * @date 2020/4/17 5:25 PM
 */
public class leecode24 {

    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
      

        @Override
        public String toString() {
            return val+"";
        }
    }


    public static ListNode swapPairs(ListNode head) {

        if (null==head){
            return null;
        }
        ListNode two=head.next;
        if (null!=two){
            ListNode second=two.next;
            two.next=head;
            head.next=swapPairs(second);
            return two;
        }else{
            return head;
        }

    }
    public  static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abaseda"));
    }

}
