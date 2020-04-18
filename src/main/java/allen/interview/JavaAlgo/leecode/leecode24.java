package allen.interview.JavaAlgo.leecode;

import java.util.HashMap;
import java.util.List;

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

    public static void main(String[] args) {
        ListNode listNode=new ListNode(1);
        ListNode listNode2=new ListNode(2);

        ListNode listNode3=new ListNode(3);

        ListNode listNode4=new ListNode(4);

        listNode.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        System.out.println(listNode);
        System.out.println(listNode.next);
        System.out.println(listNode.next.next);
        System.out.println(listNode.next.next.next);
        ListNode fin= swapPairs(listNode);

        System.out.println(fin.val);
        System.out.println(fin.next);
        System.out.println(fin.next.next);
        System.out.println(fin.next.next.next);


    }

}
