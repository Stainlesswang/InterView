package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2020/4/21 8:44 PM
 */
public class LC5 {

    public static String longestPalindrome(String input) {
        if (null == input || input.length() < 1) return "";
        //遍历所有可能是最长回文子串的中心,可能是一个字母也可能是两个字母
        int len = input.length();
        int ans = 0;
        int start = 0, end = 0;
        for (int i = 0; i < len; i++) {
            int oneLen = expend(input, i, i);
            int twoLen = expend(input, i, i + 1);
            int currMax = Math.max(oneLen, twoLen);
            if (currMax > ans) {
                ans = currMax;
                start = i - (currMax - 1) / 2;
                end = i + currMax / 2;
            }
        }
        return input.substring(start, end + 1);
    }

    public static int expend(String s, int start, int end) {
        int L = start, R = end;
        //如果 左边的值和右边的值相等,并且左边大于等于0 右边小于等于 len-1  两遍往外扩展
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    public static void main(String[] args) {

        String a = "abcdcba";
        System.out.println(a.length());
        System.out.println(longestPalindrome(a));

    }


}
