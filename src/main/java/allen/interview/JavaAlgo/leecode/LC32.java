package allen.interview.JavaAlgo.leecode;

import java.util.Stack;

/**
 * @author jianqiang8
 * @description 32 最长有效括号
 * <p>
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 * <p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 * <p>
 * 输入：s = ""
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 * @date 2021/9/23 8:07 下午
 */
public class LC32 {
    //核心点 栈里边要存下标

    public static void main(String[] args) {
        LC32 lc32 = new LC32();
        System.out.println(lc32.longestValidParentheses("()(()"));
    }

    public int longestValidParentheses(String s) {
        /*
         * 1.遇到左括号入队列
         * 2.遇到右括号,尝试从左括号去匹配,如果匹配上了,括号数量+1
         * 3.如果没匹配上,记录下前边最大的括号数量,看下是否找到尽头了
         * */
        if (null == s || "".equals(s)) {
            return 0;
        }
        Stack<Integer> st = new Stack<>();
        int ans = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            Character now = s.charAt(i);
            if (now.equals('(')) {
                st.add(i);
            } else {
                if (!st.isEmpty()) {
                    st.pop();
                    if (st.isEmpty()) ans = Math.max(ans, i - start + 1);
                    else ans = Math.max(ans, i - st.peek());
                } else {
                    start = i + 1;
                }
            }
        }
        return ans;
    }
}
