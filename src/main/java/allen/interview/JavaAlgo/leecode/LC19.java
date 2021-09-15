package allen.interview.JavaAlgo.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AllenWong
 * <p>
 * 删除链表的倒数第n个节点,
 * @date 2020/4/22 12:54 AM
 */
public class LC19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 首先要知道删除节点如何处理? 找到该节点的position, 前置节点next属性=该节点后置节点即可完成
        ListNode resultHead = new ListNode(0);
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        int index = 0;
        ListNode cor = head;
        while (null != cor) {
            int now = cor.val;
            nodeMap.put(++index, cor);
            System.out.println(now);
            cor = cor.next;
        }
        // 要删除节点位置, 现在只需要将其前置节点指向下一个节点即可,前置节点为空的话,后置节点挂到结果上
        int remPosition = index - n + 1;

        ListNode preNode = nodeMap.get(remPosition - 1);
        ListNode nextNode = nodeMap.get(remPosition + 1);
        if (null == preNode) {
            //要删除的是首节点
            resultHead = nextNode;
        } else {
            preNode.next = nextNode;
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
