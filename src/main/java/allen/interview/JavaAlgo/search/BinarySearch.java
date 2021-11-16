package allen.interview.JavaAlgo.search;

/**
 * 二分查找如何使用java实现
 */
public class BinarySearch {

    /**
     * 使用递归实现二分查找算法
     *
     * @param arr    待查数组,排好序的 升序
     * @param key    要查找的目标
     * @param low    低位下标
     * @param height 高位下标
     * @return key在当中的位置, 未找到返回-1
     */
    public static int recursionBinarySearch(int[] arr, int key, int low, int height) {
        if (key < arr[low] || key > arr[height] || low > height) {
            return -1;
        }
        int mid = (low + height) / 2;
        if (key == arr[mid]) return mid;
        if (key > arr[mid]) return recursionBinarySearch(arr, key, mid + 1, height);
        if (key < arr[mid]) return recursionBinarySearch(arr, key, low, mid - 1);
        return -1;
    }

    /**
     * 使用while循环实现二分查找
     *
     * @param arr 待查数组,排好序的
     * @param key 要查找的目标
     * @return key在数组中的位置, 不存在返回-1
     */
    public static int commonBinarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;            //定义middle

        if (key < arr[low] || key > arr[high] || low > high) {
            return -1;
        }
        while (low <= high) {
//            middle = (low + high) / 2;//这个可能会有溢出风险,修改为如下代码
            middle = low + ((high - low) / 2);
            if (arr[middle] > key) {
                //比关键字大则关键字在左区域
                high = middle - 1;
            } else if (arr[middle] < key) {
                //比关键字小则关键字在右区域
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;        //最后仍然没
    }


    public static void main(String[] args) {
        int arr[] = {1, 4, 5, 7, 9, 10, 14, 15, 16};
        System.out.println(recursionBinarySearch(arr, 4, 0, arr.length - 1));
        System.out.println(commonBinarySearch(arr, 100));
    }
}
