package allen.interview.JavaAlgo.leecode;

import java.util.Arrays;

/**
 * @author AllenWong
 * <p>
 * 颜色分类
 * <p>
 * 0,1,2 代表 红,白,蓝 三种颜色, 使用原地排序进行排序
 * 按照 红白蓝顺序进行排序,相邻颜色放置中间
 * <p>
 * [2,0,2,1,1,0]->[0,0,1,1,2,2]
 * @date 2021/12/25 下午9:53
 */
public class LC75 {
    public static void sortColors(int[] nums) {
        int position = 0;
        for (int color = 0; color < 3; color++) {
            for (int j = 0; j < nums.length; j++) {
                if (color == nums[j]) {
                    swap(nums, j, position);
                    position++;
                }
            }
        }
        System.out.print(Arrays.toString(nums));
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        sortColors(new int[]{2, 0, 2, 1, 1, 0});
    }
}
