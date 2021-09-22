package allen.interview.JavaAlgo.leecode;

import java.util.Arrays;

/**
 * @author jianqiang8
 * @date 2021/9/14实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * *
 * * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * *
 * * 必须 原地 修改，只允许使用额外常数空间。
 * *
 * *  
 * *
 * * 示例 1：
 * *
 * * 输入：nums = [1,2,3]
 * * 输出：[1,3,2]
 * * 示例 2：
 * *
 * * 输入：nums = [3,2,1]
 * * 输出：[1,2,3]
 * * 示例 3：
 * *
 * * 输入：nums = [1,1,5]
 * * 输出：[1,5,1]
 * * 示例 4：
 * *
 * * 输入：nums = [1]
 * * 输出：[1]
 * *  
 * *
 * * 提示：
 * *
 * * 1 <= nums.length <= 100
 * * 0 <= nums[i] <= 100 12:09 下午
 */
public class LC31 {

    public static void main(String[] args) {
        LC31 test = new LC31();
//        int[] mums = new int[]{1, 2, 3, 8, 5, 7, 6, 4};
        int[] mums = new int[]{3, 2, 1};

        test.nextPermutation(mums);
        System.out.println(Arrays.toString(mums));
    }

    public void nextPermutation(int[] nums) {
        //1. 找到两个相邻升序元素 i,j 并且 nums[i]<nums[j]  j后边的元素肯定是降序的,
        //2.  这时候找j后边的元素,有没有 比nums[i] <nums[k]<nums[j]
        //3. 交换i 和 k两个元素的位置,那么这个时候数字肯定变大了已经
        //4. 然后将i后边的元素进行升序排列, 也就是大数字放后边
        int len = nums.length;
        if (len <= 1) {
            return;
        }
        int i = len - 2, j = len - 1;

        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }
        // 找到了尽头也没找到,就算了,把全部元素翻转一下
        if (i < 0) {
            Arrays.sort(nums);
        } else {
            //找到 j右边比nums[i]大 并且在j右边最小的数字
            //直接从最右边找起来
            int k = len - 1;
            while (nums[k] <= nums[i]) {
                k--;
            }
            swap(i, k, nums);
            //最后一步,将i右边的数字进行排序,
            Arrays.sort(nums, i + 1, len);
        }
    }

    public void swap(int left, int right, int[] nums) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
