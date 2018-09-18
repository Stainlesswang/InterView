package allen.interview;
import static java.lang.System.out;

public class Teat {
    public static void main(String[] args){
                Integer a = 5, b = 10;
                swap (a,  b);
                // a = 10, b = 5;
                out.println ("a = {0}, b = {1}"+a+ b);


        String s1 = "Programming";
        String s2 = "Programming";
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());

        }

    public static void swap( Integer x, Integer y) {
        Integer temp = x;
        x = y;
        y = temp;
        out.println ("swap"+x+ y);
    }
    }

