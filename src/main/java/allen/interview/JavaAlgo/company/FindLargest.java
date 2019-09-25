package allen.interview.JavaAlgo.company;

/**
 * talking data 笔试题
 * 查找一个int数组当中,降序排列的情况下,第K大的数字
 * 题目:
 * Find the kth largest element in an unsorted array.
 * Note the it is the kth largest element in the sorted order.
 * not the kth distinct element
 * 例子1:
 * Input: [3,2,1,5,6,4] and key=2
 *
 * Output:5
 *
 * 例子2:
 * Input:[3,2,3,1,2,4,5,5,6] and key=4
 * Output:4
 *
 * 实现思路,这里其实就是一个排序算法,在排序算法上 加上==key的条件返回相应的数字即可
 *
 * @author AllenWong
 * @date 2019/9/25 10:53 AM
 */
public class FindLargest {
    /**
     *
     * @param array
     * @param key
     * @return
     */
    public static int findKthLargest(int [] array,int key){
        //这里个人觉得使用冒泡排序会好点,从剩余的数字当中找到最大,
        //当找了key次的时候,把第key个数返回即可, 整个方法结束
        int len=array.length;
        int a=-1;
        for (int i = len; i >0; i--) {
            for (int j = 0; j <i-1 ; j++) {
                if (array[j]>array[j+1]){
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
            if (i==key-1) {
                a= array[len-key];
                break;
            }
        }
        return a;
    }

    public static void main(String[] args) {
       int [] array={3,2,1,5,6,4};
        System.out.println(findKthLargest(array, 2));
//        int len=10;
//        for (int i = len; i >0; i--) {
//            System.out.println("----" + i);
//            for (int j = 0; j <i ; j++) {
//                System.out.println(j);
//            }
//        }
    }

}
