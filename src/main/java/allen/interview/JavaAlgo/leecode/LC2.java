package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2020/4/21 8:30 PM
 */
public class LC2 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = resultHead;
        int more = 0;
        while (null != p || null != q) {
            int x = (null == p) ? 0 : p.val;
            int y = (null == q) ? 0 : q.val;
            int sum = x + y + more;
            more = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (more > 0) {
            curr.next = new ListNode(more);
        }
        return resultHead.next;
    }
}
