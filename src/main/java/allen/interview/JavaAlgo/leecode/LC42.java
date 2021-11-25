package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2021/11/21 上午11:35
 */
public class LC42 {


    public static int trap(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            System.out.println("left"+i+" "+max_left[i]);
            System.out.println("right"+i+" "+max_right[i]);

        }
        return sum;
    }

    public static void main(String[] args) {
        trap(new int[]{5,4,3,7,9,3,2,5});
    }

}
