package allen.interview.JavaAlgo.datastructure.tree;

/**
 * 平衡二叉树的数据结构实现
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;


    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     * 二叉树查找和排序的实现
     *
     * @param key 要获取的节点key
     * @return 对应的Node对象的val值
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    /**
     * 插入key和对应val
     *
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    /**
     * 如果key存在于以x为根节点的子树中,则对value进行更新
     * 否则找到合适的位置进行插入
     *
     * @param x
     * @param key
     * @param val
     * @return
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x=floor(root,key);
        if (x==null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }
    public Key ceiling(Key key){
        Node x=ceiling(root,key);
        if (x==null) return null;
        return x.key;
    }
    private Node ceiling(Node x,Key key){
        if (x==null) return null;
        int cmp=key.compareTo(x.key);
        if (cmp==0) return x;
        if (cmp>0) return ceiling(x.right,key);
        Node t =ceiling(x.left,key);
        if (t!=null) return t;
        else return  x;
    }
    public Key select(int k){
        return select(root,k).key;
    }
    private Node select(Node x,int k){
        if (x==null) return null;
        int t =size(x.left);
        if (t>k) return select(x.left,k);
        else if (t<k) return select(x.right,k-t-1);
        else return x;
    }

}