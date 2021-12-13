package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * <p>
 * 不同路径
 * <p>
 * m*n 的长方形网格, 左上角走到右下角 只能 向右,向下一次移动一步
 * <p>
 * 问: 总共有多少条不同的路
 * @date 2021/12/13 下午11:19
 */
public class LC62 {

    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(7, 3));
    }
}
