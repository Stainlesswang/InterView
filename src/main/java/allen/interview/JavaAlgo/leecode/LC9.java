package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 * @date 2020/4/21 10:50 PM
 */
public class LC9 {
    public static boolean isPalindrome(int x) {
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }
    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        int help = 1;
        int tmp = x;
        while (tmp >= 10) {
            help *= 10;
            tmp /= 10;
        }
        while (x != 0) {
            if (x % 10 != x / help) {
                return false;
            }
            x = x % help / 10;
            help /= 100;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(34534));
    }
}
