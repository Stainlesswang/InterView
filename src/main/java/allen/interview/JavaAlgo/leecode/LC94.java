package allen.interview.JavaAlgo.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianqiang8
 * @description
 * @date 2021/12/31 1:32 下午
 */
public class LC94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        findTree(root, res);
        return res;
    }

    private void findTree(TreeNode treeNode, List<Integer> res) {
        if (null == treeNode) {
            return;
        }
        findTree(treeNode.left, res);
        res.add(treeNode.val);
        findTree(treeNode.right, res);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

