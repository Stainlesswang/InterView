package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * <p>
 * 编辑距离, 两个字符串 word1->word2 可以使用的步骤
 * 1.插入一个字符
 * 2.删除一个字符
 * 3.替换一个字符
 * <p>
 * 求最小的转换步骤
 * @date 2021/12/25 下午4:23
 */
public class LC72 {

    public static int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n2; i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[n1][n2];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("adsg", "ttsg"));
    }

}
