package allen.interview.JavaAlgo.leecode;

/**
 * @author AllenWong
 *
 * 新浪面试题
 * @date 2020/5/25 11:27 PM
 */
public class Sina {
    private static final String BASE_62_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = BASE_62_CHAR.length();
    public static String fromBase10(long i) {
        StringBuilder sb = new StringBuilder();
        if (i == 0) {
            return "a";
        }
        while (i > 0) {
            i = fromBase10(i, sb);
        }
        return sb.reverse().toString();
    }

    private static long fromBase10(long i, final StringBuilder sb) {
        int rem = (int)(i % BASE);
        sb.append(BASE_62_CHAR.charAt(rem));
        return i / BASE;
    }

    public static void main(String[] args) {
        System.out.println(fromBase10(23444353533L));
    }
}
