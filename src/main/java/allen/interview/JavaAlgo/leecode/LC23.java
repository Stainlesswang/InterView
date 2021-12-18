package allen.interview.JavaAlgo.leecode;

/**
 * @author jianqiang8
 * @description leecode23
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 *  
 * <p>
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * <p>
 * @date 2021/9/14 10:43 上午
 */
public class LC23 {

    public static ListNode mergeKLists(ListNode[] lists) {
        //先合并两个链表,然后进行递归操作,直到所有的链表元素合并完成
        //来k个指针,每次找到k指针里边的最小的一个,从左到右 然后将node追加到结果中
        //该节点向下遍历
        ListNode resultHead = new ListNode(-1);
        int k = lists.length;
        if (k <= 0) {
            return resultHead.next;
        }
        for (ListNode list : lists) {
            resultHead.next = mergeTwoList(resultHead.next, list);
        }
        return resultHead.next;
    }

    public static ListNode mergeTwoList(ListNode first, ListNode second) {
        ListNode resultHead = new ListNode(-1);
        ListNode temp = resultHead;
        ListNode p1 = first, p2 = second;
        while (null != p1 && null != p2) {
            if (p1.val < p2.val) {
                temp.next = p1;
                temp = p1;
                p1 = p1.next;
            } else {
                temp.next = p2;
                temp = p2;
                p2 = p2.next;
            }
        }
        if (null == p1) {
            temp.next = p2;
        }
        if (null == p2) {
            temp.next = p1;
        }
        return resultHead.next;
    }

    public static void main(String[] args) {
        System.out.println(mergeKLists(new ListNode[]{new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6)),
        }));

        System.out.println(mergeTwoList(new ListNode(1, new ListNode(8, new ListNode(9))),
                new ListNode(3, new ListNode(6, new ListNode(7)))));

    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode revert(ListNode head) {
            ListNode re = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = re;
                re = curr;
                curr = next;
            }
            return re;
        }

        @Override
        public String toString() {
            return val + "," + next;
        }
    }
}
