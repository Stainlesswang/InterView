package allen.interview.JavaAlgo.company;


/**
 * taking data 面试题总结,求一个二叉树的深度
 * @author AllenWong
 * @date 2019/9/25 9:58 AM
 */
public class TreeDeepTest<T> {
    /**
     * 使用递归的方法求树的深度.
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root){
        if (root==null) return 0;
        int maxLeft;
        int maxRight;
        maxLeft=maxDepth(root.left);
        maxRight=maxDepth(root.right);
        return maxLeft>maxRight?maxLeft+1:maxRight+1;
    }




    /**
     * 首先定义树中的节点
     * left 节点
     * right 节点
     * 当前Node的value值 也就是保存的数据
     */
    class TreeNode{
        T val;
        TreeNode left;
        TreeNode right;
        public TreeNode(T t){
            this.val=t;
        }
    }
}
