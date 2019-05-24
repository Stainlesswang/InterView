package allen.interview.aboutJava.reflect;

public class Father implements Person{
    private String house;
    private long money;
    public String mFatherName;
    public int mFatherAge;

    public void printFatherMsg(){}
    @Override
    public void live() {
        System.out.println("Father live");
    }
}
