package allen.interview.JavaAlgo.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AllenWong
 * <p>
 * 括号生成
 * <p>
 * 输入n 代表生成几个括号的组成
 * @date 2021 09 12
 */
public class lC22 {


    public static void dfs(String last, int left, int right, List<String> res) {
        //如果左括号和右括号都使用完毕,回溯开始 终止
        if (left == 0 && right == 0) {
            res.add(last);
            return;
        }
        if (left > right) {
            return;
        }
        if (left > 0) {
            dfs(last + "(", left - 1, right, res);
        }
        if (right > 0) {
            dfs(last + ")", left, right - 1, res);
        }
    }

    public static void main(String[] args) {

        List<String> res = new ArrayList<>();
        int n = 2;
        dfs("", n, n, res);
        System.out.println(res);
    }

}
