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
        ListNode result = new ListNode(0);
        ListNode p = l1, q = l2, tmp = result;
        int more = 0;
        while (null != p || null != q) {
            int pVal = null == p ? 0 : p.val;
            int qVal = null == q ? 0 : q.val;
            int sum = pVal + qVal;
            tmp.next = new ListNode(sum % 10 + more);
            more = sum / 10;
            tmp = tmp.next;
            if (null != p) p = p.next;
            if (null != q) q = q.next;
        }
        if (more > 0) {
            tmp.next = new ListNode(more);
        }
        return result.next;
    }
}
