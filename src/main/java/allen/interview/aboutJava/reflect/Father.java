package allen.interview.aboutJava.reflect;

public class Father implements Person{
    private String house;
    private long money;

    @Override
    public void live() {
        System.out.println("Father live");
    }
}
