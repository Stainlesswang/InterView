package allen.interview.JavaAlgo.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AllenWong
 * @date 2022/1/12 上午12:07
 */
public class LC102 {
    public static int maxProfit(int[] prices) {
        int ans = 0;
        int pre = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int curr = prices[i];
            if (curr >= pre) {
                continue;
            }
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= curr) {
                    continue;
                }
                ans = Math.max(ans, prices[j] - curr);
            }
            pre = curr;
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.print("this result=" + i);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        findWithDeep(root, 0, res);
        return res;
    }

    public void findWithDeep(TreeNode currNode, int deep, List<List<Integer>> res) {
        if (null == currNode) return;
        List<Integer> integers;
        if (deep == res.size()) {
            integers = new ArrayList<>();
        } else {
            integers = res.get(deep);
        }
        integers.add(currNode.val);
        findWithDeep(currNode.left, deep + 1, res);
        findWithDeep(currNode.right, deep + 1, res);
    }
}
