package allen.interview.JavaAlgo.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AllenWong
 * @date 2022/2/14 下午10:35
 */
class LRUCache {

    private final Map<Integer, Integer> dataMap;

    private int capacity = 8;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dataMap = new HashMap<>(capacity);
    }

    public int get(int key) {
        Integer val;
        if (null == (val = dataMap.get(key))) {
            return -1;
        }

        return val;
    }

    public void put(int key, int value) {
        synchronized (dataMap) {
            if (dataMap.keySet().size() < capacity) {
                dataMap.put(key, value);
            }

        }

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