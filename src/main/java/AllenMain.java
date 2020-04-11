import allen.interview.aboutJava.GoogleGuava;

import java.util.UUID;

public class AllenMain {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(System.getProperty("java.library.path"));

        System.out.println(UUID.randomUUID().toString());
        System.out.println("王梓硕");
    }
}
