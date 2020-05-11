package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2020/4/21 8:44 PM
 */
public class LC5 {

    public static String longestPalindrome(String s) {

        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            //以i位置作为中心向左右扩展
            int len1 = expandAroundCenter(s, i, i);
            //以i i+1 作为中心 向两边扩展,两边的值不相同停止扩展
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);//得到两个中心最长的字符长度
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        long curr=System.currentTimeMillis();
        longestPalindrome("sdasdfweasdfergasdfjasodksndvcxknck");
    }
}
