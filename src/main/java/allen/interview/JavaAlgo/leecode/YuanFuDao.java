package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 *
 * 猿辅导面试题目
 * 一个有序数组[1,2,3,4,5,6,7,8]经过折叠后变为[4,5,6,7,8,1,2,3]
 * 使用什么方式去实现
 *
 * @date 2020/5/19 1:27 PM
 */
public class YuanFuDao {
    public static void main(String[] args) {
        int [] in={1,2,3,4,5,6,7,8};
        System.out.println(getMin(in));
    }

    public static int getMin(int [] input){
        int l=0,r=input.length-1;
        while (l<r){
            int mid=(r-l)/2+l;
            if (input[mid]<input[r]){
                r=mid;
            }else {
                l=mid+1;
            }
        }
        return input[l];

    }
}
