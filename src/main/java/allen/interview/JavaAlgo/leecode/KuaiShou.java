package allen.interview.JavaAlgo.leecode;


import java.util.Stack;

/**
 * @author AllenWong
 * 快手二面题目
 * 只含有加减乘的运算表达式,进行数学计算
 * @date 2020/5/8 12:10 PM
 */
public class KuaiShou {



    public static int getResult(String input){
        Stack<Character> stack =new Stack<>();
        for (int i = 0; i <input.length() ; i++) {
            char now =input.charAt(i);
            if (Character.isDigit(now)){
                int tempNum = now - '0';
                while(++i < input.length() && Character.isDigit(input.charAt(i))){
                    tempNum = tempNum * 10 + (input.charAt(i) - '0');
                } i--;
            }
            if (now=='+'){

            }

        }
        return 0;
    }

    public static void main(String[] args) {
        String a="111+3+2-45-4*13+5*-4";
        getResult(a);
        System.out.println(a.replace('-', '#'));
        char aa='1';
        char bb='3';
        System.out.println((aa-'0') * (bb-'0'));

    }
}
