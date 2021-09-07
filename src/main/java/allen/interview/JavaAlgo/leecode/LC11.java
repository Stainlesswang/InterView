package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2020/4/21 11:00 PM
 */
public class LC11 {
    /**
     * 盛水最多的容器,左右两个指针向中间寻找,
     * 停止条件是指针相遇,移动条件是移动边界较小的那个
     * 理论是木桶效应,左右两个边界,最小的那个此时已经是最优解了
     * 再也找不到比当前容量更大的比较小这个作为边的容器了
     *
     * @param height 边界高度数组
     * @return 可容纳的最大数量
     */
    public int maxArea(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int len = right - left;
            ans = Math.max(ans, len * (Math.min(height[left], height[right])));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}
