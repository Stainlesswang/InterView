package allen.bigdata;

/**
 * @author jianqiang8
 * @description a->b->c
 * @date 2021/8/25 7:35 下午
 */
public class Test {


    public ListNode turnLikedList(ListNode head) {
        //用来保存前一个节点
        ListNode pre = null;
        ListNode curr = head;
        while (null != curr) {
            ListNode next = head.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }


    public class ListNode {
        public ListNode next;
        public int key;
        public int val;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
