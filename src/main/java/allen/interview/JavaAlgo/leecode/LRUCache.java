package allen.interview.JavaAlgo.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AllenWong
 * @date 2022/2/14 下午10:35
 */
class LRUCache {

    private final Map<Integer, DataNode> dataMap;
    private DataNode head;
    private DataNode tail;


    private int size;
    private int capacity;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        dataMap = new HashMap<>(capacity);
        head = new DataNode();
        tail = new DataNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DataNode dataNode = dataMap.get(key);
        if (null == dataNode) {
            return -1;
        }
        //将该Node 移动到头部
        move2Head(dataNode);
        return dataNode.value;
    }

    public void put(int key, int value) {
        DataNode dataNode = dataMap.get(key);
        if (null == dataNode) {
            DataNode now = new DataNode(key, value);
            dataMap.put(key, now);
            add2Head(now);
            ++size;
            if (size > capacity) {
                DataNode tail = removeTail();
                dataMap.remove(tail.key);
                --size;
            }
        } else {
            dataNode.value = value;
            move2Head(dataNode);

        }

    }

    private void add2Head(DataNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DataNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void move2Head(DataNode node) {
        removeNode(node);
        add2Head(node);

    }

    private DataNode removeTail() {
        DataNode tailDataNode = tail.prev;
        removeNode(tailDataNode);
        return tailDataNode;
    }

    private static class DataNode {
        private int key;
        private int value;
        private DataNode prev;
        private DataNode next;

        DataNode() {
        }

        DataNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}