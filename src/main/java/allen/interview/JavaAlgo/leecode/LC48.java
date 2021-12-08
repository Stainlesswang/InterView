package allen.interview.JavaAlgo.leecode;

import java.util.Arrays;

/**
 * @author AllenWong
 * @date 2021/11/21 下午12:04
 */
public class LC48 {
    public static void main(String[] args) {
        rotate(new int[][]{{3, 4, 5}, {6, 7, 8}, {9, 10, 11}});

    }


    public static void rotate(int[][] matrix) {
        String a = "afv";
        char[] temp = a.toCharArray();
        Arrays.sort(temp);
        String orderStr = new String(temp);
        for (int i = 0; i < matrix.length; i++) {
            int[] now = matrix[i];
            for (int j = 0; j < now.length; j++) {
                System.out.println(now[j]);
            }
        }

    }


}
