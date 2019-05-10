import allen.interview.aboutJava.GoogleGuava;

public class AllenMain {
    public static void main(String[] args) {
        GoogleGuava  googleGuava=new GoogleGuava();
        System.out.println(System.getProperty("java.library.path"));
        System.loadLibrary("test");

//        System.out.println(googleGuava.StringUtilTest());
        for (int i = 0; i <10 ; i++) {
            System.out.println("this is " + i);
        }
    }
}
