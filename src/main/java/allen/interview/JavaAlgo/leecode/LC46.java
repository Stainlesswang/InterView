package allen.interview.JavaAlgo.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AllenWong
 * @date 2021/11/21 下午12:04
 */
public class LC46 {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{3, 4, 5}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> begin = Arrays.stream(nums).boxed().collect(Collectors.toList());
        find(begin, new ArrayList<>(), res);

        return res;

    }

    public static void find(List<Integer> remain, List<Integer> path, List<List<Integer>> res) {
        if (remain.size() == 1) {
            path.add(remain.get(0));
            res.add(path);
        }
        for (int i = 0; i < remain.size(); i++) {
            int now = remain.get(i);
            List<Integer> next = new ArrayList<>(path);
            next.add(now);
            List<Integer> nextNeed = remain.stream().filter(t -> !t.equals(now)).collect(Collectors.toList());
            find(nextNeed, next, res);
        }
    }


}
