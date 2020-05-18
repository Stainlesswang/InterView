package allen.interview.jvm;

/**
 * @author AllenWong
 * @date 2020/5/15 11:24 PM
 */
public class StringInternTest {
    public static void main(String[] args) {
        String str1=new StringBuilder().append("fuck").append(" me").toString();
        System.out.println(str1.intern() == str1);
        String str2=new StringBuilder().append("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
