package allen.interview.JavaAlgo.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author AllenWong
 * @date 2020/4/22 12:08 AM
 */
public class LC15 {

    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);//先把数组排序 从小到大
            List<List<Integer>> res = new ArrayList<>();
            for(int k = 0; k < nums.length - 2; k++){
                if(nums[k] > 0) break;//最小的数字都大于零,退出不用找了
                if(k > 0 && nums[k] == nums[k - 1]) continue;//调过k未知和前一个未知相同的k,重复劳动
                int i = k + 1, j = nums.length - 1;//双指针初始化为 1, length-1;
                while(i < j){
                    int sum = nums[k] + nums[i] + nums[j];
                    if(sum < 0){
                        while(i < j && nums[i] == nums[++i]);
                    } else if (sum > 0) {
                        while(i < j && nums[j] == nums[--j]);
                    } else {
                        res.add(new ArrayList<>(Arrays.asList(nums[k], nums[i], nums[j])));
                        while(i < j && nums[i] == nums[++i]);
                        while(i < j && nums[j] == nums[--j]);
                    }
                }
            }
            return res;
        }
    }
}
