package allen.interview.JavaAlgo.leecode;

/**
 * @author jianqiang8
 * @description
 * @date 2021/12/31 1:32 下午
 */
public class LC98 {
    public boolean isValidBST(TreeNode root) {
        //左子树的val小于根节点
        //右子树的val大于根节点
        return false;
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

