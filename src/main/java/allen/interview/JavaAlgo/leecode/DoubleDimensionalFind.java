package allen.interview.JavaAlgo.leecode;

/**
 * @author WangJianQiang
 * @Description:查找二维数组中是否含有某个数字
 * 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中。
 * Consider the following matrix:
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * <p>
 * Given target = 5, return true.
 * Given target = 20, return false.
 *
 * 要求时间复杂度 O(M + N)，空间复杂度 O(1)
 *
 * 技巧：从右上角的数开始，左边的都比它小，下方的都比它大。每次比较大小后确定下一次搜索范围。逐步缩小查找的范围
 * @date 2019年03月11日 14:33
 */
public class DoubleDimensionalFind {
}
