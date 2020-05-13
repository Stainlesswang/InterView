package allen.interview.JavaAlgo.sort.快排;

import allen.interview.JavaAlgo.sort.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * QuickSort 快速排序，寻找一个基数进行分组，
 * 左边都是小于基数的，右边都是大于基数的
 * 然后分别对两个分组里的数据进行排序，排完后数组即有序
 * 算法的关键在于切分的算法，
 * 1.对于某个j，a[j]已经排定;
 * 2.a[lo]到a[j-1]中的所有元素都不大于a[j]
 * 3.a[j+1]到a[hi]中的所有元素都不小于a[j]
 *
 *
 *
 * ：
 * 1.在数组较小（1到15）这种的规模下，插入排序要优于快速排序，可以利用这一点在递归的时候使用InsertSort
 * 2.三项切分，该方法适用于数据中含有较多重复元素的情况下，分组的是否多加一组相等元素
 * @author WangJianQiang
 * @Description:
 * @date 2018年12月13日 9:35
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        //打乱一下顺序
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }

    private void sort(T[] nums, int l, int h) {
        if (h <= l)
            return;
        int j = partition(nums, l, h);
        sort(nums, l, j - 1);
        sort(nums, j + 1, h);
    }

    private void shuffle(T[] nums) {
        List<Comparable> list = Arrays.asList(nums);
        Collections.shuffle(list);
        list.toArray(nums);
    }

    private int partition(T[] nums, int l, int h) {
        int i = l, j = h + 1;
        T v = nums[l];
        while (true) {
            while (less(nums[++i], v)) if (i==h) break;
            while (less(v, nums[--j])) if (j==l) break;
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >right) return;
        int i=left;
        int j=right;
        int base=arr[left];

        while (i!=j) {

            while (arr[j] >= base && i < j) {
                j--;
            }
            while (arr[i] <= base && i < j) {
                i++;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        arr[left] = arr[i];
        arr[i] = base;

        quickSort(arr, left, i - 1);

        quickSort(arr, i + 1, right);

    }

    public static void main(String[] args) {

    }
}
