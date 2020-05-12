package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2020/4/21 8:38 PM
 */
public class LC4 {

    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;


    }

    public static void main(String[] args) {
        findMedianSortedArrays(new int[]{1,4},new int[]{2,3,6,9,11,16,45});
    }

}
