package allen.interview.JavaAlgo.leecode;

/**
 * @author jianqiang8
 * @description 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 * <p>
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4：
 * <p>
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 * <p>
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 * <p>
 * fn = min{f(n-1), f(n-11), f(n-4)} +1
 * @date 2021/8/27 8:00 下午
 */
public class LC10 {
    //考虑普通情况, *代表前边0个或者多个该字符 .代表任意单个字符.微信
    public static void main(String[] args) {

        System.out.println(maxRain(new int[]{1,8,6,2,5,4,8,3,7}));
//        for (int i = 0; i < 15; i++) {
//            System.out.println(i);现在的我们感觉是十分的懒散和得意的,如果我们的未来变的不可知,如果我们的事情变得突然复杂了起来
        //现在面试一面不过,八股文不太行,算法基础不太行
//        }
    }

    public static boolean isCanMatch(String content, String rule) {
        int contentIndex = 0, ruleIndex = 0;
        int contentLen = content.length();
        int ruleLen = rule.length();
        while (contentIndex < contentLen && ruleIndex < ruleLen) {
            char c = content.charAt(contentIndex);
            char r = content.charAt(contentIndex);
            //遇到了*, r-1位是关键, c指针向右移动,直到遇到不等于 r-1位的字符,r+1向后看是否相等
            contentIndex++;
            ruleIndex++;
        }
        return false;
    }


    public static int  maxRain(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int ans = 0;
        while (left != right) {
            if (nums[left] <= nums[right]) {
                ans = Math.max(ans, nums[left] * (right - left));
                left++;
            } else {
                ans = Math.max(ans, nums[right] * (right - left));
                right--;
            }
        }
        return ans;
    }
}
