package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2020/4/25 10:04 PM
 */
public class LC21 {
    public ListNode mergeTwoLinkedList(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2, resultHead = new ListNode(-1);
        ListNode point = resultHead;
        while (null != p1 && null != p2) {
            if (p1.val > p2.val) {
                point.next = p2;
                p2 = p2.next;
            } else {
                point.next = p1;
                p1 = p1.next;
            }
            point = point.next;
        }

        if (p1 == null) {
            point.next = p2;
        }
        if (p2 == null) {
            point.next = p1;
        }
        return resultHead.next;

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


}
