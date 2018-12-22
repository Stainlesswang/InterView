package allen.interview.JavaAlgo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月27日 16:08
 */
public class ThreeSum {
	public static void main(String[] args) {
		int[] num = {-2, -2, 1, 1, 2};
		System.out.println(new ThreeSum().threeSum(num));
	}

	private ArrayList<ArrayList<Integer>> threeSum(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		if (num == null) {
			return result;
		}
		//排序
		Arrays.sort(num);
		int sum, left, right;
		for (int i = 0; i < num.length - 2; i++) {
			//避免重复, 比如前面计算了以-1开头,后面就不用计算了
			if (i != 0 && num[i] == num[i - 1]) {
				continue;
			}
			left = i + 1;
			right = num.length - 1;
			/*
			 * 固定一个数,从后面的数中选出两个数,因为数组是有序的,所以可以
			 * 用两个数组下标left和right,left指向当前元素的后一个位置,right指向最后一个位置
			 * 三个数相加的和等于0时,加入解集;
			 * 小于0时,把left往右边移动;
			 * 大于0时,把right往左边移动;
			 */
			while (left < right) {
				sum = num[left] + num[right];
				if (sum + num[i] == 0) {
					ArrayList<Integer> solution = new ArrayList<>();
					solution.add(num[i]);
					solution.add(num[left]);
					solution.add(num[right]);
					result.add(solution);
					left++;
					right--;
					//这个优化必须加,不加时间超限,其实这个优化也没太大作用嘛
					while (left < right && num[left] == num[left - 1]) {
						left++;
					}
					while (left < right && num[right] == num[right + 1]) {
						right--;
					}
				} else if (sum + num[i] < 0) {
					left++;
				} else {
					right--;
				}
			}
		}
		return result;
	}
}

