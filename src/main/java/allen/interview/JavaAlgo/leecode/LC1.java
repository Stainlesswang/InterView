package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2020/4/21 8:27 PM
 */
public class LC1 {

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
