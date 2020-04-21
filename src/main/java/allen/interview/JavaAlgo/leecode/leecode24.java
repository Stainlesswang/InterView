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

}
