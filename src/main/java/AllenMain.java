import org.apache.tomcat.util.digester.Digester;

public class AllenMain {
    public static void main(String[] args) {
        System.out.println(myAtoi("-132834929"));
        System.out.println(Math.sqrt(18));
        System.out.println(Math.pow(4, 3));
        System.out.println(Integer.MAX_VALUE);
        Digester digester = new Digester();
        ClassLoader classLoader1=digester.getClass().getClassLoader();
        ClassLoader classLoader2=digester.getClass().getClassLoader().getParent();
    }

    public static int myAtoi(String str) {
        if ("".equals(str)) return 0;
        int n = str.length();
        int idx = 0;
        boolean neg = false;
        while (idx < n && str.charAt(idx) == ' ') {
            idx++;
        }
        if (idx == n) return 0;
        if ('-' == str.charAt(idx)) {
            idx++;
            neg = true;
        }
        if ('+' == str.charAt(idx)) {
            idx++;
        }
        if (!Character.isDigit(str.charAt(idx))) return 0;
        int ans = 0;
        while (idx < n && Character.isDigit(str.charAt(idx))) {
            int now = str.charAt(idx) - '0';
            if (ans > (Integer.MAX_VALUE / 10 - now)) {
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + now;
            idx++;
        }
        return neg ? -ans : ans;

    }
}
