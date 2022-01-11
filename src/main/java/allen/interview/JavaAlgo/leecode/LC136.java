package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2022/1/11 下午11:28
 */
public class LC136 {

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 3, 5, 4, 2, 5, 3,4, 9}));
    }
    public static int singleNumber(int[] nums) {
        //一个元素只出现一次,其余元素均出现两次,怎么能找到这个元素呢?
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (map.containsKey(nums[i])) {
//                map.remove(nums[i]);
//            } else {
//                map.put(nums[i], 1);
//            }
//        }
//        return map.keySet().stream().findFirst().orElse(-1);

        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
