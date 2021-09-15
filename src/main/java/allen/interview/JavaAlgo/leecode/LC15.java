package allen.interview.JavaAlgo.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AllenWong
 * <p>
 * leecode 三数之和
 * @date 2020/4/22 12:08 AM
 */
public class LC15 {

    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            //特殊判断 nums为空或者长度小于三,返回空
            if (null == nums || nums.length < 3) {
                return res;
            }
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] > 0) {
                    return res;
                }
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int L = i + 1;
                int R = n - 1;
                while (L < R) {
                    int temp = nums[i] + nums[L] + nums[R];

                    if (temp == 0) {
                        List<Integer> currRes = new ArrayList<>();
                        currRes.add(nums[i]);
                        currRes.add(nums[L]);
                        currRes.add(nums[R]);
                        res.add(currRes);
                        while (L < R && nums[L + 1] == nums[L]) {
                            L++;
                        }
                        while (L < R && nums[R - 1] == nums[R]) {
                            R--;
                        }

                    } else if (temp > 0) {
                        R--;
                    } else {
                        L++;
                    }
                }


            }
            //如果循环找最左侧数字的时候>0,因为是排好序的 后边怎么相加都不可能等于零 直接调过,返回结果

            return res;
        }
    }
}
