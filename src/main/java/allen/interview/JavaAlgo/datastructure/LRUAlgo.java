package allen.interview.JavaAlgo.datastructure;

import java.util.HashMap;

/**
 * @author jianqiang8
 * @description
 * @date 2021/8/23 5:06 下午
 */
public class LRUAlgo {

    private EntryNode LinkedListHolder = new EntryNode(0, "");
    private EntryNode tailNode;
    private HashMap<Integer, EntryNode> indexMap = new HashMap<>();

    public synchronized void put(int key, String value) {
        //如果链表为空
        if (tailNode == null) {

        }
        //首先要看map里边有没有我这个node,如果有的话,需要做两件事情
        if (indexMap.containsKey(key)) {

        }
        //1.找到这个链表所在的位置,把该node移动到队尾

        EntryNode currNode = new EntryNode(key, value);
        currNode.setPre(null);
        currNode.setNext(null);
        if (tailNode == null) {
            tailNode = currNode;
        }

        //2.找到该node的前置节点,以及后置节点,并且吧该前直接点的后置设置为
    }

    public class EntryNode {
        private EntryNode pre;
        private EntryNode next;
        private int key;
        private Object value;

        public EntryNode(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public EntryNode getPre() {
            return pre;
        }

        public void setPre(EntryNode pre) {
            this.pre = pre;
        }

        public EntryNode getNext() {
            return next;
        }

        public void setNext(EntryNode next) {
            this.next = next;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
