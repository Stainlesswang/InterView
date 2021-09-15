package allen.interview.JavaAlgo.leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author AllenWong
 * <p>
 * 有效的括号,遇到左括号,入栈, 遇到右括号,必须要有栈顶的一个元素与之匹配
 * 并且元素出栈
 * @date 2020/4/22 12:18 AM
 */
public class LC20 {
    //构造一个映射,右括号为key 做边括号为value
    Map<Character, Character> mapping = new HashMap<>();

    {
        mapping.put(')', '(');
        mapping.put(']', '[');
        mapping.put('}', '{');
    }

    public boolean isVal(String input) {
        if ("".equals(input) || null == input) {
            return false;
        }
        int len = input.length();
        if (len % 2 > 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < len; i++) {

            Character now = input.charAt(i);
            //是右边结束括号,需要出栈一个元素,做比较
            if (mapping.containsKey(now)) {

                Character left = mapping.get(now);
                Character stackNode = stack.isEmpty() ? '*' : stack.pop();
                if (!stackNode.equals(left)) {
                    return false;
                }

            } else {
                stack.push(now);
            }

        }


        return stack.isEmpty();

    }


}
