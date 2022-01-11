package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2022/1/11 下午10:42
 */
public class LC236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findDfs(root, p.val, q.val);
        return root;

    }

    public TreeNode findDfs(TreeNode currNode, int p, int q) {
        int curr = currNode.val;
        if (curr == p || curr == q) {
            return currNode;
        }
        TreeNode left = findDfs(currNode.left, p, q);
        TreeNode right = findDfs(currNode.right, p, q);
        if (null != left && null != right) {
            return currNode;
        }
        if (null == left) return right;
        return left;
    }
}
