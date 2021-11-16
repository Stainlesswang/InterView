package allen.interview.JavaAlgo.leecode;

/**
 * @author jianqiang8
 * @description 旋转数组
 * <p>
 * 例子: 升序且不重复数组 [0,1,2,4,7,9,10] 在index=3的位置进行旋转
 * 变为: [4,7,9,10,0,1,2]
 * <p>
 * 给定一个target,找出旋转后数组的该数所在的下标,没有返回0
 *
 *
 *
 * @date 2021/9/26 10:25 上午
 */
public class LC33 {


    /**
     * 二分查找算法
     *
     * @param nums   排好序的数组
     * @param target 寻找的目标元素
     * @return 目标元素下标, 不存在则为-1
     */
    public int binarySearch(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return target == nums[0] ? 0 : -1;

        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
