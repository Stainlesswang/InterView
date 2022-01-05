package allen.interview.JavaAlgo.leecode;

public class LC98 {
    public static void main(String[] args) {

        TreeNode treeNode = new TreeNode(5);
        TreeNode treeNodeL = new TreeNode(1);
        TreeNode treeNodeR = new TreeNode(4);
        TreeNode treeNodeRL = new TreeNode(3);
        TreeNode treeNodeRR = new TreeNode(6);
        treeNodeR.left = treeNodeRL;
        treeNodeR.right = treeNodeRR;

        treeNode.left = treeNodeL;
        treeNode.right = treeNodeR;


        Solution solution = new Solution();
        solution.isValidBST(treeNode);
    }

    static class Solution {
        long pre = Long.MIN_VALUE;

        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            // 访问左子树
            if (!isValidBST(root.left)) {
                return false;
            }
            // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;
            // 访问右子树
            return isValidBST(root.right);
        }
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
