package allen.interview.JavaAlgo.leecode;

/**
 * @author jianqiang8
 * @description
 * @date 2021/12/31 1:51 下午
 * <p>
 * G(n)=G(0)∗G(n−1)+G(1)∗(n−2)+...+G(n−1)∗G(0)
 */
public class LC96 {
    /**
     * 给定n个数字,
     * 每次从根节点去确定,知道所有数字都用完了, 这次算是一个结果, 结果+1
     */
    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        //G(n)=G(0)∗G(n−1)+G(1)∗(n−2)+...+G(n−1)∗G(0)
        for (int i = 2; i < n; i++) {
            System.out.println("G" + (i));
            for (int j = 1; j < i + 1; j++) {
                System.out.println("G" + (j - 1) + "*" + "G" + (i - j));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        numTrees(10);
    }
}
